package com.example.inquizitivo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.inquizitivo.R
import com.example.inquizitivo.databinding.FragmentHomeBinding
import com.example.inquizitivo.fragments.base.BindingFragment
import com.example.inquizitivo.models.*
import com.example.inquizitivo.onelist_library.QuickList
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class HomeFragment : BindingFragment<FragmentHomeBinding>(), View.OnClickListener {
    override val layout = R.layout.fragment_home

    var list = arrayListOf<Quiz>()

    private var quickList: QuickList<Quiz>? = null

    override fun onBindingData() {
        quickList =
            QuickList(Quiz::class.java, binding.quizes, R.layout.item_dash_quiz, requireContext())

        val firebaseApp = FirebaseApp.initializeApp(requireContext())
        val firebaseFirestore = FirebaseFirestore.getInstance(firebaseApp ?: return)
        binding.isLoading = true

        firebaseFirestore.collection("quizzes").addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                val documents = querySnapshot?.documents ?: return@addSnapshotListener
                for (document in documents.asIterable()) {

                    val map = document.data

                    var hasQuestion = true

                    var index = 1

                    val quizDataList = QuizDatas()

                    while (hasQuestion) {
                        try {
                            val question = map?.get("vprasanje$index") as ArrayList<Any>
                            val questionString =
                                (question[0] as HashMap<String, String>)["vprasanje"]

                            val answers = arrayListOf<Answer>()

                            for (i in question.indices.iterator()) {
                                if (i == 0) continue

                                val firebaseAnswer = question[i] as HashMap<String, Any>

                                val answer = Answer(
                                    firebaseAnswer["odgovor"] as String,
                                    firebaseAnswer["jePravi"] as Boolean
                                )
                                answers.add(answer)
                            }

                            quizDataList.add(QuizData(questionString, answers))
                            index++
                        } catch (exc: Exception) {
                            hasQuestion = false
                        }
                    }

                    val quiz =  Quiz(
                        document.id,
                        document.getString("name"),
                        getCurrentDate(document.getDate("startDate") ?: Date()),
                        getCurrentDate(document.getDate("endDate") ?: Date()),
                        document.getLong("score").toString() + " točk",
                        quizDataList,
                        document.getLong("response"))

                    if(!isThisObjectSaved(list, document.id)) {
                        list.add(quiz)
                    } else {
                        updateItem(quiz)
                    }
                }

            quickList?.reload(list)
            binding.isLoading = false
        }

//        firebaseFirestore.collection("quizzes").get().addOnCompleteListener {
//            if (it.isSuccessful) {
//
//        }


        quickList?.setOnItemClickListener {
            val quiz = list[it]
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQuizFragment(quiz))
        }
    }

    private fun updateItem(firebaseQuiz: Quiz) {
        for (index in list.indices) {
            val quiz = list[index]
            if(quiz.id == firebaseQuiz.id) {
                quiz.response = firebaseQuiz.response
                quiz.endDate = firebaseQuiz.endDate
                quiz.name = firebaseQuiz.name
                quiz.quizData = firebaseQuiz.quizData
                quiz.score = firebaseQuiz.score
                quiz.startDate = firebaseQuiz.startDate
            }
        }
    }

    private fun isThisObjectSaved(list: ArrayList<Quiz>, id: String): Boolean {
        for (quiz in list) {
            if(quiz.id == id) {
                return true
            }
        }
        return false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val usr = User("Blaž V.", HomeFragmentArgs.fromBundle(arguments ?: return).companyName)

        binding.apply {
            user = usr
            clickListener = this@HomeFragment
        }
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })
    }

    public fun getCurrentDate(date: Date): String {
    val dateFormat = SimpleDateFormat("dd. MM. yyyy");
        dateFormat.timeZone = TimeZone.getTimeZone("UTC");
    return dateFormat.format(date);
}

    override fun onClick(v: View?) {
        when(v?.id ?: return) {
            R.id.btn_next -> {

            }
            R.id.presents -> {
                findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToAwardsFragment())
            }
        }
    }
}