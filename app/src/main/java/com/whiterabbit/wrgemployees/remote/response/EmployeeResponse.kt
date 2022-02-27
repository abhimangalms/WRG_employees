package com.whiterabbit.wrgemployees.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
class EmployeeResponse (
    @SerializedName("id") val id : Int?=null,
    @SerializedName("name") val name : String?=null,
    @SerializedName("username") val username : String?=null,
    @SerializedName("email") val email : String?=null,
    @SerializedName("profile_image") val profile_image : String?=null,
    @SerializedName("address") val address : AddressResponse?=null,
    @SerializedName("phone") val phone : String?=null,
    @SerializedName("website") val website : String?=null,
    @SerializedName("company") val company : CompanyResponse?=null
): Parcelable