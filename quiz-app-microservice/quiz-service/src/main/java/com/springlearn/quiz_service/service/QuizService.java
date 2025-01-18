package com.springlearn.quiz_service.service;

import com.springlearn.quiz_service.feign.QuizFeignInterface;
import com.springlearn.quiz_service.model.QuestionWrapper;
import com.springlearn.quiz_service.model.Quiz;
import com.springlearn.quiz_service.model.Responses;
import com.springlearn.quiz_service.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuizFeignInterface quizFeignInterface;

    public ResponseEntity<String> createQuiz(String type, int numQ, String title){

        List<Integer> questions = quizFeignInterface.generateQuestion(type, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(int id){
        Quiz question = quizRepo.findById(id).get();
        List<Integer> questionIds = question.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> qw = quizFeignInterface.getQuestionsFromId(questionIds);
        return qw;
    }

    public ResponseEntity<Integer> submitResponses(int id, List<Responses> responses) {
        return quizFeignInterface.getScore(responses);
    }



}
