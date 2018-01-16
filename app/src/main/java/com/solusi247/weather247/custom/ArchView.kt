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
    val paint = Paint()
    lateinit var rect: RectF

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect = RectF(0f, 0f, w.toFloat(), h.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = ContextCompat.getColor(context, R.color.bgHome)

        path.moveTo(rect.top, rect.left)
        path.quadTo(rect.centerX(), rect.bottom, rect.right, rect.top)

        canvas.drawColor(ContextCompat.getColor(context, R.color.colorPrimary))
        canvas.drawPath(path, paint)
    }
}