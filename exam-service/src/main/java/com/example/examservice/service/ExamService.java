package com.example.examservice.service;

import com.example.examservice.model.Exam;
import com.example.examservice.model.Question;
import com.example.examservice.model.Section;
import com.example.examservice.repository.RedisRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExamService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RedisRepository repository;
    ObjectMapper objectMapper = new ObjectMapper();
    private static final String HISTORY = "HISTORY";

    public Exam getExam(Map<String, Integer> spec) {
        List<Section> sections = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : spec.entrySet()) {
            String url = getUrl(entry);
            if (entry.getKey().equals(HISTORY)) {
                sections.add(new Section(getHistoryQuestion(url, entry.getValue())));
                continue;
            }
            sections.add(new Section(Arrays.asList(restTemplate.getForObject(url, Question[].class))));
        }
        return new Exam("EXAM", sections);
    }

    public ResponseEntity<Question> createQuestion(String spec, Question question) {
        String url = getUrl(Maps.immutableEntry(spec, 1));
        return restTemplate.postForEntity(url, question, Question.class);
    }

    private String getUrl(Map.Entry<String, Integer> entry) {
        return "http://" + entry.getKey() + "/api/questions?amount="+entry.getValue();
    }

    @KafkaListener(topics = "questions", groupId = "questions_id")
    public void consume(JsonNode question) throws JsonProcessingException {
        repository.save(objectMapper.treeToValue(question, Question.class));
        log.info("new question is available : {}", question);
    }

    private List<Question> getHistoryQuestion(String url, Integer amount) {
        if (repository.count() != 0) {
            List<Question> questions = Lists.newArrayList(repository.findAll());
            Collections.shuffle(questions);
            return questions.stream().limit(amount).collect(Collectors.toList());
        }
        return Arrays.stream(restTemplate.getForObject(url, Question[].class)).toList();
    }
}
