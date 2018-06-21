package com.ilnur.steckoverflow_kotlin.ui.favorites

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.LinearLayout
import com.arellomobile.mvp.presenter.InjectPresenter
import com.ilnur.steckoverflow_kotlin.R
import com.ilnur.steckoverflow_kotlin.model.AnswerModel
import com.ilnur.steckoverflow_kotlin.ui.BaseActivity
import com.ilnur.steckoverflow_kotlin.ui.result.ResultActivity
import java.util.ArrayList
import android.view.View
import android.widget.Toast
import com.ilnur.steckoverflow_kotlin.Constant.Companion.RESULT_FAVORITES
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : BaseActivity(), IFavorites, FavoritesAdapter.onCurrentItemAction {
    @InjectPresenter
    lateinit var presenter: FavoritesPresenter

    private var adapter: FavoritesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Список избранных"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onShowLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun fillFavorites(answers: List<AnswerModel>) {
        if (adapter == null) {
            favoritesRecView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            adapter = FavoritesAdapter(this, this, ArrayList())
            favoritesRecView.adapter = adapter
        }
        adapter!!.setData(answers)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onError(errorText: String) {
        Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(answer: AnswerModel) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("answer", answer)
        startActivityForResult(intent, RESULT_FAVORITES)
    }
}