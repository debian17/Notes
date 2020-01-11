package github.debian17.notes.base.ui

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import github.debian17.notes.base.extension.hide
import github.debian17.notes.base.extension.show

class ViewStateSwitcher(
    private val context: Context,
    private val containerView: FrameLayout,
    private val retryListener: RetryListener
) {

    companion object {
        private const val PROGRESSBAR_TAG = "progressbarTag"
    }

    private var curViewState = ViewStates.MAIN

    private val progressbar: ProgressBar by lazy {
        return@lazy ProgressBar(context, null, android.R.attr.progressBarStyle).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            )
            visibility = View.GONE
            isIndeterminate = true
            tag = PROGRESSBAR_TAG
        }
    }

    private val errorViewGroup: FrameLayout by lazy {
        val container = FrameLayout(context).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
            )
            visibility = View.GONE
        }

        val errorTextView = TextView(context)

        return@lazy container
    }

    fun switchToLoading() {
        if (curViewState == ViewStates.LOADING) {
            return
        }
        curViewState = ViewStates.LOADING

        if (progressbar.parent == null) {
            containerView.addView(progressbar)
        }
        progressbar.show()

        val childViewCount = containerView.childCount
        for (i in 0..childViewCount) {
            val childView = containerView.getChildAt(i)
            if (childView != null && childView.tag != PROGRESSBAR_TAG) {
                childView.hide()
            }
        }

    }

    fun switchToMain() {
        if (curViewState == ViewStates.MAIN) {
            return
        }
        curViewState = ViewStates.MAIN

        val childViewCount = containerView.childCount
        for (i in 0..childViewCount) {
            val childView = containerView.getChildAt(i)
            if (childView != null && childView.tag != PROGRESSBAR_TAG) {
                childView.show()
            }
        }

        progressbar.hide()

    }

}