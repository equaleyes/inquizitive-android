package com.example.inquizitivo.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.inquizitivo.R
import com.example.inquizitivo.databinding.FragmentQuizBinding
import com.example.inquizitivo.databinding.FragmentQuizBindingImpl
import com.example.inquizitivo.databinding.FragmentSettingsBinding
import com.example.inquizitivo.fragments.adapter.MyStepperAdapter
import com.example.inquizitivo.fragments.base.BindingFragment
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError

class QuizFragment : BindingFragment<FragmentQuizBinding>() {
    override val layout = R.layout.fragment_quiz

    var index = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val quizData = QuizFragmentArgs.fromBundle(arguments ?: return).quizData.quizData
        val stepperAdapter = MyStepperAdapter(fragmentManager, requireContext()) {
            binding.stepperLayout.proceed()
        }
        stepperAdapter.setValues(quizData)

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(
                    QuizFragmentDirections.actionQuizFragmentToHomeFragment())
            }
        })


        binding.apply {
            stepperLayout.adapter = stepperAdapter
            stepperLayout.setListener(object: StepperLayout.StepperListener {
                override fun onStepSelected(newStepPosition: Int) {

                    (stepperAdapter.findStep(index) as QuizStep).reloadAnimation(newStepPosition)
                    index++
                }

                override fun onError(verificationError: VerificationError?) {
                }

                override fun onReturn() {

                }

                override fun onCompleted(completeButton: View?) {
                    findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToFinishQuizFragment(QuizFragmentArgs.fromBundle(arguments ?: return).quizData))
                }
            })
        }
    }
}