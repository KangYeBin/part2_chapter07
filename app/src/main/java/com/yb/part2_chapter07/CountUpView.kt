package com.yb.part2_chapter07

import android.annotation.SuppressLint
import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView

class CountUpView(context: Context, attrs: AttributeSet? = null) :
    AppCompatTextView(context, attrs) {

    private var startTimeStamp: Long = 0

    private val countUpAction: Runnable = object : Runnable {
        override fun run() {
            val currentTimeStamp = SystemClock.elapsedRealtime()
            val currentTimeSeconds = ((currentTimeStamp - startTimeStamp) / 1000L).toInt()

            invalidate()
            updateCountTime(currentTimeSeconds)
            handler?.postDelayed(this, 1000L)
        }
    }

    fun startCountUp() {
        startTimeStamp = SystemClock.elapsedRealtime()
        handler?.post(countUpAction)

    }

    fun stopCountUp() {
        handler?.removeCallbacks(countUpAction)
    }

    fun clearCountTime() {
        updateCountTime(0)
    }

    @SuppressLint("SetTextI18n")
    private fun updateCountTime(countTimeSeconds: Int) {
        val minutes = countTimeSeconds / 60
        val seconds = countTimeSeconds % 60

        text = "%02d:%02d".format(minutes, seconds)
    }
}