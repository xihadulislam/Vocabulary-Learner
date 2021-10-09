package com.project.wordlearner.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Data(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val isFav: Boolean = false,
    @SerializedName("pron") val pron: List<String>,
    @SerializedName("bn") val bn: String,
    @SerializedName("en") val en: String,
    @SerializedName("bn_syns") val bn_synonyms: List<String>,
    @SerializedName("en_syns") val en_synonyms: List<String>,
    @SerializedName("sents") val sentences: List<String>
)
