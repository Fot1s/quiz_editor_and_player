package com.phinnovation.quizplayer.presentation.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.phinnovation.quizplayer.R
import kotlin.math.round

class VUMeterComponent(context: Context, attrs: AttributeSet) : View(context, attrs) {
    companion object {
        private const val DEFAULT_BG_COLOR = Color.YELLOW
        private const val DEFAULT_BAR_ACTIVE_COLOR = Color.RED
        private const val DEFAULT_BAR_INACTIVE_COLOR = Color.GRAY
        private const val DEFAULT_BAR_WIDTH = 8f
        private const val DEFAULT_BAR_SEPARATOR_WIDTH = 8f
        private const val DEFAULT_PADDING = 16f
        private const val DEFAULT_INITIAL_VALUE = 0
        private const val DEFAULT_MIN_VALUE = 0
        private const val DEFAULT_MAX_VALUE = 100
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var bgColor = DEFAULT_BG_COLOR
    private var barColorActive = DEFAULT_BAR_ACTIVE_COLOR
    private var barColorInactive = DEFAULT_BAR_INACTIVE_COLOR
    private var barWidth = DEFAULT_BAR_WIDTH
    private var barSeparatorWidth = DEFAULT_BAR_SEPARATOR_WIDTH
    private var padding = DEFAULT_PADDING;
    private var minValue = DEFAULT_MIN_VALUE ;
    private var maxValue = DEFAULT_MAX_VALUE ;

    private var canvasRect:Rect = Rect() ;

    var value = DEFAULT_INITIAL_VALUE
        set(state) {
            field = state
            invalidate()
        }

    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.getClipBounds(canvasRect)

        //draw BG
        paint.color = bgColor
        paint.style = Paint.Style.FILL
        canvas.drawRect(canvasRect, paint)
        paint.color = barColorActive

        //DRAW Inactive bars
        paint.color = barColorInactive
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = barWidth
        paint.strokeCap = Paint.Cap.ROUND

        val numberOfBars = ((canvasRect.width() - 2 * padding) / (barWidth + barSeparatorWidth)).toInt()

        val fix = canvasRect.width() - 2 * padding - numberOfBars * (barWidth + barSeparatorWidth)
        padding += fix/2

        for (i in 0 .. numberOfBars ) {
            canvas.drawLine(padding + i*barWidth*2,padding,padding + i*barWidth*2,canvasRect.height()-padding, paint)
        }

        //Draw active bars
        paint.color = barColorActive

        //normalize
        var normalizedValue = (round((value - minValue).toFloat() / (maxValue - minValue) * (numberOfBars + 1))).toInt()

        for (i in 0 until normalizedValue) {
            canvas.drawLine(padding + i*barWidth*2,padding,padding + i*barWidth*2,canvasRect.height()-padding, paint)
        }
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.VUMeterComponent, 0, 0)

        // Extract custom attributes into member variables
        bgColor = typedArray.getColor(R.styleable.VUMeterComponent_bgColor, DEFAULT_BG_COLOR)
        barColorActive = typedArray.getColor(R.styleable.VUMeterComponent_barActiveColor, DEFAULT_BAR_ACTIVE_COLOR)
        barColorInactive = typedArray.getColor(R.styleable.VUMeterComponent_barInactiveColor, DEFAULT_BAR_INACTIVE_COLOR)
        barWidth = typedArray.getFloat(R.styleable.VUMeterComponent_barWidth, DEFAULT_BAR_WIDTH)
        barSeparatorWidth = typedArray.getFloat(R.styleable.VUMeterComponent_barSeparatorWidth, DEFAULT_BAR_SEPARATOR_WIDTH)
        padding = typedArray.getFloat(R.styleable.VUMeterComponent_padding, DEFAULT_PADDING)
        value = typedArray.getInt(R.styleable.VUMeterComponent_initialValue, DEFAULT_INITIAL_VALUE)
        minValue = typedArray.getInt(R.styleable.VUMeterComponent_minValue, DEFAULT_MIN_VALUE)
        maxValue = typedArray.getInt(R.styleable.VUMeterComponent_maxValue, DEFAULT_MAX_VALUE)

        // TypedArray objects are shared and must be recycled.
        typedArray.recycle()
    }
}