package com.whiterabbit.wrgemployees.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
class AddressResponse(
    @SerializedName("street") val street : String?=null,
    @SerializedName("suite") val suite : String?=null,
    @SerializedName("city") val city : String?=null,
    @SerializedName("zipcode") val zipcode : String?=null,
    @SerializedName("geo") val geo : GeoResponse

) : Parcelable