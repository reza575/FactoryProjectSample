package com.moeiny.reza.projectfactorysample.view.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.moeiny.reza.projectfactorysample.MainActivity
import com.moeiny.reza.projectfactorysample.R
import com.moeiny.reza.projectfactorysample.repository.model.Decision
import com.moeiny.reza.projectfactorysample.viewmodel.DecisionViewModel

class Decision_element(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int):
    LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    lateinit var txtTitle: TextView
    lateinit var imgCheck: ImageView
    var index: Int = 0
    var count: Int = 0

    lateinit var viewModel:DecisionViewModel

    constructor(context: Context, index:Int, count:Int,viewModel:DecisionViewModel,selected:Int):
            this(context,null,0,0){
        Init(context, index, count,viewModel,selected)
    }

    constructor(context: Context, attrs: AttributeSet,viewModel:DecisionViewModel,selected:Int):
            this(context, attrs, 0, 0) {
        Init(context, index, count,viewModel,selected)

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int,viewModel:DecisionViewModel,selected:Int):
            this(context, attrs, defStyleAttr, 0) {
        Init(context, index, count,viewModel,selected)

    }


    private fun Init(context: Context, index: Int, count: Int,viewModel: DecisionViewModel,selected: Int) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.decission_element, this, true)
        val factor = 1 + 1.0f * index / (2 * count)
        val cc = Color.rgb((100 * factor).toInt(), (80 * factor).toInt(), (160 * factor).toInt())
        pArc = object : Paint() {
            init {
                color = cc
                strokeWidth = 0f
                style = Paint.Style.FILL
            }
        }

        txtTitle = view.findViewById(R.id.txt_title) as TextView
        imgCheck = view.findViewById(R.id.img_check) as ImageView
        this.index = index
        this.count = count
        this.viewModel = viewModel

        if(selected==1)
            imgCheck.visibility=View.VISIBLE
        else if (selected==0)
            imgCheck.visibility=View.INVISIBLE

        view.setOnClickListener {
            if (imgCheck.visibility == View.VISIBLE) {
                var item=viewModel.findDecisionByName(txtTitle.text.toString())
                item.decision_select=0
                viewModel.updateDecision(item)
                val animation = ScaleAnimation(1f, 0f, 1f, 0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                animation.duration = 100
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {

                    }

                    override fun onAnimationEnd(animation: Animation) {
                        imgCheck.visibility = View.INVISIBLE
                    }

                    override fun onAnimationRepeat(animation: Animation) {

                    }
                })
                imgCheck.startAnimation(animation)
            } else {
                var item=viewModel.findDecisionByName(txtTitle.text.toString())
                item.decision_select=1
                viewModel.updateDecision(item)
                val animation = ScaleAnimation(0f,1f,0f,1f,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
                animation.duration = 100
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {

                    }

                    override fun onAnimationEnd(animation: Animation) {
                        imgCheck.visibility = View.VISIBLE
                    }

                    override fun onAnimationRepeat(animation: Animation) {

                    }
                })
                imgCheck.startAnimation(animation)
            }
        }
    }


    private var pArc: Paint? = null

    override fun dispatchDraw(canvas: Canvas) {
        if (pArc != null)
            canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), 25f, 25f, pArc!!)
        super.dispatchDraw(canvas)
    }
}