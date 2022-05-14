package com.example.historyserver.service;

import com.example.historyserver.model.Question;
import com.example.historyserver.repository.QuestionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    KafkaTemplate<String, JsonNode> kafkaTemplate;
    @Autowired
    private QuestionRepository questionRepository;
    ObjectMapper mapper = new ObjectMapper();

    @CircuitBreaker(name = "historyService", fallbackMethod = "buildFallbackHistoryList")
    @Bulkhead(name = "bulkheadHistoryService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "buildFallbackHistoryList")
    @RateLimiter(name = "historyService", fallbackMethod = "buildFallbackHistoryList")
    @Retry(name = "retryHistoryService", fallbackMethod = "buildFallbackHistoryList")
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question create(Question question) {
        JsonNode node = mapper.convertValue(question, JsonNode.class);
        kafkaTemplate.send("questions", node);
        return questionRepository.save(question);
    }

    private List<Question> buildFallbackHistoryList(Throwable t) {
        Question question = new Question(1, "Счастливый вопрос: как называется экзаменационный предмет?", "История");
        return Collections.singletonList(question);
    }
}
