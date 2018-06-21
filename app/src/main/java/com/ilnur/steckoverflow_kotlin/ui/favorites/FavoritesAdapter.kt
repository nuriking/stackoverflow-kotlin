package com.ilnur.steckoverflow_kotlin.ui.favorites

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilnur.steckoverflow_kotlin.R

import com.ilnur.steckoverflow_kotlin.model.AnswerModel
import kotlinx.android.synthetic.main.items_answer.view.*

class FavoritesAdapter(val action: onCurrentItemAction, private val context: Context, answers: List<AnswerModel>): RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {
    var answerList = answers

    fun setData(answers: List<AnswerModel>) {
        answerList = answers
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return answerList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.items_answer, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemsAnswerTitle.itemsAnswerTitle.text = answerList[position].title
        holder.scopeTextView.text = answerList[position].scope.toString()
        holder.countAnswerTextView.text = answerList[position].answerCount.toString()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val rootView = view.rootView!!
        val itemsAnswerTitle = view.itemsAnswerTitle!!
        val scopeTextView = view.scopeTextView!!
        val countAnswerTextView = view.countAnswerTextView!!

        init {
            this.rootView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = layoutPosition
            val answer = answerList.get(position)
            action.onItemClick(answer)
        }
    }

    interface onCurrentItemAction {
        fun onItemClick(answer: AnswerModel)
    }
}