package com.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.entity.Quiz;


public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
