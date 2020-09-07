package com.amo.motherfinancetest.ui.main

import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amo.motherfinancetest.R
import com.amo.motherfinancetest.model.Bank
import com.amo.motherfinancetest.model.BankParcel
import com.amo.motherfinancetest.model.Result
import com.amo.motherfinancetest.ui.base.BaseActivity
import com.amo.motherfinancetest.ui.main.detail.BankDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    private var adapter: BankListAdapter? = null

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initUi() {
        super.initUi()
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        bankList.addItemDecoration(decoration)
        adapter = BankListAdapter { bank ->
            bank?.let {
                onBankClick(it)
            }
        }.apply {
            bankList.adapter = this
            bankList.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        retryBtn.setOnClickListener {
            viewModel.getBanks()
        }
    }

    override fun watchData() {
        super.watchData()
        viewModel.responseBanks.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Result.Error -> {
                    retryBtn.visibility = View.VISIBLE
                }
                is Result.Done -> {
                    progressBar.visibility = View.INVISIBLE
                    retryBtn.visibility = View.INVISIBLE
                    adapter?.submitList(it.data?.data)
                }
            }


        })
    }

    private fun onBankClick(bank: Bank) {
        startActivity(BankDetail.startIntent(this, BankParcel(bank.name, bank.logoUrl)))
    }

}