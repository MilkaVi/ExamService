package com.example.tourservice.controller;

import com.example.tourservice.model.Tour;
import com.example.tourservice.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/tours")
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;

    @GetMapping
    public List<Tour> getAllTours() {
        return tourService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable Integer id) {
        Tour tour = tourService.findById(id);
        tour.add(
                linkTo(methodOn(TourController.class).getTourById(tour.getId())).withRel("getTourById"),
                linkTo(methodOn(TourController.class).createTour(tour)).withRel("createTour"),
                linkTo(methodOn(TourController.class).updateTour(tour)).withRel("updateTour"),
                linkTo(methodOn(TourController.class).deleteTour(tour.getId())).withRel("deleteTour")
        );
        return ResponseEntity.ok(tour);
    }

    @PostMapping
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {

        return ResponseEntity.ok(tourService.save(tour));    }

    @PutMapping
    public ResponseEntity<Tour> updateTour(@RequestBody Tour tour) {
        return ResponseEntity.ok(tourService.update(tour));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deleteTour(@PathVariable Integer id) {
        tourService.delete(id);
        return ResponseEntity.ok();
    }
}
