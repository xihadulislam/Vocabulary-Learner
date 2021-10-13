package com.project.wordlearner.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.wordlearner.R


class CategorySliderAdapter() :
    RecyclerView.Adapter<CategorySliderAdapter.MyViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(news: String)
    }

    private lateinit var mListener: OnItemClickListener
    fun setListener(listener: OnItemClickListener) {
        mListener = listener
    }

    private var list: List<String> = listOf()
    fun setNewList(mList: List<String>) {
        list = mList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.item_explore, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setDataBinding(list[position])
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) {
            0
        } else list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setDataBinding(news: String) {

        }
    }
}