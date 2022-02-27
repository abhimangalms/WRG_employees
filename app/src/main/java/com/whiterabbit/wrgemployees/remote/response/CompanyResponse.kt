package com.whiterabbit.wrgemployees.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
class CompanyResponse(
    @SerializedName("name") val name: String?=null,
    @SerializedName("catchPhrase") val catchPhrase: String?=null,
    @SerializedName("bs") val bs: String?=null
) : Parcelable