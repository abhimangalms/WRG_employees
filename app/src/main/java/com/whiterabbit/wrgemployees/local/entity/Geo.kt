package com.whiterabbit.wrgemployees.local.entity

import androidx.room.ColumnInfo

data class Geo(
    @ColumnInfo(name = "lat")
    var lat: String?,
    @ColumnInfo(name = "lng")
    var lng: String?,
) {
}

