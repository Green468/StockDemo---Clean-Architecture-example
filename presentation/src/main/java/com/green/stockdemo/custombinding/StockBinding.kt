package com.green.stockdemo.custombinding

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import javax.inject.Inject
import javax.inject.Singleton

@BindingAdapter("app:price_color")
fun bindPriceColor(textView: TextView, priceColor: Int) {
    when (priceColor) {
        0 -> textView.setTextColor(Color.BLACK)
        1 -> textView.setTextColor(Color.GREEN)
        -1 -> textView.setTextColor(Color.RED)
    }
}

@BindingAdapter("app:price_bg_anim")
fun bindPriceBgAnimation(view: View, priceColor: Int) {
    var colorId = Color.TRANSPARENT
    when (priceColor) {
        0 -> colorId = Color.TRANSPARENT
        1 -> colorId = Color.GREEN
        -1 -> colorId = Color.RED
    }
    generateAnimateBlinkLastPrice(view, colorId).start()
}

fun generateAnimateBlinkLastPrice(view: View, colorId: Int): ObjectAnimator {
    val animator = ObjectAnimator.ofObject(
        view,
        "backgroundColor",
        ArgbEvaluator(),
        Color.TRANSPARENT,
        colorId
    )
    animator.duration = 800
    animator.repeatMode = ObjectAnimator.REVERSE
    animator.repeatCount = 1
    animator.setAutoCancel(true)
    return animator
}
