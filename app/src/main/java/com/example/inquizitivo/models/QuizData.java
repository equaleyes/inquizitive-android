package com.example.inquizitivo.models;

import com.example.inquizitivo.onelist_library.annotations.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizData implements Serializable {

    @Text(resourceName = "name")
    public String question;
    @Text(resourceName = "startDate")
    public ArrayList<Answer> answers;

    public QuizData(String question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }
}
