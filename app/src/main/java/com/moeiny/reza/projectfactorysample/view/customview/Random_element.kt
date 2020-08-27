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

class Random_element (context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int):
    LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    lateinit var txtTitle1: TextView
    lateinit var txtTitle2: TextView


    constructor(context: Context):
            this(context,null,0,0){
        Init(context)
    }

    constructor(context: Context, attrs: AttributeSet):
            this(context, attrs, 0, 0) {
        Init(context)

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int):
            this(context, attrs, defStyleAttr, 0) {
        Init(context)

    }

    private fun Init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.selected_element_big, this, true)
        val cc = Color.rgb(100, 80, 160)

        pArc = object : Paint() {
            init {
                color = cc
                strokeWidth = 0f
                style = Paint.Style.FILL
            }
        }

        txtTitle1 = view.findViewById(R.id.txt_title1)
        txtTitle2 = view.findViewById(R.id.txt_title2)


    }

    private var pArc: Paint? = null

    protected override fun dispatchDraw(canvas: Canvas) {
        if (pArc != null)
            canvas.drawRoundRect(0f,0f,getWidth().toFloat(),getHeight().toFloat(),25f,25f,pArc!!)
        super.dispatchDraw(canvas)
    }
}