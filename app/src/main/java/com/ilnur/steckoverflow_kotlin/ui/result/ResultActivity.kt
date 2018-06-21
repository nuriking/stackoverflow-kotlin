package com.ilnur.steckoverflow_kotlin.ui.result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.ilnur.steckoverflow_kotlin.R
import com.ilnur.steckoverflow_kotlin.model.AnswerModel
import com.ilnur.steckoverflow_kotlin.ui.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : BaseActivity(), IResult {
    @InjectPresenter
    lateinit var mPresenter: ResultPresenter

    lateinit var mAnswer: AnswerModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Результат"
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
        }

        if (this.intent != null) {
            mAnswer = intent.getSerializableExtra("answer") as AnswerModel
            mPresenter.setAnswer(mAnswer, this)
        }

        addDeleteBtn.setOnClickListener {
            mPresenter.dbAddOrDelete()
        }

        urlTextView.setOnClickListener {
            mPresenter.clickSite()
        }
    }

    override fun fillLayout(title: String, site: String, created: String, ownerName: String,
                            imageUrl: String, ownerReputation: String) {
        titleTextView.text = title
        urlTextView.text = site
        createdTextView.text = created
        ownerNameTextView.text = ownerName
        if (!imageUrl.isEmpty()) {
            Picasso.with(this)
                    .load(imageUrl)
                    .into(ownerImageView)
        } else {
            ownerImageView.setVisibility(View.GONE)
        }
        ownerReputationTextView.text = ownerReputation
    }

    override fun lookSite(site: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlTextView.text.toString()))
        startActivity(browserIntent)
    }

    override fun onShowLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun isExist(title: String) {
        addDeleteBtn.text = title
    }

    override fun onBack() {
        setResult(RESULT_OK)
        finish()
    }

    override fun onError(errorText: String) {
        Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
    }
}
