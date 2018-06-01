package com.example.alexandre_aubry.jokecouroutines

import android.util.Log
import android.widget.Toast
import com.example.alexandre_aubry.jokecouroutines.retrofit.JokeApiResponse
import com.example.alexandre_aubry.jokecouroutines.retrofit.JokeApiServiceFactory
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.consumeEach
import java.io.IOException
import kotlin.coroutines.experimental.CoroutineContext


class JokePresenter(view: MainActivity) : Contract.JokePresenterInterface {
    val TAG = "JOKE_PRESENTER"
    private val mUiContext: CoroutineContext = UI
    private val mBgContext: CoroutineContext = newSingleThreadContext("MyOwnThread")
    private var mView: MainActivity = view
    private var mChannel = Channel<Pair<Int, Joke>>(5)
    private var mUserWantToReceive = true

    private val mJokeApiService by lazy {
        JokeApiServiceFactory.createService()
    }


    init {
        mView.setPresenter(this)
    }

    override fun getJokeWithLaunchOnly() {
        launch(mUiContext) {
            try {
                val jokeStr = getJokeFromApi().await().joke.jokeText
                mView.setOneJokeText(jokeStr)
            } catch (iOE: IOException) {
                Toast.makeText(mView.applicationContext, "Could not fetch a joke", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getJokeFromApi(): Deferred<JokeApiResponse> {
        return async { mJokeApiService.getRandomJoke().await() }
    }

    override fun getJokeWithAsync() {
        launch(mUiContext) {
            try {
                val jokeStr = mJokeApiService.getRandomJoke().await().joke.jokeText
                mView.setOneJokeText(jokeStr)
            } catch (iOE: IOException) {
                Toast.makeText(mView.applicationContext, "Could not fetch a joke", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getAsyncJokes() {
        mUserWantToReceive = true
        getAsyncJokesFromApi()
        launch(mUiContext) {
            mChannel.consumeEach {
                val pair = mChannel.receive()
                val key = pair.first
                Log.w(TAG, "KEY OF THE JOKE : $key")
                val joke = pair.second.jokeText
                mView.setThreeTexts(Pair(key, joke))
            }
        }
    }

    fun getAsyncJokesFromApi() {
        launch(mBgContext) {
            var key = 1
            while (mUserWantToReceive) {
                if (key > 2) {
                    key = 0
                }
                val joke = mJokeApiService.getRandomJoke().await().joke
                val pair = Pair(key, joke)
                mChannel.send(pair)
                key++

                delay(700)
            }
        }
    }

    override fun stopReception() {
        mUserWantToReceive = false
        /* mChannel.close() or *  mChannel.cancel()
        *       Work but close the channel which mean we can't use it anymore
        * */
    }
}