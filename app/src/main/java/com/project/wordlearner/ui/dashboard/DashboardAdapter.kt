package com.project.wordlearner.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.project.wordlearner.R
import com.project.wordlearner.data.models.Dashboard


class DashboardAdapter(val context: Context) :
    RecyclerView.Adapter<DashboardAdapter.MyViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(news: String)
    }

    private lateinit var mListener: OnItemClickListener
    fun setListener(listener: OnItemClickListener) {
        mListener = listener
    }

    private var list: List<Dashboard> = mutableListOf()
    fun setNewList(mList: List<Dashboard>) {
        list = mList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.item_explore, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setDataBinding(context, list[position])
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) {
            0
        } else list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var card_explore: ConstraintLayout = itemView.findViewById(R.id.card_explore)
        var card_inside: ConstraintLayout = itemView.findViewById(R.id.card_inside)
        var categoryProgressBar: ProgressBar = itemView.findViewById(R.id.categoryProgressBar)

        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvSubTitle: TextView = itemView.findViewById(R.id.tvSubTitle)
        var tvLessionCount: TextView = itemView.findViewById(R.id.tvLessionCount)
        var tvLession: TextView = itemView.findViewById(R.id.tvLession)


        fun setDataBinding(context: Context, dashboard: Dashboard) {
            card_explore.background = ContextCompat.getDrawable(context, dashboard.bg)
            card_inside.background = ContextCompat.getDrawable(context, dashboard.bg_inside)
            categoryProgressBar.progressDrawable =
                ContextCompat.getDrawable(context, dashboard.progressbar)

            tvTitle.text = dashboard.title
            tvSubTitle.text = dashboard.description
            tvLessionCount.text = "${dashboard.lessson}"

        }
    }
}