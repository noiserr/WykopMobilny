package io.github.feelfreelinux.wykopmobilny.utils

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.View
import android.widget.TextView
import io.github.feelfreelinux.wykopmobilny.adapters.TagClickListener

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun SpannableStringBuilder.makeLinkClickable(context: Context, span: URLSpan, tagClickListener: TagClickListener?) {
    val start = getSpanStart(span)
    val end = getSpanEnd(span)
    val flags = getSpanFlags(span)
    val clickable = object : LinkSpan() {
        override fun onClick(tv: View) {
            if (span.isTag) {
                tagClickListener?.invoke(span.url.removePrefix("#"))
            }
        }
    }
    setSpan(clickable, start, end, flags)
    removeSpan(span)
}

fun TextView.setTagsClickable(html: String, tagClickListener: TagClickListener? = null) {
    val sequence = html.toSpannable()
    val strBuilder = SpannableStringBuilder(sequence)
    val urls = strBuilder.getSpans(0, strBuilder.length, URLSpan::class.java)
    urls.forEach {
        span -> strBuilder.makeLinkClickable(context, span, tagClickListener)
    }
    text = strBuilder
    movementMethod = LinkMovementMethod.getInstance()
}