package com.project.wordlearner.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.project.wordlearner.R
import com.project.wordlearner.common.AppConstants.Companion.I_KNOW
import com.project.wordlearner.common.AppConstants.Companion.LEARNING
import com.project.wordlearner.common.AppConstants.Companion.NOTIFY_ME
import com.project.wordlearner.common.capitalizeWords
import com.project.wordlearner.common.setHtmlText
import com.project.wordlearner.data.models.Word

class HomeAdapter(val context: Context) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private var mList: MutableList<Word> = mutableListOf()
    private var listener: HomeListener? = null

    fun setListener(mListener: HomeListener) {
        listener = mListener
    }

    fun setUpdatedList(list: List<Word>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_card, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(context, mList[position], listener, mList.size)
    }

    override fun getItemCount(): Int {
        return if (mList.isNullOrEmpty()) 0 else mList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun deleteITem(position: Int) {

        if (position < mList.size) {
            mList.removeAt(position)
            notifyItemRangeChanged(position, mList.size)
        } else {
            notifyDataSetChanged()
        }

    }


    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var tvId: TextView = itemView.findViewById(R.id.tvId)
        var tvMainText: TextView = itemView.findViewById(R.id.tvMainText)
        var tvSecondText: TextView = itemView.findViewById(R.id.tvSecondText)
        var tv_en_synonyms: TextView = itemView.findViewById(R.id.tv_en_synonyms)
        var tv_bn_synonyms: TextView = itemView.findViewById(R.id.tv_bn_synonyms)
        var tvSentences: TextView = itemView.findViewById(R.id.tvSentences)

        var tvKnow: TextView = itemView.findViewById(R.id.tvKnow)
        var tvLearning: TextView = itemView.findViewById(R.id.tvLearning)
        var tvNotify: TextView = itemView.findViewById(R.id.tvNotify)

        var imBookmark: ImageView = itemView.findViewById(R.id.imBookmark)
        var imClose: ImageView = itemView.findViewById(R.id.imClose)
        var imSpeak: ImageView = itemView.findViewById(R.id.imSpeak)

        var innerRoot: LinearLayout = itemView.findViewById(R.id.innerRoot)

        @SuppressLint("SetTextI18n")
        fun bindView(context: Context, word: Word, listener: HomeListener?, size: Int) {

            tvId.text = "$adapterPosition / $size"
            tvMainText.text = word.en.capitalizeWords()
            tvSecondText.text = word.bn

            if (word.en_synonyms.isEmpty()) tv_en_synonyms.text = "N/A"
            else tv_en_synonyms.text = word.en_synonyms.joinToString()

            if (word.bn_synonyms.isEmpty()) tv_bn_synonyms.text = "N/A"
            else tv_bn_synonyms.text = word.bn_synonyms.joinToString()


            if (word.sentences.isNotEmpty()) {

                if (word.sentences.size > 1) {
                    if (word.sentences[0].length < word.sentences[1].length) {
                        tvSentences.setHtmlText(word.sentences[0])
                    } else {
                        tvSentences.setHtmlText(word.sentences[1])
                    }
                } else {

                    tvSentences.setHtmlText(word.sentences[0])
                }

            }

            bindBookmarkIcon(context, word)
            bindActionIcon(context, word)

            itemView.setOnClickListener { listener?.onItemClick(word) }
            innerRoot.setOnClickListener { listener?.onItemClick(word) }
            imBookmark.setOnClickListener {
                word.isFav = !word.isFav
                bindBookmarkIcon(context, word)
                listener?.onBookmarkClick(word, adapterPosition)

            }
            imClose.setOnClickListener { listener?.onCloseClick(word, adapterPosition) }

            tvKnow.setOnClickListener {

                if (word.stage.equals(I_KNOW, true)) {
                    return@setOnClickListener
                }

                word.stage = I_KNOW

                listener?.onActionClick(
                    word,
                    adapterPosition
                )

                bindActionIcon(context,word)

            }

            tvLearning.setOnClickListener {

                if (word.stage.equals(LEARNING, true)) {
                    return@setOnClickListener
                }
                word.stage = LEARNING
                listener?.onActionClick(
                    word,
                    adapterPosition
                )
                bindActionIcon(context,word)
            }

            tvNotify.setOnClickListener {

                if (word.stage.equals(NOTIFY_ME, true)) {
                    return@setOnClickListener
                }
                word.stage = NOTIFY_ME
                listener?.onActionClick(
                    word,
                    adapterPosition
                )
                bindActionIcon(context,word)
            }

            imSpeak.setOnClickListener {
                listener?.onSpeakClick(
                    word
                )
            }


        }


        private fun bindBookmarkIcon(context: Context, word: Word) {

            if (word.isFav) {
                imBookmark.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_bookmark_remove
                    )
                )
            } else {
                imBookmark.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_bookmark_boarder
                    )
                )
            }

        }

        final fun bindActionIcon(context: Context, word: Word) {


            if (word.stage.equals(NOTIFY_ME, true)) {

                tvNotify.background = ContextCompat.getDrawable(context, R.drawable.bg_grey_card)
            } else {
                tvNotify.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_logout_card_normal)
            }



            if (word.stage.equals(LEARNING, true)) {

                tvLearning.background = ContextCompat.getDrawable(context, R.drawable.bg_grey_card)
            } else {
                tvLearning.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_preparation_screen)
            }

            if (word.stage.equals(I_KNOW, true)) {

                tvKnow.background = ContextCompat.getDrawable(context, R.drawable.bg_grey_card)
            } else {
                tvKnow.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_result_point_card_normal)
            }


        }


    }


    interface HomeListener {

        fun onItemClick(word: Word)
        fun onSpeakClick(word: Word)
        fun onBookmarkClick(word: Word, position: Int)
        fun onCloseClick(word: Word, position: Int)
        fun onActionClick(word: Word, position: Int)
    }


    companion object {
        private const val TAG = "HomeAdapter"
    }

}