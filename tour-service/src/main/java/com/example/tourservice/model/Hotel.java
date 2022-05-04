package com.example.tourservice.model;

import lombok.Data;

@Data
public class Hotel {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String country;
}