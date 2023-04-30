package com.example.imbored

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imbored.model.CompletionRequest
import com.example.imbored.network.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class HomeViewModel : ViewModel() {
    // Variable to hold the Completion answer, as a String
    private val _answerString = MutableLiveData<String>()
    var answerString: LiveData<String> = _answerString
    private var previousResponses: List<String> = listOf("")

    // Function to get a text completion from OpenAI completion model (text-davinci-003)
    fun getCompletion(input: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val request = CompletionRequest(
                prompt = input + previousResponses,
                model = "text-davinci-003",
                max_tokens = 500,
                frequency_penalty = 2,
                temperature = 1,
                top_p = 1
            )
            try {
                // Make a call Object to hold the API request
                val call = API.retrofitService.getCompletion(request)
                // Execute a Retrofit call
                val response = call.execute()
                if (response.isSuccessful) {
                    // Run the following on the Main thread
                    withContext (Dispatchers.Main) {
                        val parsedResponse = response.body()?.choices?.get(0)?.text?.trim().toString()
                        // Update the Live Data with API response
                        _answerString.value = parsedResponse
                        // update previous responses so they don't repeat
                        previousResponses = previousResponses + parsedResponse.toString()
                        // TODO remove line below, only used for debugging purposes
//                        answerString.value?.let { Log.d("HomeViewModel",
//                            "$it\nParsed response: $parsedResponse"
//                        ) }
                    }
                }
            } catch (e: IOException) {
                Log.d("HomeViewModel", "API fail")
            }
        }
        /**
         * TODO: If the "I did it!" (Camera) button is clicked, store the last String in previous result as a
         * data object, to be coupled with a photo and stored in Room database
         */

    }
    
}
