package com.example.imbored

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imbored.model.CompletionRequest
import com.example.imbored.model.CompletionResponse
import com.example.imbored.network.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import java.io.IOException

class CompletionViewModel : ViewModel() {
    private val _answer = MutableLiveData<Call<CompletionResponse>>()
    val answer: LiveData<Call<CompletionResponse>>  = _answer

    fun getCompletion(input: String): String {
        // Hold the desired response String
        val choice: String = ""
        viewModelScope.launch(Dispatchers.IO) {
            val request = CompletionRequest(
                prompt = input,
                max_tokens = 100,
                model = "text-davinci-003"
            )
            try {
                val call = API.retrofitService.getCompletion(request)
                val response = call.execute()
                if (response.isSuccessful) {
                    val choice = response.body()?.choices?.get(0)
                    Log.d("CatAppViewModel", "choice")
                    // set the idea in the View Model
//                    viewModelScope.launch(Dispatchers.Main) {
//                        activityIdea?.text?.let{
//                            idea = it
//                        }
//                    }
                }
                _answer.value = API.retrofitService.getCompletion(request)
            } catch (e: IOException) {
                Log.d("CatAppViewModel", "API fail")
//            viewModelScope.launch(Dispatchers.Main) {
//                answer = "Error: ${response.code()} - ${response.message()}"
//            }
            }
        }
        return choice
    }

}