package kz.arctan.rock_paper_scissors

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class HealthBarView : View {
    private lateinit var mRect: Rect
    private lateinit var mPaint: Paint
    private lateinit var mBorderPaint: Paint

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        mBorderPaint = Paint()
        mBorderPaint.color = -0x1000000 // black brush color
        mBorderPaint.strokeWidth = 5f

        mRect = Rect(5, 5, 390, 65)
        mPaint = Paint()
        mPaint.color = Color.GREEN
    }

    fun reduceHealth() {
        mRect.right -= 40
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRect(mRect, mPaint)
        // border
        canvas?.drawLine(5f, 5f, 395f, 5f, mBorderPaint)
        canvas?.drawLine(5f, 65f, 5f, 5f, mBorderPaint)
        canvas?.drawLine(5f, 65f, 395f, 65f, mBorderPaint)
        canvas?.drawLine(395f, 65f, 395f, 5f, mBorderPaint)
    }

    fun reset() {
        mRect.right = 395
        invalidate()
    }
}