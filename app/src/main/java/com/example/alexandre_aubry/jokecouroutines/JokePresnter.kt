package com.example.alexandre_aubry.jokecouroutines

import com.example.alexandre_aubry.jokecouroutines.retrofit.JokeApiResponse
import com.example.alexandre_aubry.jokecouroutines.retrofit.JokeApiServiceFactory
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext


class JokePresenter(view: MainActivity) : Contract.JokePresenterInterface {
    val TAG = "JOKE_PRESENTER"
    private val mUiContext: CoroutineContext = UI
    private val mBgContext: CoroutineContext = CommonPool
    var mView: MainActivity = view

    private val jokeApiService by lazy {
        JokeApiServiceFactory.createService()
    }

    private lateinit var mJoke: Joke


    init {
        mView.setPresenter(this)
    }

    override fun getJokeWithLaunchOnly() {
        launch(mUiContext) {
            val jokeStr = jokeApiService.getRandomJoke().await().joke.jokeText
            mView.setOneJokeText(jokeStr)
        }
    }

    override fun getJokeWithAsync() {
        launch(mUiContext) {
            val jokeStr = getJokeFromApi().await().joke.jokeText
            val map = mapOf(Pair(1, jokeStr))
            mView.setThreeTexts(map)
        }
    }

    private fun getJokeFromApi(): Deferred<JokeApiResponse> {
        return async { jokeApiService.getRandomJoke().await() }
    }

   suspend fun getAsyncJokes(): List<Joke>? {
       return null
    }


}