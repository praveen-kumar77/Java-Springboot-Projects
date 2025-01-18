package com.springlearn.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @ElementCollection
    private List<Integer> questionIds;

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public List<Integer> getQuestionIds() {
//        return questionIds;
//    }
//
//    public void setQuestionIds(List<Integer> questions) {
//        this.questionIds = question;
//    }

}