package com.example.wordle.repository;

import com.example.wordle.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {
    Optional<Word> findByTargetDate(LocalDate date);
}
