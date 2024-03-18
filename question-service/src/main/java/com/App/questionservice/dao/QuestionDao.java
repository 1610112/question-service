package com.App.questionservice.dao;

import com.App.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    public List<Question> findByCategory(String category);


    @Query(value = "SELECT q.id from question q where q.difficultylevel=:level ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String level, int numQ);
}
