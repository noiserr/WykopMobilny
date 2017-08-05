package io.github.feelfreelinux.wykopmobilny.adapters.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.feelfreelinux.wykopmobilny.R
import io.github.feelfreelinux.wykopmobilny.objects.Embed
import io.github.feelfreelinux.wykopmobilny.utils.*
import kotlinx.android.synthetic.main.feed_layout.view.*

interface EntryDetailsListItem {
    fun setHeader(author : String, authorGroup : Int, authorSex: String, avatarUrl : String, dateSubtitle : String, app : String?)
    fun setBody(body : String, embed : Embed?)
    fun setupVoteButton(votesCount : Int, isVoted : Boolean)
    fun setupEmbed(embed: Embed)
    fun hideImage()
    var voteClickListener: EmptyListener
    var tagClickListener: TagClickedListener
}

class EntryDetailsViewHolder(val view: View) : RecyclerView.ViewHolder(view), EntryDetailsListItem {
    override var voteClickListener = {}
    override var tagClickListener: TagClickedListener = {}

    override fun hideImage() = view.entryImageView.gone()

    override fun setupEmbed(embed: Embed) {
        view.entryImageView.visible()
        view.entryImageView.loadImage(embed.preview)
        if (embed.type == "image") view.entryImageView.setPhotoViewUrl(embed.url)
    }

    override fun setupVoteButton(voteCount: Int, isVoted: Boolean) {
        view.voteCountTextView.apply {
            isSelected = isVoted
            text = context.getString(R.string.votes_count, voteCount)
            setOnClickListener { voteClickListener.invoke() }
        }
    }

    override fun setBody(body: String, embed: Embed?) {
        view.entryContentTextView.prepareBody(body, tagClickListener)
    }

    override fun setHeader(author: String, authorGroup : Int, authorSex : String, avatarUrl: String, dateSubtitle: String, app : String?) {
        view.userNameTextView.apply {
            text = author
            setTextColor(getGroupColor(authorGroup))
        }

        view.avatarImageView.loadImage(avatarUrl)
        view.entryDateTextView.text = dateSubtitle
        app?.let {
            view.entryDateTextView.text = dateSubtitle + " " + view.context.getString(R.string.user_app, app)
        }

        view.genderStripImageView.setBackgroundResource(getGenderStripResource(authorSex))
    }

}