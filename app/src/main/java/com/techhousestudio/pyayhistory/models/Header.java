package com.techhousestudio.pyayhistory.models;

import java.util.List;

public class Header {
    public String name;
    public List<Education> educationList;

    public Header(String name, List<Education> educationList) {
        this.name = name;
        this.educationList = educationList;
    }
}
