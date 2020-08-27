package com.moeiny.reza.projectfactorysample.view

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.moeiny.reza.projectfactorysample.MainActivity
import com.moeiny.reza.projectfactorysample.R
import com.moeiny.reza.projectfactorysample.view.customview.Random_element
import com.moeiny.reza.projectfactorysample.view.customview.Selected_element
import com.moeiny.reza.projectfactorysample.viewmodel.DecisionViewModel

class ShowActivity : AppCompatActivity() {
    lateinit var linear_vertical_left: LinearLayout
    lateinit var linear_vertical_right: LinearLayout
    lateinit var txt_back: TextView
    lateinit var viewModel: DecisionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        linear_vertical_left = findViewById(R.id.ll_vertical_left)
        linear_vertical_right = findViewById(R.id.ll_vertical_right)
        txt_back = findViewById(R.id.txt_back)
        viewModel = ViewModelProviders.of(this).get(DecisionViewModel::class.java)

        txt_back.setOnClickListener {
            var intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        var items=viewModel.getSelected()

        if(items.size!=null) {
            var rand = java.util.Random();
            var idx = rand.nextInt(items.size)
            var element = Random_element(this)
            element.txtTitle2.text = items[idx].decision_title
            var param = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            param.setMargins(15, 15, 15, 15)
            linear_vertical_right.addView(element, param)

            for (i in 0..items.size - 1) {
                if (i == idx)
                    continue;
                var element = Selected_element(this, i, items.size)
                element.txtTitle.text = items[i].decision_title
                var param = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                param.setMargins(15, 15, 15, 15)
                linear_vertical_left.addView(element, param)
                var a: Animation = TranslateAnimation(-1000f, 0f, 0f, 0f)
                a.duration = i * 300L
                element.startAnimation(a)
            }
        }

    }

}