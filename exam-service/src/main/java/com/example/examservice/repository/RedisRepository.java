package com.example.examservice.repository;

import com.example.examservice.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<Question, Integer> {

}
