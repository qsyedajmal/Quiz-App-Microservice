package com.ajmal.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajmal.quizservice.model.Answer;
import com.ajmal.quizservice.model.QuizDTO;
import com.ajmal.quizservice.model.QuizQuestion;
import com.ajmal.quizservice.service.QuizService;

@RestController
public class QuizController {
	
	@Autowired
	private QuizService quizService; 
	
	@PostMapping("/quiz/create/category/{category}/numofques/{numofques}/title/{title}")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO)
	{
		return quizService.createQuiz(quizDTO.getCategoryName(),quizDTO.getNumQuestions(),quizDTO.getTitle());
		
	}
	
	@GetMapping("/quiz/{quiz_id}")
	public ResponseEntity<List<QuizQuestion>> getQuiz(@PathVariable Integer quiz_id)
	{
		return quizService.getQuizQuestions(quiz_id);
		
	}

	@PostMapping("/quiz/{quiz_id}")
	public ResponseEntity<String> submitQuiz(@PathVariable Integer quiz_id, @RequestBody List<Answer> answer)
	{
		return quizService.calculateResult(quiz_id,answer);
	}
}
