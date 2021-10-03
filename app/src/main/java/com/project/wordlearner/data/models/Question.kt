package com.project.wordlearner.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
        @PrimaryKey val id: Int,
        val formula: String,
        val answer: Double,
        val solutionType: String,
        val question: String,
        var nearestAnswer: String = "",
        var isCompleted: Boolean = false,
        var isLocked:Boolean ,
        var star: Int = 0
)
