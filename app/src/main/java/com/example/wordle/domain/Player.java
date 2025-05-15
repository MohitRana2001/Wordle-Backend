package com.example.wordle.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Player {
    @Id
    private UUID id;

    protected Player() {
    } // JPA

    public Player(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
