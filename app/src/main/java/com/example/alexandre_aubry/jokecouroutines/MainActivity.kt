package com.example.alexandre_aubry.jokecouroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Contract.MainViewInterface {

    private var mPresenter = JokePresenter(this)
    private val TAG = "MAIN_ACTIVITY"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        one_joke_button.setOnClickListener { onClickOneJoke() }
        three_joke_button.setOnClickListener { onClickThreeJokes() }
        stop_reception.setOnClickListener { onClickStopReception() }
    }

    private fun onClickOneJoke() {
        // one_joke_progress_bar.setProgress(10, true)
        mPresenter.getJokeWithAsync()

    }

    private fun onClickThreeJokes() {
        mPresenter.getAsyncJokes()
    }

    private fun onClickStopReception() {
        mPresenter.stopReception()
    }

    override fun showToast() {
        Toast.makeText(applicationContext, "Could not fetch a joke", Toast.LENGTH_SHORT).show()
    }

    override fun setOneJokeText(text: String) {
        one_joke_text_field.text = text
    }

    override fun setThreeTexts(jokePair: Pair<Int, String>) {
        when (jokePair.first) {
            0 -> three_jokes_text_field_first
            1 -> three_jokes_text_field_second
            2 -> three_jokes_text_field_third
            else -> three_jokes_text_field_third
        }.text = jokePair.second

    }

    override fun setPresenter(presenter: JokePresenter) {
        mPresenter = presenter
    }
}
