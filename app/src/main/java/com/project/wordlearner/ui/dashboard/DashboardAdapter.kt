package com.project.wordlearner.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.project.wordlearner.R
import com.project.wordlearner.data.models.Dashboard

class DashboardAdapter(val context: Context) :
    RecyclerView.Adapter<DashboardAdapter.MyViewHolder>() {

    private var mList: MutableList<Dashboard> = mutableListOf()

    fun setUpdatedList(list: List<Dashboard>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_explore, parent, false)
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

        var card_explore: ConstraintLayout = itemView.findViewById(R.id.card_explore)
        var card_inside: ConstraintLayout = itemView.findViewById(R.id.card_inside)


        fun bindView(dashboard: Dashboard, context: Context) {

            card_explore.background = ContextCompat.getDrawable(context, dashboard.bg)
            card_inside.background = ContextCompat.getDrawable(context, dashboard.bg_inside)


        }

    }

}