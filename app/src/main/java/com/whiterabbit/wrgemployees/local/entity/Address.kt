package com.whiterabbit.wrgemployees.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class Address(

    @ColumnInfo(name = "street")
    var street: String?,

    @ColumnInfo(name = "suite")
    var suite: String?,

    @ColumnInfo(name = "city")
    var city: String?,

    @ColumnInfo(name = "zipcode")
    var zipcode: String?,
    @Embedded

    var geo: Geo?
) {
}

