package com.example.inquizitivo.fragments

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.inquizitivo.R
import com.example.inquizitivo.databinding.FragmentFinishQuizBinding
import com.example.inquizitivo.fragments.base.BindingFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class FinishQuizFragment : BindingFragment<FragmentFinishQuizBinding>() {
    override val layout = R.layout.fragment_finish_quiz

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val quizData = FinishQuizFragmentArgs.fromBundle(arguments ?: return).quizData
        val firebaseApp = FirebaseApp.initializeApp(requireContext())
        val firebaseFirestore = FirebaseFirestore.getInstance(firebaseApp ?: return)

        val document = firebaseFirestore.collection("quizzes").document(quizData.id)

        document.update("response", quizData.response+1)
            .addOnSuccessListener {
                Log.e("SUCCESS", "SUCCESS")
            }
            .addOnFailureListener {
                Log.e("ERROR", it.message)
            }

        val animR: AnimatedVectorDrawableCompat? = AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.zvezdice_top)
        animR?.let {
            binding.ivStars.setImageDrawable(it)
        }
        (binding.ivStars.drawable as Animatable).start()

        binding.clickListener = View.OnClickListener {
        }

        binding.finish.setOnClickListener {

            findNavController().navigate(FinishQuizFragmentDirections.actionFinishQuizFragmentToBadgeDialog())
        }
    }
}