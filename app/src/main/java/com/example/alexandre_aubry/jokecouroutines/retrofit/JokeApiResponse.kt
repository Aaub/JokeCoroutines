package com.example.alexandre_aubry.jokecouroutines.retrofit
import com.example.alexandre_aubry.jokecouroutines.Joke
import com.google.gson.annotations.SerializedName
data class JokeApiResponse(
        @SerializedName("type") val type: String,
        @SerializedName("value") val joke: Joke)