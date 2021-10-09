package com.project.wordlearner.ui.details

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.littlemango.stacklayoutmanager.DefaultAnimation
import com.littlemango.stacklayoutmanager.StackLayoutManager
import com.project.wordlearner.R
import com.project.wordlearner.common.capitalizeWords
import com.project.wordlearner.common.setHtmlText
import com.project.wordlearner.data.models.Word
import com.project.wordlearner.data.preference.AppSharedPref
import com.project.wordlearner.ui.home.HomeAdapter
import com.project.wordlearner.ui.home.HomeFragment
import com.project.wordlearner.ui.home.WordSate

class WordDetailsActivity : AppCompatActivity() {

    private lateinit var appSharedPref: AppSharedPref


    private lateinit var tvId: TextView

    private lateinit var tvMainText: TextView
    private lateinit var tvSecondText: TextView
    private lateinit var tv_en_synonyms: TextView
    private lateinit var tv_bn_synonyms: TextView
    private lateinit var tvKnow: TextView
    private lateinit var tvLearning: TextView
    private lateinit var tvNotify: TextView

    private lateinit var imBookmark: ImageView
    private lateinit var imClose: ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SentencesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_details)
        appSharedPref = AppSharedPref(this)

        tvId = findViewById(R.id.tvId)
        tvMainText = findViewById(R.id.tvMainText)
        tvSecondText = findViewById(R.id.tvSecondText)
        tv_en_synonyms = findViewById(R.id.tv_en_synonyms)
        tv_bn_synonyms = findViewById(R.id.tv_bn_synonyms)

        tvKnow = findViewById(R.id.tvKnow)
        tvLearning = findViewById(R.id.tvLearning)
        tvNotify = findViewById(R.id.tvNotify)

        imBookmark = findViewById(R.id.imBookmark)
        imClose = findViewById(R.id.imClose)

        recyclerView = findViewById(R.id.sentencesList)


        adapter = SentencesAdapter()
        recyclerView.also {
            it.setHasFixedSize(true)
            it.setItemViewCacheSize(20)
            ViewCompat.setNestedScrollingEnabled(it, false)
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }


        imBookmark.setOnClickListener { }
        imClose.setOnClickListener { }

        tvKnow.setOnClickListener { }

        tvLearning.setOnClickListener {

        }

        tvNotify.setOnClickListener {

        }


        getWord()

    }

    private fun getWord() {
        val word = Gson().fromJson(appSharedPref.getWord(), Word::class.java)
        word?.let {
            bindData(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindData(word: Word) {

        tvId.text = "# ${word.id}"
        tvMainText.text = word.en.capitalizeWords()
        tvSecondText.text = word.bn

        if (word.en_synonyms.isEmpty()) tv_en_synonyms.text = "N/A"
        else tv_en_synonyms.text = word.en_synonyms.joinToString()

        if (word.bn_synonyms.isEmpty()) tv_bn_synonyms.text = "N/A"
        else tv_bn_synonyms.text = word.bn_synonyms.joinToString()


        adapter.setUpdatedList(word.sentences)

    }
}