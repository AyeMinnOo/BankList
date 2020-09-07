package com.amo.motherfinancetest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BankParcel(val name: String, val logoUrl: String) : Parcelable