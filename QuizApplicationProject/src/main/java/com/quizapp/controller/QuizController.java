package com.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.entity.Question;
import com.quizapp.entity.QuestionWrapper;
import com.quizapp.entity.Response;
import com.quizapp.service.QuizService;

@RestController
public class QuizController 
{
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/quiz/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int num, @RequestParam String title)
	{
		// return new ResponseEntity<>("i am good", HttpStatus.OK);
		
		return quizService.createQuiz(category,num,title);
    }
	
	@GetMapping("/quiz/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id)
	{
	
		return quizService.getQuizQuestion(id);
	}
	
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses)
	{
		return quizService.calculateResult(id,responses);
	}
	
}