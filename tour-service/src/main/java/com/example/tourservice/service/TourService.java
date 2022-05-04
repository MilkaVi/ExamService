package com.example.tourservice.service;

import com.example.tourservice.model.Tour;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TourService{

    public List<Tour> findAll() {
        List<Tour> tours = new ArrayList<>();
        return tours;
    }

    public Tour findById(Integer id) {
        //TODO
        return new Tour();
    }

    public Tour save(Tour tour) {
        //TODO
        return new Tour();
    }

    public Tour update(Tour tour) {
        //TODO
        return new Tour();
    }

    public void delete(Integer id) {
        //TODO
    }
}
