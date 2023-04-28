package com.example.imbored.ui.home

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imbored.R
import com.example.imbored.model.CompletionRequest
import com.example.imbored.model.CompletionResponse
import com.example.imbored.network.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.io.IOException

class HomeViewModel : ViewModel() {

    // Variable to hold the Completion answer, as a String
    private val _answerString = MutableLiveData<String>()
    var answerString: LiveData<String> = _answerString

    // Function to get a text completion from OpenAI completion model (text-davinci-003)
    fun getCompletion(input: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val request = CompletionRequest(
                prompt = input,
                model = "text-davinci-003",
                max_tokens = 20,
                frequency_penalty = 2,
                temperature = 1,
                best_of = 1,
                top_p = 1,
            )
            try {
                val call = API.retrofitService.getCompletion(request)
                val response = call.execute()
                // set the live data if the response is successful
                if (response.isSuccessful) {
                    withContext (Dispatchers.Main) {
                        //update the UI once the call is executed
                        _answerString.value = response.body()?.choices?.get(0)?.text?.trim().toString()
                        answerString.value?.let { Log.d("HomeViewModel", it) }
                    }
                }
            } catch (e: IOException) {
                Log.d("HomeViewModel", "API fail")
            }
        }
    }
}