package com.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.quizapp.dao.QuestionRepository;
import com.quizapp.dao.QuizRepository;
import com.quizapp.entity.Question;
import com.quizapp.entity.QuestionWrapper;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.Response;


@Component
public class QuizService 
{
   
   @Autowired	
   QuizRepository quizrepo;
   
   @Autowired
   QuestionRepository questionrepo;
   
	public ResponseEntity<String> createQuiz(String category, int num, String title) {
		// TODO Auto-generated method stub
		
		List<Question> questions=questionrepo.findRandomQuestionByCategory(category, num);
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizrepo.save(quiz);
	
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id) {
		// TODO Auto-generated method stub
		Optional<Quiz> quiz= quizrepo.findById(id);
	    List<Question> questionfromDB=quiz.get().getQuestions();
	    List<QuestionWrapper> queforUser=new ArrayList<>();
	    for(Question q: questionfromDB)
	    {
	    	QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
	        queforUser.add(qw);
	    }
	     
	    return new ResponseEntity<>(queforUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
		// TODO Auto-generated method stub
		
		Quiz quiz=quizrepo.findById(id).get();
		List<Question> questions=quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response response : responses)
		{
			if(questions != null && response.getResponse().equals(questions.get(i).getRightAnswer()))
			{
			right++;
			}
			
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
