package com.picpay.desafio.android.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ResultRepositoryModel<T>(
    var isSuccess: Boolean = true,
    var data: @RawValue T,
    var messageError: String = ""
) : Parcelable