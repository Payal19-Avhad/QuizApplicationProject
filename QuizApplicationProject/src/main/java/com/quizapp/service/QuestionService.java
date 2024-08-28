package com.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.quizapp.dao.QuestionRepository;
import com.quizapp.dao.QuizRepository;
import com.quizapp.entity.Question;

@Component
public class QuestionService 
{
	
	@Autowired
	QuestionRepository questionrepository;
	
	@Autowired
	QuizRepository quizrepo;

	public ResponseEntity<List<Question>> GetAllQuestions() {
		// TODO Auto-generated method stub
		try {
            List<Question> questions = questionrepository.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
		//return questionrepository.findAll();
	}

	public ResponseEntity<List<Question>> GetQuestionsByCategory(String category) {
		// TODO Auto-generated method stub
		try {
            List<Question> questions = questionrepository.findByCategory(category);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
		// return questionrepository.findByCategory(category);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		// TODO Auto-generated method stub\
		
		try
		{
		questionrepository.save(question);
		return new ResponseEntity<>("Questions added successfully!", HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

	public ResponseEntity<String> deleteQuestion(Integer id) {
		// TODO Auto-generated method stub
		Optional<Question> questions=questionrepository.findById(id);
		try
		{
			
			if (!questions.isPresent()) {
		        return new ResponseEntity<>(" Question is not present" , HttpStatus.NOT_FOUND);
		    }
			
		    quizrepo.deleteById(id);
			questionrepository.deleteById(id);
			
			return new ResponseEntity<>("successfully delete", HttpStatus.OK);
		}
			catch(Exception e)
			{
				e.printStackTrace();
				
				
			}
		return new ResponseEntity<>("Question is not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
		
		
		
		// questionrepository.deleteById(id);
		// return "deleted successfully" ;
	}

	public ResponseEntity<String> updateQuestion(Question question, Integer id) {
		// TODO Auto-generated method stub
		Optional<Question> questions=questionrepository.findById(id);
		
		try
		{
			
			if (!questions.isPresent()) {
		        return new ResponseEntity<>(" Question id not present" , HttpStatus.NOT_FOUND);
		    }
		
		question.setId(id);
		questionrepository.save(question);
		return new ResponseEntity<>("updated successfully!",HttpStatus.OK);
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>("not updated successfully!",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	

}
