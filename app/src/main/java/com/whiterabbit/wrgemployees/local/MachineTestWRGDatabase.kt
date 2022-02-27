package com.whiterabbit.wrgemployees.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whiterabbit.wrgemployees.local.entity.EmployeeEntity

@Database(
    entities = [EmployeeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MachineTestWRGDatabase : RoomDatabase() {
    abstract fun machineTestDao(): MachineTestWRGDao
}
