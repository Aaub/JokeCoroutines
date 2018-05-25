package com.example.alexandre_aubry.jokecouroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), Contract.MainViewInterface {

    private var mPresenter = JokePresenter(this)
    private lateinit var mOneJokeTextView: TextView
    private lateinit var mThreeJokesFirstTextView: TextView
    private lateinit var mThreeJokesSecondTextView: TextView
    private lateinit var mThreeJokesThirdTextView: TextView
    private lateinit var mOneJokeButton: Button
    private lateinit var mThreeJokesButton: Button
    private val TAG = "MAIN_ACTIVITY"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    fun setupViews() {
        mOneJokeTextView = findViewById(R.id.one_joke_text_field)
        mThreeJokesFirstTextView = findViewById(R.id.three_jokes_text_field_first)
        mThreeJokesSecondTextView = findViewById(R.id.three_jokes_text_field_second)
        mThreeJokesThirdTextView = findViewById(R.id.three_jokes_text_field_third)

        mOneJokeButton = findViewById(R.id.one_joke_button)
        mThreeJokesButton = findViewById(R.id.three_joke_button)
        setupOnClickListeners()
    }

    fun setupOnClickListeners() {
        mOneJokeButton.setOnClickListener { onClickOneJoke() }
        mThreeJokesButton.setOnClickListener { onClickThreeJokes() }
    }

    fun onClickOneJoke() {
        mPresenter.getJokeWithAsync()

    }

    fun onClickThreeJokes() {
        mPresenter.getJokeWithLaunchOnly()
    }

    override fun setOneJokeText(text: String) {
        mOneJokeTextView.text = text
    }

    override fun setThreeTexts(texts: Map<Int, String>) {
        for (key in texts.keys) {
            when (key) {
                0 -> mThreeJokesFirstTextView.text = texts[key]
                1 -> mThreeJokesSecondTextView.text = texts[key]
                2 -> mThreeJokesThirdTextView.text = texts[key]
            }
        }

    }

    override fun setPresenter(presenter: JokePresenter) {
        mPresenter = presenter
    }
}
