package com.project.wordlearner.data.models

import com.google.gson.annotations.SerializedName


data class Data (

	@SerializedName("pron") val pron : List<String>,
	@SerializedName("bn") val bn : String,
	@SerializedName("en") val en : String,
	@SerializedName("bn_syns") val bn_syns : List<String>,
	@SerializedName("en_syns") val en_syns : List<String>,
	@SerializedName("sents") val sents : List<String>
)