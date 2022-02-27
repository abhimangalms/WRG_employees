package com.whiterabbit.wrgemployees.utils.network.calladapters

import com.whiterabbit.wrgemployees.utils.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        return when (getRawType(returnType)) {

            Flow::class.java -> {
                val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
                require(observableType is ParameterizedType) {
                    "Resource must be parameterized"
                }

                val rawObservableType = getRawType(observableType)
                require(rawObservableType == ApiResponse::class.java) {
                    "Type must be a resource"
                }

                val responseType = getParameterUpperBound(0, observableType)
                FlowCallAdapter(responseType)
            }

            else -> null
        }
    }
}
