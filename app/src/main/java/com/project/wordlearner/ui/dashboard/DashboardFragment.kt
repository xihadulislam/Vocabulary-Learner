package com.project.wordlearner.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.wordlearner.R

class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var viewModel: DashboardViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DashboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        recyclerView = view.findViewById(R.id.dashboardList)
        adapter = DashboardAdapter(requireContext())

        recyclerView.also {
            it.setHasFixedSize(true)
            it.setItemViewCacheSize(20)
            ViewCompat.setNestedScrollingEnabled(it, false)
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }

        val list = mutableListOf<String>()
        list.add("trtertre")
        list.add("trtertre")
        list.add("trtertre")
        list.add("trtertre")

        adapter.setUpdatedList(list)


    }

}