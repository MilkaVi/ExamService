package com.example.historyserver.controller;

import com.example.historyserver.model.Question;
import com.example.historyserver.repository.QuestionRepository;
import com.example.historyserver.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HistoryController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    public List<Question> getQuestions(@RequestParam int amount) {
        List<Question> questions = questionService.getAll();
        Collections.shuffle(questions);
        return questions.stream().limit(amount).collect(Collectors.toList());
    }
}
