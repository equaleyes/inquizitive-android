package com.example.inquizitivo.fragments.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.inquizitivo.fragments.QuizStep.Companion.getInstance
import com.example.inquizitivo.models.QuizDatas
import com.stepstone.stepper.Step
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter
import com.stepstone.stepper.viewmodel.StepViewModel
import com.stepstone.stepper.viewmodel.StepViewModel.Builder

class MyStepperAdapter(fm: FragmentManager?, context: Context?, private val callback: () -> Unit) :
    AbstractFragmentStepAdapter(fm!!, context!!) {

    private var quizes: QuizDatas? = null

    fun setValues(items: QuizDatas) {
        quizes?.clear()
        quizes = items
    }

    override fun createStep(position: Int): Step {
        return getInstance(quizes!![position], callback)
    }

    override fun getCount(): Int {
        return quizes?.size ?: 0
    }

    override fun getViewModel(position: Int): StepViewModel {
        return Builder(context)
            .create()
    }



}