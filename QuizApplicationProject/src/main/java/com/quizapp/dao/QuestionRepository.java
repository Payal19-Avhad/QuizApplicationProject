package com.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizapp.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>
{
   List<Question> findByCategory(String category);

   @Query(value="SELECT * FROM question q where q.category=:category ORDER BY RAND() LIMIT :num", nativeQuery = true)
   
    List<Question> findRandomQuestionByCategory(String category, int num);
	
}
