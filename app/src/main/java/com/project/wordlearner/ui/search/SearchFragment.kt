package com.project.wordlearner.ui.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.project.wordlearner.R
import com.project.wordlearner.data.db.AppDatabase
import com.project.wordlearner.data.models.Word
import com.project.wordlearner.data.preference.AppSharedPref
import com.project.wordlearner.data.repositories.WordRepo
import com.project.wordlearner.ui.details.WordDetailsActivity
import kotlinx.android.synthetic.main.search_fragment.*
import java.util.*

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.project.wordlearner.common.*

import android.view.animation.LinearInterpolator


class SearchFragment : Fragment(), SearchAdapter.SearchListener {

    companion object {
        fun newInstance() = SearchFragment()
        private const val TAG = "SearchFragment"
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var appSharedPref: AppSharedPref
    private lateinit var appDatabase: AppDatabase
    private lateinit var wordRepo: WordRepo
    private lateinit var factory: SearchViewModelFactory


    private lateinit var contInfoLayout: ConstraintLayout
    private lateinit var tvHelper: TextView
    private lateinit var cardSearch: CardView
    private lateinit var etSearch: EditText

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appSharedPref = AppSharedPref(requireContext())
        appDatabase = AppDatabase.getAppDataBase(requireContext())!!
        wordRepo = WordRepo(appDatabase)
        factory = SearchViewModelFactory(wordRepo, appSharedPref)
        viewModel = ViewModelProvider(this, factory).get(SearchViewModel::class.java)


        contInfoLayout = view.findViewById(R.id.contInfoLayout)
        tvHelper = view.findViewById(R.id.tvHelper)
        cardSearch = view.findViewById(R.id.cardSearch)
        etSearch = view.findViewById(R.id.etSearch)


        initSearchBox()

        recyclerView = view.findViewById(R.id.searchList)


        adapter = SearchAdapter(appSharedPref)
        recyclerView.also {
            it.setHasFixedSize(true)
            it.setItemViewCacheSize(20)
            ViewCompat.setNestedScrollingEnabled(it, false)
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }
        adapter.setListener(this)

        viewModel.searchFetchSuccess.observe(viewLifecycleOwner, { list ->
            list.toMutableList().sortBy { it.en.length }
            adapter.setUpdatedList(list)
        })


        imInfo.setOnClickListener {
            showInfoDialog()
        }

        swapFlag()

        imSwap.setOnClickListener {
            if (appSharedPref.getSearchType().equals(AppConstants.E_TO_B, true)) {
                appSharedPref.setSearchType(AppConstants.B_TO_E)
            } else {
                appSharedPref.setSearchType(AppConstants.E_TO_B)
            }
            swapFlag()
            rotateImSwapIcon()

            adapter.setUpdatedList(listOf())
            etSearch.setText("")

        }

    }

    private fun rotateImSwapIcon() {
        val rotate = RotateAnimation(
            0f,
            180f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 600
        rotate.interpolator = LinearInterpolator()
        imSwapIcon.startAnimation(rotate)
    }

    private fun swapFlag() {

        if (appSharedPref.getSearchType().equals(AppConstants.E_TO_B, true)) {

            ImageUtil.loadOfflineImage(firstFlag, R.drawable.en_flag)
            firstLang.text = "English"

            ImageUtil.loadOfflineImage(secondFlag, R.drawable.bd_flag)
            secondLang.text = "বাংলা"

            etSearch.hint = "Search here"
        } else {

            ImageUtil.loadOfflineImage(secondFlag, R.drawable.en_flag)
            secondLang.text = "English"

            ImageUtil.loadOfflineImage(firstFlag, R.drawable.bd_flag)
            firstLang.text = "বাংলা"

            etSearch.hint = "এখানে অনুসন্ধান করুন"
        }

    }


    private fun showInfoDialog() {

        MaterialAlertDialogBuilder(requireContext(), R.style.RoundShapeTheme)
            .setTitle("Your message goes here.")
            .setMessage("Keep it short but clear." + getString(R.string.dummy))
            .setPositiveButton(
                "GOT IT"
            ) { _, i -> }
            .show()

    }

    private fun initSearchBox() {
        etSearch.setOnKeyListener { v, _, event ->
            when {
                ((event.action == KeyEvent.ACTION_DOWN)) -> {
                    if (!v.isKeyboardVisible()) {
                        etSearch.clearFocus()
                        etSearch.isSelected = false
                        etSearch.isFocusable = false
                    } else {
                        v.hideKeyboard()
                    }
                    return@setOnKeyListener true
                }
                else -> false
            }

        }

        etSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                contInfoLayout.visibility = GONE
                tvHelper.visibility = View.INVISIBLE
                recyclerView.visibility = View.VISIBLE
            } else {
                contInfoLayout.visibility = View.VISIBLE
                tvHelper.visibility = GONE
                recyclerView.visibility = GONE
            }

        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if (s.toString().length >= 2) {
                    viewModel.searchIt(s.toString())
                } else {
                    adapter.setUpdatedList(listOf())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        etSearch.setOnClickListener {

            if (!it.hasFocus()) {
                it.isFocusableInTouchMode = true
                it.isFocusable = true
                it.requestFocus()
                it.showKeyboard()
            }

        }

    }

    override fun onItemClick(word: Word) {
        appSharedPref.setWord(Gson().toJson(word))
        startActivity(Intent(requireContext(), WordDetailsActivity::class.java))
    }

}