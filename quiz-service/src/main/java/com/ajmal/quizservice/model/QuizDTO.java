package com.ajmal.quizservice.model;

import lombok.Data;

@Data
public class QuizDTO 
{
  private String categoryName;
  private Integer numQuestions;
  private String title;
}
