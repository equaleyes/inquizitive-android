package com.example.inquizitivo.models;

import com.example.inquizitivo.onelist_library.annotations.Text;

public class User {
    @Text(resourceName = "name")
    public String name;
    @Text(resourceName = "company")
    public String company;

    public User(String name) {
        this.name = name;
        this.company = "";
    }
    public User(String name, String company) {
        this.name = name;
        this.company = company;
    }
}
