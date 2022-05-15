package com.example.examservice.controller;

import com.example.examservice.model.Exam;
import com.example.examservice.model.Question;
import com.example.examservice.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ExamController {

    @Autowired
    ExamService examService;

    @PostMapping("/exam")
    public Exam getExam(@RequestBody Map<String, Integer> spec) {
        return examService.getExam(spec);
    }

    @PostMapping("/create")
    public ResponseEntity<Question> getExam(@RequestParam String spec, @RequestBody Question question) {
        return examService.createQuestion(spec, question);
    }
}
