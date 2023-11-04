package com.example.dogaplication.entities

data class BreedsResponse(
    val message: Map<String , List<String>>,
    val status: String
)
