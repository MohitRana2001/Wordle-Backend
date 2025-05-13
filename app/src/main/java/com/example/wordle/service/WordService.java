package com.example.wordle.service;

import com.example.wordle.domain.Word;
import com.example.wordle.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WordService {

    private final WordRepository repo;

    public WordService(WordRepository repo) {
        this.repo = repo;
    }

    public Word getTodayWord() {
        return repo.findByTargetDate(LocalDate.now())
                .orElseThrow(() -> new IllegalStateException("Word for today not selected"));
    }

    public boolean checkGuess(String guess) {
        return guess != null & guess.equalsIgnoreCase(getTodayWord().getValue());
    }
}
