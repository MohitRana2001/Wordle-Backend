package com.example.wordle.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 5, unique = true)
    private String value;

    @Column(nullable = false, unique = true)
    private LocalDate targetDate;

    protected Word() {
    }

    public Word(String value, LocalDate targetDate) {
        this.value = value.toUpperCase();
        this.targetDate = targetDate;
    }

    public String getValue() {
        return value;
    }
}
