package com.project.wordlearner.data

import com.project.wordlearner.R
import com.project.wordlearner.data.models.Dashboard

class Data {

    companion object {


        fun getDashboards(): List<Dashboard> {

            val list: MutableList<Dashboard> = mutableListOf()


            val dashboard2 = Dashboard(
                "Daily word\nlearning",
                "learn english conversation a to z ",
                2,
                R.drawable.bg_card_explore_3,
                R.drawable.bg_card_explore_3_inside,
                R.drawable.progress_bar_blue,
                60.0,
                25
            )

            val dashboard = Dashboard(
                "Phrases and idioms",
                "learn english conversation a to z ",
                1,
                R.drawable.bg_card_explore_1,
                R.drawable.bg_card_explore_1_inside,
                R.drawable.progress_bar_blue,
                60.0,
                25
            )

            val dashboard3 = Dashboard(
                "Most used \nSentences",
                "learn english conversation a to z ",
                3,
                R.drawable.bg_card_explore_2,
                R.drawable.bg_card_explore_2_inside,
                R.drawable.progress_bar_blue,
                60.0,
                25
            )

            val dashboard4 = Dashboard(
                "Conversation \nIn-depth",
                "learn english conversation a to z ",
                4,
                R.drawable.bg_card_explore_4,
                R.drawable.bg_card_explore_4_inside,
                R.drawable.progress_bar_red,
                60.0,
                25
            )



            list.add(dashboard2)
            list.add(dashboard)
            list.add(dashboard3)
            list.add(dashboard4)



            return list


        }


    }

}