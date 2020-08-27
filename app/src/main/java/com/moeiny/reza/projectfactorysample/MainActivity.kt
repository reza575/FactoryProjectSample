package com.moeiny.reza.projectfactorysample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.moeiny.reza.optustest.repository.database.entitiy.DecisionEntity
import com.moeiny.reza.projectfactorysample.repository.model.Decision
import com.moeiny.reza.projectfactorysample.view.ShowActivity
import com.moeiny.reza.projectfactorysample.view.customview.Decision_element
import com.moeiny.reza.projectfactorysample.viewmodel.DecisionViewModel
import kotlinx.android.synthetic.main.decission_element.view.*

class MainActivity : AppCompatActivity() {
    lateinit var linear_main: LinearLayout
    lateinit var txt_next: TextView
    lateinit var viewModel: DecisionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linear_main = findViewById(R.id.ll_main)
        txt_next = findViewById(R.id.txt_next)
        viewModel = ViewModelProviders.of(this).get(DecisionViewModel::class.java)

        var elements = viewModel.getAllDecisions()
        for (i in 0..elements.size - 1) {
            var element =Decision_element(this, i, elements.size, viewModel, elements[i].decision_select)
            element.txtTitle.text = elements[i].decision_title

            var param = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

            param.setMargins(15, 15, 15, 15)
            linear_main.addView(element, param)
            var animation: Animation = TranslateAnimation(-1000f, 0f, 0f, 0f)
            animation.duration = i * 300L
            element.startAnimation(animation)
        }

        txt_next.setOnClickListener {
            var intent = Intent(this, ShowActivity::class.java)
            startActivity(intent)
        }
    }

}

