package github.sachin2dehury.recipeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus
import retrofit2.Response

fun <T : Any> Response<T>.isSuccess() = isSuccessful && code() == 200 && body() != null

val ViewModel.viewModelIoScope: CoroutineScope
    get() = viewModelScope + Dispatchers.IO
