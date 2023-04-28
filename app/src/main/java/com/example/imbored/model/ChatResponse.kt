package com.example.imbored.model

import com.squareup.moshi.JsonClass
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList

/**
 * Data class that defines a cat photo, which includes an id,image URL, and sizing.
 */
//@JsonClass(generateAdapter = true)
//data class ChatResponse(
//    val id: String,
//    val objectR: String,
//    val created: Int,
//    val model: String,
//    val choices: Array<String>,
//    val usage: Array<String>
//    )

data class CompletionRequest(
    val prompt: String,
    val max_tokens: Int,
    val model: String
)

data class CompletionResponse(
    val choices: List<Choice>
)

data class Choice(
    val text: String
)