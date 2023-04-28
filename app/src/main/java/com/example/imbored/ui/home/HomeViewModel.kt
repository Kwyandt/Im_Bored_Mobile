package com.example.imbored.ui.home

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
import java.io.IOException

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    // My edits
    private val _answer = MutableLiveData<Call<CompletionResponse>>()
    val answer: LiveData<Call<CompletionResponse>>  = _answer


    // Variable to hold the answer as a String, not editing actual data so mutable
    var answerString: String = getCompletion("Say hi!")

    // Function to get a text completion from OpenAI completion model (text-davinci-003)
    fun getCompletion(input: String): String {
        // Hold the desired response String
//        var completion: String = ""
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
                    answerString = response.body()?.choices?.get(0).toString()
                    Log.d("CatAppViewModel", answerString)

                    // set the idea in the View Model
//                    viewModelScope.launch(Dispatchers.Main) {
//                        activityIdea?.text?.let{
//                            idea = it
//                        }
//                    }
                }
            } catch (e: IOException) {
                Log.d("CatAppViewModel", "API fail")

            }
        }
        return answerString
    }
}