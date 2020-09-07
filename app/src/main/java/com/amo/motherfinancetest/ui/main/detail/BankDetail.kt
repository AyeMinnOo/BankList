package com.amo.motherfinancetest.ui.main.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.amo.motherfinancetest.R
import com.amo.motherfinancetest.model.BankParcel
import com.amo.motherfinancetest.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_bank_detail.*

class BankDetail : BaseActivity() {

    companion object {

        const val PARCEL_BANK = "PARCEL_BANK"

        fun startIntent(context: Context, parcel: BankParcel): Intent {
            val intent = Intent(context, BankDetail::class.java)
            intent.putExtra(PARCEL_BANK, parcel)
            return intent
        }
    }

    override val layoutId: Int
        get() = R.layout.activity_bank_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bank = intent.getParcelableExtra<BankParcel>(PARCEL_BANK)
        textView.text = bank?.name
    }

}