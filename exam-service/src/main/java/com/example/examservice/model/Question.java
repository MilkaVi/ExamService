package com.example.examservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("questions")
public class Question {
    @JsonIgnore
    private Long id;

    private String question;
    private String answer;
}
