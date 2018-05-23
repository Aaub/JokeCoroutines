package com.example.alexandre_aubry.jokecouroutines.retrofit
import com.example.alexandre_aubry.jokecouroutines.Joke
import com.google.gson.annotations.SerializedName
data class JokeApiResponse(
        @SerializedName("id") val jokeId: Int,
        @SerializedName("joke") val joke : Joke = Joke())