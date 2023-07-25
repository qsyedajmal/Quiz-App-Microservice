package com.ajmal.quizservice.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import com.ajmal.quizapp.rest.dao.QuestionsDAO;
import com.ajmal.quizservice.dao.QuizDAO;
import com.ajmal.quizservice.feign.QuizInterface;
import com.ajmal.quizservice.model.Answer;
import com.ajmal.quizservice.model.Quiz;
import com.ajmal.quizservice.model.QuizQuestion;

@Service
public class QuizService {
	
	@Autowired
	private QuizDAO quizDAO;
	@Autowired
	private QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, Integer numofques, String title) 
	{
		
		try 
		{
			List<Integer> question = quizInterface.getQuestionsForQuiz(category, numofques).getBody();
			
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestionIds(question);
			
			quizDAO.save(quiz);
			
			return new ResponseEntity<>("Quiz created successfully",HttpStatus.OK);
		}
		catch (Exception e) 
		{
			System.out.println("Cant able to create quiz");
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<List<QuizQuestion>> getQuizQuestions(Integer quiz_id) {
		
		
		
        try
        {
        	Quiz quiz = quizDAO.findById(quiz_id).orElse(null);

    		List<Integer> questionIds= quiz.getQuestionIds();
    		
    		List<QuizQuestion> questions = quizInterface.getQuestionsFromId(questionIds).getBody();
    		
    		return new ResponseEntity<>(questions,HttpStatus.OK);
        }
        
        catch (Exception e) {
        	System.out.println("Cant able to get quiz");
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> calculateResult(Integer quiz_id, List<Answer> answer) {
		
		
		
        try
        {
        	Integer score = quizInterface.getScore(answer).getBody();
        	
        	return new ResponseEntity<>("The score is "+score, HttpStatus.OK);
        }
        
        catch (Exception e) 
        {
        	System.out.println("Cant able to calculate score");
		}
		
		return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
	}

}
