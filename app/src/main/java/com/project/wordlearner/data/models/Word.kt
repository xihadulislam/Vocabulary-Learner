package com.project.wordlearner.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Word(
    @SerializedName("pron") var pron: List<String?>,
    @SerializedName("bn") var bn: String,
    @SerializedName("en") var en: String,
    @SerializedName("bn_syns") var bn_synonyms: List<String>,
    @SerializedName("en_syns") var en_synonyms: List<String>,
    @SerializedName("sents") var sentences: List<String>,

    @PrimaryKey(autoGenerate = true) val id: Long=0,
    var isFav: Boolean = false,
    var wordType: String = "",
    var status: String = "active",
    var stage: String = "learning",
    var stage_count: Int = 0,
)


data class WordDemo(
    @SerializedName("pron") var pron: List<String?>,
    @SerializedName("bn") var bn: String,
    @SerializedName("en") var en: String,
    @SerializedName("bn_syns") var bn_synonyms: List<String>,
    @SerializedName("en_syns") var en_synonyms: List<String>,
    @SerializedName("sents") var sentences: List<String>
)