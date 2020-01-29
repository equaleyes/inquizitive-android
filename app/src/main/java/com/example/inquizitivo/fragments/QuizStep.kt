package com.example.inquizitivo.fragments

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter
import com.example.inquizitivo.R
import com.example.inquizitivo.databinding.FragmentStepBinding
import com.example.inquizitivo.fragments.base.BindingFragment
import com.example.inquizitivo.models.Answer
import com.example.inquizitivo.models.Quiz
import com.example.inquizitivo.models.QuizData
import com.example.inquizitivo.onelist_library.QuickList
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError

class QuizStep: BindingFragment<FragmentStepBinding>(), Step {
    override val layout = R.layout.fragment_step
    private var callback: (() -> Unit)? = null

    private var controller: LayoutAnimationController? = null
    companion object {
        fun getInstance(step: QuizData, callback: () -> Unit): QuizStep {
            val args = Bundle()
            args.putSerializable("step", step)

            val fragment = QuizStep()
            fragment.arguments = args
            fragment.callback = callback
            return fragment
        }
    }
    override fun onSelected() {
        binding.answers.scheduleLayoutAnimation()
    }

    override fun verifyStep(): VerificationError? {
        return null
    }

    override fun onError(error: VerificationError) {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val quizData = arguments?.getSerializable("step") as QuizData
        val quickList: QuickList<Answer> =
            QuickList(Answer::class.java, binding.answers, R.layout.item_answer, requireContext())
        quickList.reload(quizData.answers)
        controller =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fail_down)
        binding.apply {
            tvQuestion.text = quizData.question
            answers.layoutAnimation = controller
        }
        quickList.setOnItemClickListener {
            callback?.invoke()
        }


    }

    fun setCallback(callback: () -> Unit) {
        this.callback = callback
    }

    fun reloadAnimation(index: Int) {
        if(index != 0) {
            val quizData = arguments?.getSerializable("step") as QuizData
            val quickList: QuickList<Answer> =
                QuickList(
                    Answer::class.java,
                    binding.answers,
                    R.layout.item_answer,
                    requireContext()
                )
            quickList.reload(quizData.answers)
            controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fail_down)
            binding.apply {
                tvQuestion.text = quizData.question
                answers.layoutAnimation = controller
            }
            quickList.setOnItemClickListener {
                callback?.invoke()
            }
        }
    }

}