package com.example.imbored.model

/**
 * Data class that defines a text-completion for OpenAI completion models
 */

data class CompletionRequest(
    val prompt: String,
    val model: String,
    // How many tokens to maximally produce
    val max_tokens: Int,
    // How much to penalize reused word in response
    val frequency_penalty: Int,
    // Control the randomness (0-1, 1 most random)
    val temperature: Int,
    // Generate multiple responses and chose the best
    val best_of: Int,
    // Control diversity of answers based on what percentage of next tokens to consider
    val top_p: Int
)

data class CompletionResponse(
    val choices: List<Choice>
)

data class Choice(
    val text: String
)