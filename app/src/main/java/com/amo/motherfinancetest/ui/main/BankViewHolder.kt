package com.amo.motherfinancetest.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amo.motherfinancetest.R
import com.amo.motherfinancetest.model.Bank
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer

class BankViewHolder(
    private val view: View,
    val onClick: (Bank?) -> Unit
) : RecyclerView.ViewHolder(view), LayoutContainer {

    private var bank: Bank? = null
    private var bankName: TextView? = null
    private var bankLogo: ImageView? = null

    override val containerView: View?
        get() = view

    init {
        view.setOnClickListener {
            onClick.invoke(bank)
        }
        bankName = view.findViewById(R.id.bankName)
        bankLogo = view.findViewById(R.id.bankLogo)
    }

    fun bind(bank: Bank) {
        this.bank = bank
        bankName?.text = bank.name
        Picasso.get().load(bank.logoUrl).centerInside()
            .resize(256, 256)
            .onlyScaleDown()
            .into(bankLogo)
    }

    companion object {
        fun create(
            viewGroup: ViewGroup,
            onClick: (Bank?) -> Unit
        ): BankViewHolder {
            val view =
                LayoutInflater.from(viewGroup.context).inflate(R.layout.item_bank, viewGroup, false)
            return BankViewHolder(view, onClick)
        }
    }

}