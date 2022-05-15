package com.example.examservice.controller;

import com.example.examservice.model.Exam;
import com.example.examservice.model.Question;
import com.example.examservice.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ExamController {

    @Autowired
    DiscoveryClient discoveryClient;

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

    @GetMapping("/specs")
    public List<String> getSpecs() {
        List<String> specs = discoveryClient.getServices();
        specs.remove("exam-service");
        return specs;
    }
}
