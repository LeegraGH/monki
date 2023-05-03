package com.example.monki.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address", columnDefinition = "text")
    private String address;
    @Column(name = "comment", columnDefinition = "text")
    private String comment;
    @Column(name = "payment")
    private String payment;
    @Column(name = "amount")
    private int amount;
    @ElementCollection
    private List<String> information;
}

