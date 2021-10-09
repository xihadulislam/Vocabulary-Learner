package com.project.wordlearner.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var isFav: Boolean = false,
    @SerializedName("pron") var pron: List<String?>,
    @SerializedName("bn") var bn: String,
    @SerializedName("en") var en: String,
    @SerializedName("bn_syns") var bn_synonyms: List<String>,
    @SerializedName("en_syns") var en_synonyms: List<String>,
    @SerializedName("sents") var sentences: List<String>
)
