package com.example.inquizitivo.fragments

import android.os.Bundle
import com.example.inquizitivo.R
import com.example.inquizitivo.databinding.FragmentHighscoreBinding
import com.example.inquizitivo.databinding.FragmentQuizBindingImpl
import com.example.inquizitivo.databinding.FragmentSettingsBinding
import com.example.inquizitivo.fragments.base.BindingFragment

class HighscoreFragment : BindingFragment<FragmentHighscoreBinding>() {
    override val layout = R.layout.fragment_highscore

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}