package com.example.inquizitivo.models;

import com.example.inquizitivo.onelist_library.annotations.Text;

import java.util.ArrayList;

public class Answer {

    @Text(resourceName = "answer")
    public String answer;
    public Boolean isTrue;

    public Answer(String answer, Boolean isTrue) {
        this.answer = answer;
        this.isTrue = isTrue;
    }

    public Answer() {
    }
}
