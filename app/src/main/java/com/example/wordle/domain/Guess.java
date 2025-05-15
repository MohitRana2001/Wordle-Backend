package com.example.wordle.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "guesses", uniqueConstraints = @UniqueConstraint(columnNames = { "player_id", "word_id", "attempt" }))
public class Guess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Player player;
    @ManyToOne(optional = false)
    private Word word;

    private int attempt;
    private boolean correct;

    protected Guess() {
    }

    public Guess(Player p, Word w, int attempt, boolean correct) {
        this.player = p;
        this.word = w;
        this.attempt = attempt;
        this.correct = correct;
    }
    // getters
}
