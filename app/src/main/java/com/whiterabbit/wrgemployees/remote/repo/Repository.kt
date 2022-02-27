package com.whiterabbit.wrgemployees.remote.repo


import com.whiterabbit.wrgemployees.remote.Api
import com.whiterabbit.wrgemployees.local.MachineTestWRGDao
import com.whiterabbit.wrgemployees.local.entity.EmployeeEntity
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow


@Singleton
class Repository @Inject constructor(private val api: Api,
                                     private val dao: MachineTestWRGDao
) {

    fun fetchData() = api.getData()

    fun searchDatabase(searchQuery: String) : Flow<List<EmployeeEntity>>{
        return dao.searchDatabase(searchQuery)
    }

    suspend fun insertData(data: List<EmployeeEntity>) {
        return dao.insertData(data)
    }

    fun getEmployeeList() = dao.getEmployeeList()
}
