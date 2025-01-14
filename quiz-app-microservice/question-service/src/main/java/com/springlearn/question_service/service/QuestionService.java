package com.springlearn.question_service.service;

import com.springlearn.question_service.model.Question;
import com.springlearn.question_service.model.QuestionWrapper;
import com.springlearn.question_service.model.Responses;
import com.springlearn.question_service.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo repo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> questionByType(String type) {
        try{
            return new ResponseEntity<>( repo.findByQuestionType(type), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> addQuestion(Question question) {
       repo.save(question);
       return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<String> deleteQuestion(int id) {
        repo.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.CREATED);
    }


    public ResponseEntity<Question> getQuestionById(int id) {
        return new ResponseEntity<>(repo.findById(id).get(), HttpStatus.OK);

    }


    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String questionType, Integer numQuestions) {
        List<Integer> questions = repo.findRandomQuestionsByCategory(questionType, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getOnlyQuestions(List<Integer> questionIds) {
        List<QuestionWrapper> wrapper = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Integer id : questionIds){
            questions.add(repo.findById(id).get());
        }
        for(Question q : questions){
            wrapper.add( new QuestionWrapper(q.getQuestionId(), q.getQuestionText(), q.getOption1(), q.getOption2(),
                    q.getOption3(), q.getOption4()));
        }
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }


    public ResponseEntity<Integer> getScore(List<Responses> responses) {
        int right = 0;
        for(Responses res : responses){
            Question question = repo.findById(res.getId()).get();
            if(res.getResponse().equals(question.getCorrectAnswer()))
                right++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
