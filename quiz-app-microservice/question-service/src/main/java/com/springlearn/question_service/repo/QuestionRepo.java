package com.springlearn.question_service.repo;

import com.springlearn.question_service.model.Question;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findByQuestionType(String type);

    @Query(value = "SELECT q.question_id FROM question q Where q.question_type= :type ORDER BY RANDOM() LIMIT :numQ",
            nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String type, int numQ);
}
