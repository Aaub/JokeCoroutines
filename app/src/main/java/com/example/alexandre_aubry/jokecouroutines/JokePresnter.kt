package com.example.alexandre_aubry.jokecouroutines

import com.example.alexandre_aubry.jokecouroutines.retrofit.JokeApiServiceFactory
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext


class JokePresenter(view: MainActivity) : Contract.JokePresenterInterface {
    val TAG = "JOKE_PRESENTER"
    private val mUiContext: CoroutineContext = UI
    private val mBgContext: CoroutineContext = CommonPool
    var  mViewInterface : MainActivity = view
    private val jokeApiService by lazy {
        JokeApiServiceFactory.createService()
    }

    var joke = Joke(-1, "Not a real joke")


    init {
        mViewInterface.setPresenter(this)
    }

    override fun getJoke(){
        launch(mUiContext) {
            var deferred = jokeApiService.getRandomJoke().await()
//            Log.w(TAG, "deferred : $deferred")
            joke = deferred.joke
//           Log.w(TAG, "joke : $joke")
            val jokeStr = joke.jokeText
//           Log.w(TAG, "joke text : $joke")
            mViewInterface.setOneJokeText(jokeStr)
        }
    }

   suspend fun getAsyncJokes(): List<Joke>? {
       return null
    }

}