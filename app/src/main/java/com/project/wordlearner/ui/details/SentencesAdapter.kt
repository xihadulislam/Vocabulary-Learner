package com.project.wordlearner.ui.details


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.wordlearner.R
import com.project.wordlearner.common.setHtmlText

class SentencesAdapter() :
    RecyclerView.Adapter<SentencesAdapter.MyViewHolder>() {

    private var mList: MutableList<String> = mutableListOf()

    fun setUpdatedList(list: List<String>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sentence, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(mList[position])
    }

    override fun getItemCount(): Int {
        return if (mList.isNullOrEmpty()) 0 else mList.size
    }


    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var tvSentences: TextView = itemView.findViewById(R.id.tvSentences)

        @SuppressLint("SetTextI18n")
        fun bindView(word: String) {
            tvSentences.setHtmlText(word)
        }

    }


    companion object {
        private const val TAG = "SentencesAdapter"
    }

}