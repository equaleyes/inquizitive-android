package com.example.inquizitivo.models;

import com.example.inquizitivo.onelist_library.annotations.Text;

public class Data {
    @Text(resourceName = "test")
    public String test;

    public Data(String test) {
        this.test = test;
    }
}
