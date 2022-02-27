package com.whiterabbit.wrgemployees.local.entity

import androidx.room.ColumnInfo

data class Company(
    @ColumnInfo(name = "company_name")
    var name: String?,
    @ColumnInfo(name = "catchPhrase")
    var catchPhrase: String?,
    @ColumnInfo(name = "bs")
    var bs: String?
) {
}

