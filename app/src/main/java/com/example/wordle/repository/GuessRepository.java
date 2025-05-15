package com.example.wordle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.wordle.domain.*;

public interface GuessRepository extends JpaRepository<Guess, Long> {
    int countByPlayerAndWord(Player p, Word w);

    long countByPlayer(Player p);

    long countByPlayerAndWordAndAttempt(Player p, Word w, int attempt);

    int countByPlayerAndCorrectTrue(Player p);
}
