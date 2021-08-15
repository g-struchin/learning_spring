package com.example.learning_spring.repository;

import com.example.learning_spring.Model.Massage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MassageRepository extends CrudRepository<Massage, Integer> {

    List<Massage> findByTopic(String topic);
}
