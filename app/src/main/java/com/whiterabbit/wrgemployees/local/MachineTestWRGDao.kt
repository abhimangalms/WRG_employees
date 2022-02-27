package com.whiterabbit.wrgemployees.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whiterabbit.wrgemployees.local.entity.EmployeeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MachineTestWRGDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: List<EmployeeEntity>)

    @Query("SELECT * FROM employee_table")
    fun getEmployeeList(): Flow<List<EmployeeEntity>>

    @Query("SELECT * FROM employee_table WHERE employee_name LIKE :searchQuery OR email LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<EmployeeEntity>>
}
