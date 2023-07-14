package com.ajmal.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajmal.questionservice.model.Answer;
import com.ajmal.questionservice.model.Question;
import com.ajmal.questionservice.model.QuizQuestion;
import com.ajmal.questionservice.service.QuestionsService;

@RestController
//@RequestMapping("/question")
public class QuestionsController { 
	
	@Autowired
	private QuestionsService questionsService;
	
	@GetMapping("/question")
	@ResponseBody
	public ResponseEntity<List<Question>> getAllQuestions()
	{
		return questionsService.getAllQuestions();
	}
	
	@GetMapping("/question/category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
	{    
		
		return questionsService.getQuestionsByCategory(category);
	}
	
	@PostMapping("/question")
	public  ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return questionsService.addQuestion(question);
	}
	
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam String numofques)
	{    
	
		return questionsService.getQuestionsForQuiz(category, numofques);
	}
    
    @PostMapping("/getquestions")
	public  ResponseEntity<List<QuizQuestion>> getQuestionsFromId(@RequestParam List<Integer> questionId) 
	{
		return questionsService.getQuestionsFromId(questionId);
	}
    
    @PostMapping("/getscore")
	public  ResponseEntity<Integer> getScore(@RequestParam List<Answer> answer) 
	{
		return questionsService.getScore(answer);
	}
}
