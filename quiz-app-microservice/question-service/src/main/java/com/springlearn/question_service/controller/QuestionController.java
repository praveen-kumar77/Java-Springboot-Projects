package com.springlearn.question_service.controller;


import com.springlearn.question_service.model.Question;
import com.springlearn.question_service.model.QuestionWrapper;
import com.springlearn.question_service.model.Responses;
import com.springlearn.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> allQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/questionType/{type}")
    public ResponseEntity<List<Question>> getQuestionByType(@PathVariable String type){
        return questionService.questionByType(type);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestions(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestions(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }

    @GetMapping("/updateQuestion/get/{id}")
    public ResponseEntity<Question> getQuestions(@PathVariable int id){
        return questionService.getQuestionById(id);
    }

    @PutMapping("/updateQuestion/update")
    public ResponseEntity<String> updateQuestions(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestion(@RequestParam String questionType, @RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(questionType, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        return questionService.getOnlyQuestions(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Responses> responses){
        return questionService.getScore(responses);
    }

}