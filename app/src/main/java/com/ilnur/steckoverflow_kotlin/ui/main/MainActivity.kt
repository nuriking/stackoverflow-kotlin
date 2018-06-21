package com.ilnur.steckoverflow_kotlin.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.ilnur.steckoverflow_kotlin.Constant.Companion.RESULT_MAIN
import com.ilnur.steckoverflow_kotlin.R
import com.ilnur.steckoverflow_kotlin.model.AnswerModel
import com.ilnur.steckoverflow_kotlin.ui.BaseActivity
import com.ilnur.steckoverflow_kotlin.ui.favorites.FavoritesActivity
import com.ilnur.steckoverflow_kotlin.ui.result.ResultActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseActivity(), IMain, MainAdapter.onCurrentItemAction {
    @InjectPresenter
    lateinit var presenter: MainPresenter

    private var adapter: MainAdapter? = null

    @Suppress("UNUSED_EXPRESSION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        searchEditText.setOnKeyListener({ _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_SEARCH) {
                presenter.getAnswerModels(searchEditText.text.toString())
                true
            }
            false
        })

        favoritesButton.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
    }

    override fun onError(errorText: String) {
        Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
    }

    override fun onShowLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun fillLayout(answers: List<AnswerModel>) {
        if(adapter == null) {
            requestsRecView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            adapter = MainAdapter(this, this, ArrayList())
            requestsRecView.adapter = adapter
        }
        adapter!!.setData(answers)
    }

    override fun onItemClick(answer: AnswerModel) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("answer",answer)
        startActivityForResult(intent, RESULT_MAIN)
    }

    override fun setNumberCountFavorite(count: String) {
        favoritesButton.text = count
    }

    override fun hideKeyboard() {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = this.currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
