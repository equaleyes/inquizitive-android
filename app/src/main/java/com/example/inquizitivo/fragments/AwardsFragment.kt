package com.example.inquizitivo.fragments

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.inquizitivo.R
import com.example.inquizitivo.databinding.FragmentAwardsBinding
import com.example.inquizitivo.fragments.base.BindingFragment

class AwardsFragment : BindingFragment<FragmentAwardsBinding>() {
    override val layout = R.layout.fragment_awards

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(
                AwardsFragmentDirections.actionAwardsFragmentToHomeFragment())
            }
        })

    }
}