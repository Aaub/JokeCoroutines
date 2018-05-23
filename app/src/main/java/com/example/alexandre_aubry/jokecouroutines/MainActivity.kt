package com.example.alexandre_aubry.jokecouroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), Contract.MainViewInterface {

     private var mPresenter = JokePresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.one_joke_button).setOnClickListener { onClickOneJoke() }
    }

    fun onClickOneJoke() {
      mPresenter.getJoke()
    }

    fun onClickThreeJokes(view : View){
//      mPresenter.getAsyncJokes()
    }

    override fun setOneJokeText(text: String) {
        findViewById<TextView>(R.id.one_text_field).text = text
    }

    override fun setThreeTexts(texts: Map<Int, String>) {
        for (key in texts.keys) {
            when (key) {
                0 -> findViewById<TextView>(R.id.three_text_field_first).text = texts[key]
                1 -> findViewById<TextView>(R.id.three_text_field_second).text = texts[key]
                2 -> findViewById<TextView>(R.id.three_text_field_third).text = texts[key]
            }
        }

    }

    override fun setPresenter(presenter: JokePresenter) {
        mPresenter = presenter
    }
}
