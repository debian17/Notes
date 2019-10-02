package github.debian17.notes.base.extension

import android.view.View
import android.view.animation.AlphaAnimation

const val ANIMATION_DURATION = 350L

fun View.hide() {
    if (visibility != View.GONE) {
        val animation = AlphaAnimation(1f, 0f).apply {
            duration = ANIMATION_DURATION
        }
        startAnimation(animation)
        visibility = View.GONE
    }
}

fun View.show() {
    if (visibility != View.VISIBLE) {
        val animation = AlphaAnimation(0f, 1f).apply {
            duration = ANIMATION_DURATION
        }
        startAnimation(animation)
        visibility = View.VISIBLE
    }
}