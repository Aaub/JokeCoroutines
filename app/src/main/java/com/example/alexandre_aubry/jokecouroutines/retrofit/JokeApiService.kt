package com.example.alexandre_aubry.jokecouroutines.retrofit

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface JokeApiService {
    @GET("jokes/random")
    fun getRandomJoke() : Deferred<JokeApiResponse>
}