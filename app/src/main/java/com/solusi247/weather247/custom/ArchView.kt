package com.solusi247.weather247.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.solusi247.weather247.R

class ArchView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    val path = Path()
    val path2 = Path()
    val paint = Paint()
    val paint2 = Paint()
    lateinit var rect: RectF

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect = RectF(0f, 0f, w.toFloat(), h.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = ContextCompat.getColor(context, R.color.backgroundMain)
        }

        paint2.apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = ContextCompat.getColor(context, R.color.colorArch)
            alpha = 122
        }

        path.apply {
            moveTo(rect.left, rect.top)
            quadTo(rect.centerX(), rect.bottom * 2, rect.right, rect.top)
        }

        path2.apply {
            moveTo(rect.left, rect.bottom)
            quadTo(rect.centerX(), rect.top, rect.right, rect.bottom)
        }

        canvas.apply {
            drawRect(rect, paint2)
            drawPath(path, paint)
            drawPath(path2, paint2)
        }
    }
}