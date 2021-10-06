package com.project.wordlearner.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.wordlearner.R
import com.project.wordlearner.data.models.Data

class HomeAdapter(val context: Context) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private var mList: MutableList<String> = mutableListOf()

    fun setUpdatedList(list: List<String>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_card, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(mList[position], context)
    }

    override fun getItemCount(): Int {
        return if (mList.isNullOrEmpty()) 0 else mList.size
    }


    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindView(faq: String, context: Context) {

        }

    }

}