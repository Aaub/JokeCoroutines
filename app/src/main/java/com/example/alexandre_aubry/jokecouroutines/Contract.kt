package com.example.alexandre_aubry.jokecouroutines

class Contract{

    interface JokePresenterInterface {
        fun getJokeWithAsync()
        fun getJokeWithLaunchOnly()
        fun getAsyncJokes()
        fun stopReception()
    }

    interface MainViewInterface{
        fun setOneJokeText(text : String)
        fun setThreeTexts(texts : Map<Int, String>)
        fun setPresenter(presenter : JokePresenter)
    }
}