package com.example.mathserver.service;

import com.example.mathserver.model.Question;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MathService {

    private Random random = new Random();

    private int max = 100;

    public Question getRandom() {
        int a = random.nextInt(max);
        int b = random.nextInt(max);
        return new Question(a + " + " + b + " = ?", String.valueOf(a+b));
    }
}
