package com.springlearn.quiz_service.controller;


import com.springlearn.quiz_service.model.QuestionGen;
import com.springlearn.quiz_service.model.QuestionWrapper;
import com.springlearn.quiz_service.model.Responses;
import com.springlearn.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuestionGen gen) {
        return quizService.createQuiz(gen.getQuestionType(), gen.getNum(), gen.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestion(@PathVariable int id) {
        return quizService.getQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuestions(@PathVariable int id, @RequestBody List<Responses> responses) {
        return quizService.submitResponses(id, responses);
    }

}