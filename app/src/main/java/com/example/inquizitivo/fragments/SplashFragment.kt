package com.example.inquizitivo.fragments

import android.os.Bundle
import android.os.Handler
import androidx.navigation.fragment.findNavController
import com.example.inquizitivo.R
import com.example.inquizitivo.databinding.FragmentSplashBinding
import com.example.inquizitivo.fragments.base.BindingFragment

class SplashFragment : BindingFragment<FragmentSplashBinding>() {
    override val layout = R.layout.fragment_splash

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val handler = Handler()

        handler.postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToInsertUserCodeFragment())
        }, 2000)

    }
}