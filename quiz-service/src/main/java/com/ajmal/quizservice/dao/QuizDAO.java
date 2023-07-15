package com.ajmal.quizservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajmal.quizservice.model.Quiz;

public interface QuizDAO extends JpaRepository<Quiz, Integer>{

}
