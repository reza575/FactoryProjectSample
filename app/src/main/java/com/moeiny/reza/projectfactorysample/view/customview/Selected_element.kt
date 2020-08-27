package com.moeiny.reza.projectfactorysample.view.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.moeiny.reza.projectfactorysample.R

class Selected_element(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int):
    LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    lateinit var txtTitle: TextView
    var index: Int = 0
    var count: Int = 0

    constructor(context: Context, index:Int, count:Int):
            this(context,null,0,0){
        Init(context, index, count)
    }

    constructor(context: Context, attrs: AttributeSet):
            this(context, attrs, 0, 0) {
        Init(context, index, count)

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int):
            this(context, attrs, defStyleAttr, 0) {
        Init(context, index, count)

    }

    private fun Init(context: Context, index: Int, count: Int) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.selected_element_small, this, true)
        val factor = 1 + 1.0f * index / (2 * count)
        val cc = Color.rgb((100 * factor).toInt(), (80 * factor).toInt(), (160 * factor).toInt())
        pArc = object : Paint() {
            init {
                color = cc
                strokeWidth = 0f
                style = Paint.Style.FILL
            }
        }
        txtTitle = view.findViewById(R.id.txt_title_small)

        this.index = index
        this.count = count
    }

    private var pArc: Paint? = null

    protected override fun dispatchDraw(canvas: Canvas) {
        if (pArc != null)
            canvas.drawRoundRect(0f,0f,getWidth().toFloat(),getHeight().toFloat(),25f,25f,pArc!!)
        super.dispatchDraw(canvas)
    }
}