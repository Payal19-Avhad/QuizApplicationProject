package com.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.quizapp.entity.Question;
import com.quizapp.service.QuestionService;

@RestController
public class QuestionController 
{
	@Autowired
	QuestionService questionservice;
	
	
	@GetMapping("/questions")
	public ResponseEntity<List<Question>> GetAllQuestions()
	{
		return questionservice.GetAllQuestions();
	}
	
	@GetMapping("/question/category/{category}")
	public ResponseEntity<List<Question>> GetQuestionsByCategory(@PathVariable String category)
	{
		return questionservice.GetQuestionsByCategory(category);
	}

	@PostMapping("/question/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question)
	{
		return questionservice.addQuestion(question);
		
	}
	
	@DeleteMapping("/question/delete/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer id)
	{
		return questionservice.deleteQuestion(id);
	}
	
	@PutMapping("/question/update/{id}")
	public ResponseEntity<String> updateQuestion(@RequestBody Question question,@PathVariable Integer id)
	{
		return questionservice.updateQuestion(question,id);
	}
}
