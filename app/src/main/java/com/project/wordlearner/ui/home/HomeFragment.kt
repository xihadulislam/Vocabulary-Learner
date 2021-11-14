package com.project.wordlearner.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.littlemango.stacklayoutmanager.StackLayoutManager
import com.project.wordlearner.R
import com.littlemango.stacklayoutmanager.StackLayoutManager.*

import com.littlemango.stacklayoutmanager.DefaultAnimation
import com.project.wordlearner.common.AppConstants
import com.project.wordlearner.data.db.AppDatabase
import com.project.wordlearner.data.models.Word
import com.project.wordlearner.data.preference.AppSharedPref
import com.project.wordlearner.data.repositories.WordRepo
import com.project.wordlearner.ui.details.WordDetailsActivity
import com.project.wordlearner.ui.splash.SplashViewModel
import com.project.wordlearner.ui.splash.SplashViewModelFactory
import com.xihad.androidutils.AndroidUtils
import java.util.*


class HomeFragment : Fragment(), HomeAdapter.HomeListener, TextToSpeech.OnInitListener {

    companion object {
        fun newInstance() = HomeFragment()
        private const val TAG = "HomeFragment"
    }

    private lateinit var appSharedPref: AppSharedPref
    private lateinit var appDatabase: AppDatabase
    private lateinit var wordRepo: WordRepo
    private lateinit var factory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: HomeAdapter

    private lateinit var manager: StackLayoutManager

    private var tts: TextToSpeech? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appSharedPref = AppSharedPref(requireContext())
        appDatabase = AppDatabase.getAppDataBase(requireContext())!!
        wordRepo = WordRepo(appDatabase)
        factory = HomeViewModelFactory(wordRepo, appSharedPref)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)


        recyclerView = view.findViewById(R.id.homeList)


        adapter = HomeAdapter(requireContext())

        recyclerView.also {
            it.setHasFixedSize(true)
            it.setItemViewCacheSize(20)
            ViewCompat.setNestedScrollingEnabled(it, false)


            val animation = DefaultAnimation(ScrollOrientation.TOP_TO_BOTTOM, 2)
            manager = StackLayoutManager(ScrollOrientation.RIGHT_TO_LEFT, 2)
//            //  manager.setVisibleItemCount(2)
//            manager.setPagerMode(false)
//          //  manager.setPagerFlingVelocity(1000)
//            manager.setAnimation(animation)
            it.layoutManager = manager

            it.adapter = adapter
        }
        adapter.setListener(this)

        manager.setItemChangedListener(object : ItemChangedListener {
            override fun onItemChanged(position: Int) {
                Log.d(TAG, "onItemChanged: $position")
            }
        })

        adapter.setUpdatedList(getTodayWord())

//        viewModel.getWordList().observe(viewLifecycleOwner, { words ->
//
//            Log.d(TAG, "onViewCreated: "+words.size)
//            adapter.setUpdatedList(words)
//        })


    }

    override fun onStart() {
        super.onStart()
        tts = TextToSpeech(requireContext().applicationContext, this)
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(
                    requireContext(),
                    "The Language specified is not supported!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                Log.d(TAG, "onInit: ")
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }

    }

    private fun speak(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }

    private fun stopSpeak() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSpeak()
    }

    private fun setStatusBarColor(color: Int) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(requireContext(), color)
        }
    }

    private fun getTodayWord(): List<Word> {
        val list = Gson().fromJson(appSharedPref.getTodayWordList(), Array<Word>::class.java)
            .toMutableList()
        list.shuffle()

        return list
    }

    override fun onItemClick(word: Word) {
        appSharedPref.setWord(Gson().toJson(word))
        startActivity(Intent(requireContext(), WordDetailsActivity::class.java))
        //  activity?.overridePendingTransition(R.anim.fade_in_animation, R.anim.fade_out_animation)
    }

    override fun onSpeakClick(word: Word) {
        speak(word.en)
    }

    override fun onBookmarkClick(word: Word, position: Int) {

        viewModel.bookmarkUpdate(word)
        //  adapter.bookMarkUpdate(position)

    }

    override fun onCloseClick(word: Word, position: Int) {


    }

    override fun onActionClick(word: Word, position: Int) {
        viewModel.stageUpdate(word)

        manager.requestSimpleAnimationsInNextLayout()

    }


}