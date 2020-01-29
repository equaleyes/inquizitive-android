package com.example.inquizitivo.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.inquizitivo.R
import com.example.inquizitivo.databinding.FragmentInsertUserCodeBinding
import com.example.inquizitivo.fragments.base.BindingFragment

class InsertUserCodeFragment : BindingFragment<FragmentInsertUserCodeBinding>() {
    override val layout = R.layout.fragment_insert_user_code

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments ?: return
        val comName = InsertUserCodeFragmentArgs.fromBundle(arguments ?: return).companyName
        binding.apply {
            companyName = comName
            btnNext.setOnClickListener {
                //TODO uncomment here
//                if(etUserCode.text?.isNotEmpty() == true) {
                    findNavController().navigate(
                        InsertUserCodeFragmentDirections.actionInsertUserCodeFragmentToHomeFragment(
                            comName
                        )
                    )
//                } else {
//                    Toast.makeText(requireContext(), "Pozabili ste vpisati vstopno kodo.", Toast.LENGTH_LONG).show()
//                }
            }
        }
    }
}