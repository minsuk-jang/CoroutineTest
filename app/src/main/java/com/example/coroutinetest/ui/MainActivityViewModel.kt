package com.example.coroutinetest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinetest.model.Response
import com.example.coroutinetest.usecase.GetQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getQueryUseCase: GetQueryUseCase
) : ViewModel() {
    private val _response = MutableStateFlow(Response(meta = null, document = null))
    val response: StateFlow<Response> = _response.asStateFlow()

    private val dispatcher = Dispatchers.Default.limitedParallelism(1)

    fun getQuery(query: String) {
        viewModelScope.launch {
            val time = measureTimeMillis {
                val jobs = List(3) {
                    launch(dispatcher) {
                        getQueryUseCase(query = query)
                        delay(1000L)
                        Timber.e("Context: $coroutineContext")
                    }
                }

                jobs.joinAll()
            }

            Timber.e("Time: $time")
        }
    }
}