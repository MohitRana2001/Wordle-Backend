package com.example.wordle.service;

import com.example.wordle.domain.*;
import com.example.wordle.repository.*;
import com.example.wordle.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class WordService {

    private final WordRepository words;
    private final PlayerRepository players;
    private final GuessRepository guesses;

    public WordService(WordRepository words,
            PlayerRepository players,
            GuessRepository guesses) {
        this.words = words;
        this.players = players;
        this.guesses = guesses;
    }

    public Word getTodayWord() {
        return words.findByTargetDate(LocalDate.now())
                .orElseThrow(() -> new IllegalStateException("Word for today not selected"));
    }

    public GuessResponse evaluateGuess(UUID playerId, String guess) {
        guess = guess.toUpperCase();

        Word today = getTodayWord();

        // 1. fetch or create player
        Player player = players.findById(playerId)
                .orElseGet(() -> players.save(new Player(playerId)));

        // 2. determine attempt #
        int attempt = guesses.countByPlayerAndWord(player, today) + 1;

        String target = today.getValue();
        boolean correct = guess.equals(target);

        guesses.save(new Guess(player, today, attempt, correct));

        List<LetterState> states = new ArrayList<>(5);

        for (int i = 0; i < 5; i++) {
            char g = guess.charAt(i);
            char t = target.charAt(i);
            if (g == t)
                states.add(LetterState.CORRECT);
            else if (target.indexOf(g) >= 0)
                states.add(LetterState.PRESENT);
            else
                states.add(LetterState.ABSENT);
        }

        return new GuessResponse(states, correct);
    }
}
