package com.project.wordlearner.data.models

data class Dashboard(

    var title: String,
    var description: String,
    var id: Long,
    var bg: Int,
    var bg_inside: Int,
    var progressbar: Int,
    var progress: Double,
    var lessson: Int
)