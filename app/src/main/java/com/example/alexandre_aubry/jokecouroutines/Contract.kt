package com.example.alexandre_aubry.jokecouroutines

class Contract{

    interface JokePresenterInterface {
        fun getJoke()
    }

    interface MainViewInterface{
        fun setOneJokeText(text : String)
        fun setThreeTexts(texts : Map<Int, String>)
        fun setPresenter(presenter : JokePresenter)
    }
}