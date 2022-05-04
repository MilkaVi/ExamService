package com.example.tourservice.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
public class Tour extends RepresentationModel<Tour> {
    private Integer id;
    private Date startDate;
    private Date endDate;
    private Hotel hotel;
}
