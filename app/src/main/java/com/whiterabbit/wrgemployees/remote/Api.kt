package com.whiterabbit.wrgemployees.remote

import com.whiterabbit.wrgemployees.remote.response.EmployeeResponse
import com.whiterabbit.wrgemployees.utils.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface Api {
    @GET("v2/5d565297300000680030a986")
    fun getData(
    ): Flow<ApiResponse<List<EmployeeResponse>>>
}
