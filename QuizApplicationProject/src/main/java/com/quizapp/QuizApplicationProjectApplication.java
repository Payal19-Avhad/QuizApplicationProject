package com.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizApplicationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplicationProjectApplication.class, args);
	}

}


// this is the url to create quiz in db in our postman we have to write this url to create quiz ok
//http://localhost:8083/quiz/create?category=python&num=3&title=jQuiz2