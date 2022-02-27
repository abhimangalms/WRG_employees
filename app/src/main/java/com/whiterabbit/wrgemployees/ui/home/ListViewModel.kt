package com.whiterabbit.wrgemployees.ui.home

import android.app.Person
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.whiterabbit.wrgemployees.local.entity.EmployeeEntity

import dagger.hilt.android.lifecycle.HiltViewModel
import com.whiterabbit.wrgemployees.remote.repo.Repository
import com.whiterabbit.wrgemployees.remote.response.AddressResponse
import com.whiterabbit.wrgemployees.remote.response.CompanyResponse
import com.whiterabbit.wrgemployees.remote.response.EmployeeResponse
import com.whiterabbit.wrgemployees.remote.response.GeoResponse
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: Repository)
    : ViewModel() {


    private val _categoryList = MutableStateFlow<List<EmployeeResponse>>(listOf())
    val categoryList = _categoryList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getEmployeeList().collect {
                val mappedList = it.map { employee ->

                    val geoResponse:GeoResponse= GeoResponse(
                        employee.address?.geo?.lat?.toDouble(),
                        employee.address?.geo?.lng?.toDouble(),
                    )
                   val addressResponse= AddressResponse(
                       employee.address?.street,
                       employee.address?.suite,
                       employee.address?.city,
                       employee.address?.zipcode,
                       geoResponse,
                   )

                    val companyResponse=CompanyResponse(
                        employee.company?.name,
                        employee.company?.catchPhrase,
                        employee.company?.bs
                    )
                    EmployeeResponse(
                       employee.id,
                        employee.name,
                        employee.username,
                        employee.email,
                        employee.profile_image,
                        addressResponse,
                        employee.phone,
                        employee.website,
                        companyResponse

                    )
                }

                _categoryList.value = mappedList
            }
        }
    }

    fun searchDatabase(serchQuery: String): LiveData<List<EmployeeEntity>>{
        return repository
            .searchDatabase(serchQuery).asLiveData()
    }

}
