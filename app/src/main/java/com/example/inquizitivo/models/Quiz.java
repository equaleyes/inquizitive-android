package com.example.inquizitivo.models;

import com.example.inquizitivo.onelist_library.annotations.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {

    @Text(resourceName = "name")
    public String name;
    @Text(resourceName = "startDate")
    public String startDate;
    @Text(resourceName = "endDate")
    public String endDate;
    @Text(resourceName = "score")
    public String score;
    public QuizDatas quizData;
    public String id;
    public Long response;


    public Quiz(String id, String name, String startDate, String endDate, String score, QuizDatas quizData, Long response)
    {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.score = score;
        this.quizData = quizData;
        this.response = response;
    }
}
