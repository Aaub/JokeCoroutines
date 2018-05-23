package com.example.alexandre_aubry.jokecouroutines

import com.example.alexandre_aubry.jokecouroutines.retrofit.JokeApiServiceFactory


class JokePresenter(view: MainActivity) : Contract.JokePresenterInterface {
    val TAG = "JOKE_PRESENTER"
    var  mViewInterface : MainActivity = view
    private val jokeApiService by lazy {
        JokeApiServiceFactory.createService()
    }

    var joke = Joke()


    init {
        mViewInterface.setPresenter(this)
    }

    override fun start() {

    }

    override fun getJoke(){
//       runBlocking {
//           async {
//               var deferred = jokeApiService.getRandomJoke().await()
//               Log.w(TAG, "deferred : $deferred")
//           joke = deferred.joke
//           Log.w(TAG, "joke : $joke")
//            val jokeStr = joke.jokeText
//           Log.w(TAG, "joke text : $joke")
//            mViewInterface.setOneJokeText(jokeStr)
//           }
//        }
    }

   suspend fun getAsyncJokes(): List<Joke>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}