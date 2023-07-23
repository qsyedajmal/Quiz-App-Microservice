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
			
		}
		catch (Exception e) 
		{
			System.out.println("Cant able to create quiz");
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<List<QuizQuestion>> getQuizQuestions(Integer quiz_id) {
		
//		Quiz quiz = quizDAO.findById(quiz_id).orElse(null);
//		
//		List<Question> questionFromQuiz = quiz.getQuestion();
//		
		List<QuizQuestion> questionToUser = new ArrayList<>();
//		
//		for(Question q : questionFromQuiz)
//		{
//			QuizQuestion qq = new QuizQuestion(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
//			questionToUser.add(qq);
//		}
		
		return new ResponseEntity<>(questionToUser,HttpStatus.OK);
	}

	public ResponseEntity<String> calculateResult(Integer quiz_id, List<Answer> answer) {
		
//		Quiz quiz = quizDAO.findById(quiz_id).orElse(null);
//		
//		List<Question> question = quiz.getQuestion();
//		
		int score =0;
//		int i=0;
//		
//		for(Answer a : answer)
//		{
//			while (i<question.size())
//			{
//				if(a.getId().equals(question.get(i).getId()))
//				{
//					if(a.getAnswer().equals(question.get(i).getRightAnswer()))
//					{
//						score++;
//					}
//					break;
//				}
//				i++;
//			}
//			i=0;
//		}
		
		return new ResponseEntity<>("The score is "+score, HttpStatus.OK);
	}

}
