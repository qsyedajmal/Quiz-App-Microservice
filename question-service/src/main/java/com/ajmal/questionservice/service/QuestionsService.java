package com.ajmal.questionservice.service;

import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ajmal.questionservice.dao.QuestionsDAO;
import com.ajmal.questionservice.model.Answer;
import com.ajmal.questionservice.model.Question;
import com.ajmal.questionservice.model.QuizQuestion;



@Service
public class QuestionsService {
    
	@Autowired
	private QuestionsDAO questionsDAO;
	
	
	
	public ResponseEntity<List<Question>> getAllQuestions() 
	{
		try 
		{
			return new ResponseEntity<>(questionsDAO.findAll(),HttpStatus.OK);
		}
		catch (Exception e) 
		{
			System.out.println("Not able to find all questions");
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}

	
	
	
	
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		
		try 
		{
			return new ResponseEntity<>(questionsDAO.findByCategory(category),HttpStatus.OK);
		}
		catch (Exception e) 
		{
			System.out.println("Not able to find all questions by category");
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		 
	}

	
	
	
	
	
	public ResponseEntity<Question>  addQuestion(Question question) {
		
		Question q = questionsDAO.save(question);
		
		try 
		{
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(q.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
		}
		catch (Exception e) {
			System.out.println("Not able to add question");
		}
		
		return ResponseEntity.badRequest().build();
	}





	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, String numofques) {
		BigInteger numofquesint = new BigInteger(numofques);
		List<Integer> questions = questionsDAO.findRandomQuestionsByCategory(category,numofquesint);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}





	public ResponseEntity<List<QuizQuestion>> getQuestionsFromId(List<Integer> questionId) 
	{
		List<QuizQuestion> questionsForQuiz = new ArrayList<>();
		
		 
		
		for (Integer q : questionId) 
		{
			Question questionsFromDB = questionsDAO.findById(q).orElse(null);
			QuizQuestion qs =new QuizQuestion(questionsFromDB.getId(), questionsFromDB.getQuestionTitle(), questionsFromDB.getOption1(),
					questionsFromDB.getOption2(), questionsFromDB.getOption3(), questionsFromDB.getOption4());
			questionsForQuiz.add(qs);
		}
		
		return new ResponseEntity<>(questionsForQuiz, HttpStatus.OK);
	}





	public ResponseEntity<Integer> getScore(List<Answer> answer) {
		
		
		int score =0;
		int i=0;
		
		for(Answer a : answer)
		{
			Question q = questionsDAO.findById(a.getId()).orElse(null);
			

				if(a.getAnswer().equals(q.getRightAnswer()))
				{
					score++;
				}
			
		}
		
		return new ResponseEntity<>(score,HttpStatus.OK);
	}

}
 