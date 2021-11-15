package com.project.wordlearner.ui.search


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.wordlearner.R
import com.project.wordlearner.common.AppConstants
import com.project.wordlearner.common.setHtmlText
import com.project.wordlearner.data.models.Word
import com.project.wordlearner.data.preference.AppSharedPref
import com.project.wordlearner.ui.home.HomeAdapter

class SearchAdapter(var appSharedPref: AppSharedPref) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    private var mList: MutableList<Word> = mutableListOf()


    private var listener: SearchListener? = null

    fun setListener(mListener: SearchListener) {
        listener = mListener
    }


    fun setUpdatedList(list: List<Word>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(mList[position], listener, appSharedPref)


        if (position == mList.size - 1) {
            holder.view.visibility = View.INVISIBLE
        } else {
            holder.view.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return if (mList.isNullOrEmpty()) 0 else mList.size
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var tvSearch: TextView = itemView.findViewById(R.id.tvSearch)
        var view: View = itemView.findViewById(R.id.view)

        @SuppressLint("SetTextI18n")
        fun bindView(word: Word, listener: SearchListener?, appSharedPref: AppSharedPref) {


            if (appSharedPref.getSearchType().equals(AppConstants.E_TO_B, true)) {
                tvSearch.setHtmlText(word.en)
            } else {
                tvSearch.setHtmlText(word.bn)
            }

            itemView.setOnClickListener { listener?.onItemClick(word) }

        }


    }

    interface SearchListener {

        fun onItemClick(word: Word)
    }


    companion object {
        private const val TAG = "SentencesAdapter"
    }

}