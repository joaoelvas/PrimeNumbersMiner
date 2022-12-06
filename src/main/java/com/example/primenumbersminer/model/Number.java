package com.example.primenumbersminer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "numbers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Number {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long integer;
    String isPrime;

}
