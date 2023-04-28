package com.example.imbored.network

import com.example.imbored.model.CompletionRequest
import com.example.imbored.model.CompletionResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.*

private const val BASE_URL = "https://api.openai.com/v1/completions/"
// Accessing local properties to load my API key (This will be hard coded in project when it is turned in, but removed once submitted)
//private val localProperties = Properties()
//private val API_KEY = localProperties["API_KEY"]
private const val API_KEY = "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"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SimpleService {
    // Using Header annotation to add authorization token in the body of the Post (JSON format)
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer $API_KEY"
    )
    // Using POST annotation to post the request content
    @POST("/v1/completions")
    fun getCompletion(
        @Body requestBody: CompletionRequest
    ): Call<CompletionResponse>
}

object API {
    val retrofitService: SimpleService by lazy {
        retrofit.create(SimpleService::class.java)
    }
}
