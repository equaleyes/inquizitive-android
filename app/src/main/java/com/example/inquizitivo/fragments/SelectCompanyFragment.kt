package com.example.inquizitivo.fragments

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.inquizitivo.R
import com.example.inquizitivo.databinding.FragmentSelectCompanyBinding
import com.example.inquizitivo.fragments.base.BindingFragment
import com.example.inquizitivo.models.Data
import com.example.inquizitivo.onelist_library.QuickList
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot


class SelectCompanyFragment : BindingFragment<FragmentSelectCompanyBinding>() {
    override val layout = R.layout.fragment_select_company


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val d = resources.getDrawable(R.drawable.search_background)
        binding.searchView.background = d

        val txtSearch =
            binding.searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        txtSearch.setTextColor(ContextCompat.getColor(requireContext(), R.color.select_company))

        val divider = DividerItemDecoration(
            requireContext(),
            DividerItemDecoration.VERTICAL
        )
        divider.setDrawable(ShapeDrawable().apply {
            intrinsicHeight = resources.getDimensionPixelOffset(R.dimen.dp_1)
            paint.color =
                ContextCompat.getColor(requireContext(), R.color.select_company) // note: currently (support version 28.0.0), we can not use tranparent color here, if we use transparent, we still see a small divider line. So if we want to display transparent space, we can set color = background color or we can create a custom ItemDecoration instead of DividerItemDecoration.
        })
        binding.rvTest.addItemDecoration(divider)


        val quickList: QuickList<Data> =
            QuickList(Data::class.java, binding.rvTest, R.layout.item_company, requireContext())

        val firebaseApp = FirebaseApp.initializeApp(requireContext())
        val firebaseFirestore = FirebaseFirestore.getInstance(firebaseApp ?: return)

        val list = arrayListOf<Data>()

        firebaseFirestore.collection("companies").get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (document in it.result!!) {
                    list.add(Data(document.getString("name")))
                }

                quickList.reload(list)
                binding.progressBar.isVisible = false
            }
        }


        quickList.setOnItemClickListener {
//            findNavController().navigate(SelectCompanyFragmentDirections.actionSelectCompanyFragmentToInsertUserCodeFragment(list[it].test))
        }
    }
}