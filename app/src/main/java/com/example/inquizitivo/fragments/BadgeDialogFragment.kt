package com.example.inquizitivo.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.Animatable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.inquizitivo.R
import com.example.inquizitivo.databinding.FragmentSplashBinding
import com.example.inquizitivo.fragments.base.BindingFragment
import com.google.android.material.button.MaterialButton

class BadgeDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        val view = inflater.inflate(R.layout.dialog_badge, container, false)

        val imageView = view.findViewById<ImageView>(R.id.iv_badge)
        val animR: AnimatedVectorDrawableCompat? = AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.like)
        animR?.let {
            imageView.setImageDrawable(it)
        }
        (imageView.drawable as Animatable).start()


        view.findViewById<MaterialButton>(R.id.finish).setOnClickListener {
            findNavController().navigate(BadgeDialogFragmentDirections.actionBadgeDialogToHomeFragment())
        }
        return view
    }
}