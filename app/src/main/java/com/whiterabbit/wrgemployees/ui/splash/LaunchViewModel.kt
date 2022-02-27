package com.whiterabbit.wrgemployees.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whiterabbit.wrgemployees.local.entity.Address
import com.whiterabbit.wrgemployees.local.entity.Company
import com.whiterabbit.wrgemployees.local.entity.EmployeeEntity
import com.whiterabbit.wrgemployees.local.entity.Geo
import com.whiterabbit.wrgemployees.remote.repo.Repository
import com.whiterabbit.wrgemployees.remote.response.EmployeeResponse
import com.whiterabbit.wrgemployees.utils.network.ApiEmptyResponse
import com.whiterabbit.wrgemployees.utils.network.ApiErrorResponse
import com.whiterabbit.wrgemployees.utils.network.ApiSuccessResponse
import com.whiterabbit.wrgemployees.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _fetchDataApiState =
        MutableStateFlow<Resource<EmployeeResponse?>>(Resource.Idle())
    val fetchDataApiState = _fetchDataApiState.asStateFlow()
    private val _navigateState = MutableStateFlow(false)
    val navigateState = _navigateState.asStateFlow()


    fun fetchEmployeeData() {
        viewModelScope.launch {
            repository.fetchData()
                .onStart { _fetchDataApiState.value = Resource.Loading() }
                .collect { response ->
                    when (response) {
                                is ApiSuccessResponse -> {
                            val employeelist = response.data?.mapIndexed { index, employee ->

                                _navigateState.value = true
                                val geo = Geo(
                                    employee.address?.geo?.lat?.toString(),
                                    employee.address?.geo?.lng?.toString()
                                )
                                val address= Address(
                                    employee.address?.street,
                                    employee.address?.suite,
                                    employee.address?.city,
                                    employee.address?.zipcode,
                                    geo,
                                )

                                val company= Company(
                                    employee.company?.name,
                                    employee.company?.catchPhrase,
                                    employee.company?.bs
                                )
                                EmployeeEntity(
                                    employee.id,
                                    employee.name,
                                    employee.username,
                                    employee.email,
                                    employee.profile_image,
                                    address,
                                    employee.phone,
                                    employee.website,
                                    company,
                                )
                            }

                            insertData_DB(employeelist)



                        }
                        is ApiErrorResponse -> {
                            if (repository.getEmployeeList().first().isNullOrEmpty()) {
                                _navigateState.value = false
                                _fetchDataApiState.value = Resource.Error("Network error")
                            } else {
                                _navigateState.value = true
                            }
                        }
                        is ApiEmptyResponse -> {
                            _navigateState.value = false
                            _fetchDataApiState.value =
                                Resource.Error("A null response has been received")
                        }
                    }
                }
        }
    }

    private fun insertData_DB(data: List<EmployeeEntity>?) {
        data?.let {
            viewModelScope.launch {
                repository.insertData(it)
            }
        }
    }
}
