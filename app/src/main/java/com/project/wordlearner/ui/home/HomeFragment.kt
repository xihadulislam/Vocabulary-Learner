package com.project.wordlearner.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.littlemango.stacklayoutmanager.StackLayoutManager
import com.project.wordlearner.R
import com.littlemango.stacklayoutmanager.StackLayoutManager.*

import com.littlemango.stacklayoutmanager.DefaultAnimation


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
        private const val TAG = "HomeFragment"
    }

    private lateinit var viewModel: HomeViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: HomeAdapter

    private lateinit var manager: StackLayoutManager;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        recyclerView = view.findViewById(R.id.homeList)


        adapter = HomeAdapter(requireContext())

        recyclerView.also {
            it.setHasFixedSize(true)
            it.setItemViewCacheSize(20)
            ViewCompat.setNestedScrollingEnabled(it, false)


            val animation = DefaultAnimation(ScrollOrientation.TOP_TO_BOTTOM, 2)
            manager = StackLayoutManager(ScrollOrientation.RIGHT_TO_LEFT,2)
//            //  manager.setVisibleItemCount(2)
//            manager.setPagerMode(false)
//          //  manager.setPagerFlingVelocity(1000)
//            manager.setAnimation(animation)
            it.layoutManager = manager

            it.adapter = adapter
        }

        manager.setItemChangedListener(object : ItemChangedListener {
            override fun onItemChanged(position: Int) {
                Log.d(TAG, "onItemChanged: $position")
            }
        })


        var list = mutableListOf<String>()
        list.add("trtertre")
        list.add("trtertre")
        list.add("trtertre")
        list.add("trtertre")
        list.add("trtertre")
        list.add("trtertre")

        adapter.setUpdatedList(list)

    }


}