package com.ajmal.quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ajmal.quizservice.model.Answer;
import com.ajmal.quizservice.model.QuizQuestion;



@FeignClient("QUESTION-SERVICE")
public interface QuizInterface 
{
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numofques);

    
    @PostMapping("/getquestions")
	public  ResponseEntity<List<QuizQuestion>> getQuestionsFromId(@RequestBody List<Integer> questionId);
	
    
    @PostMapping("/getscore")
	public  ResponseEntity<Integer> getScore(@RequestBody List<Answer> answer);
}
