package com.whiterabbit.wrgemployees.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class EmployeeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "employee_name")
    var name: String?,

    @ColumnInfo(name = "username")
    var username: String?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "profile_image")
    var profile_image: String?,

@Embedded

    var address: Address?,

    @ColumnInfo(name = "phone")
    var phone: String?,

    @ColumnInfo(name = "website")
    var website: String?,

    @Embedded
    var company: Company?,


    ) {
/*
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null*/

}


