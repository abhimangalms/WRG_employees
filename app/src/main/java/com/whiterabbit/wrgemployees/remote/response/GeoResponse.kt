package com.whiterabbit.wrgemployees.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
class GeoResponse(
    @SerializedName("lat") val lat : Double?=null,
    @SerializedName("lng") val lng : Double?=null

): Parcelable