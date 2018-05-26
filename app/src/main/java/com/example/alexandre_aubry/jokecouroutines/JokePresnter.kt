package com.example.alexandre_aubry.jokecouroutines

import com.example.alexandre_aubry.jokecouroutines.retrofit.JokeApiResponse
import com.example.alexandre_aubry.jokecouroutines.retrofit.JokeApiServiceFactory
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlin.coroutines.experimental.CoroutineContext


class JokePresenter(view: MainActivity) : Contract.JokePresenterInterface {
    val TAG = "JOKE_PRESENTER"
    private val mUiContext: CoroutineContext = UI
    private val mBgContext: CoroutineContext = CommonPool
    var mView: MainActivity = view
    var mChannel = Channel<Joke>()
    private var mUserWantToReceive = true

    private val jokeApiService by lazy {
        JokeApiServiceFactory.createService()
    }

    private lateinit var mJoke: Joke


    init {
        mView.setPresenter(this)
    }

    override fun getJokeWithLaunchOnly() {
        launch(mUiContext) {
            val jokeStr = getJokeFromApi().await().joke.jokeText
            val map = mapOf(Pair(1, jokeStr))
            mView.setThreeTexts(map)
        }

    }

    override fun getJokeWithAsync() {
        launch(mUiContext) {
            val jokeStr = jokeApiService.getRandomJoke().await().joke.jokeText
            mView.setOneJokeText(jokeStr)
        }
    }
    private fun getJokeFromApi(): Deferred<JokeApiResponse> {
        return async { jokeApiService.getRandomJoke().await() }
    }

    override fun getAsyncJokes() {
        mUserWantToReceive = true
        getAsyncJokesFromApi()
        launch(mUiContext) {
            mChannel.consumeEach {
                val map = mapOf(Pair(1, mChannel.receive().jokeText))
                mView.setThreeTexts(map)
            }
        }
    }

    fun getAsyncJokesFromApi() {
        launch(mBgContext) {
            while (mUserWantToReceive) {
                mChannel.send(jokeApiService.getRandomJoke().await().joke)
                delay(1000)
            }
        }
    }

    override fun stopReception() {
        mUserWantToReceive = false
    }
}