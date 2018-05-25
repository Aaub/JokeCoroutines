package com.example.alexandre_aubry.jokecouroutines

class Contract{

    interface JokePresenterInterface {
        fun getJokeWithAsync()
        fun getJokeWithLaunchOnly()
    }

    interface MainViewInterface{
        fun setOneJokeText(text : String)
        fun setThreeTexts(texts : Map<Int, String>)
        fun setPresenter(presenter : JokePresenter)
    }
}