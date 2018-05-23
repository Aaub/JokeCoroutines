package com.example.alexandre_aubry.jokecouroutines

import com.google.gson.annotations.SerializedName

data class Joke (
    @SerializedName("id") val jokeId : Int = -1,
    @SerializedName("joke") val jokeText : String = "NOT A JOKE")