package io.github.feelfreelinux.wykopmobilny.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.feelfreelinux.wykopmobilny.R
import io.github.feelfreelinux.wykopmobilny.adapters.holders.FeedViewHolder
import io.github.feelfreelinux.wykopmobilny.presenters.FeedPresenter


class FeedAdapter(val presenter : FeedPresenter) : RecyclerView.Adapter<FeedViewHolder>() {
    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        presenter.onBindListItem(position, holder)
    }

    override fun getItemCount() = presenter.getItemsCount()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder =
            FeedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.feed_layout, parent, false))

}
