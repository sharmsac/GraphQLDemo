package com.example.demo.model;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private Address address;
    private Work work;
}