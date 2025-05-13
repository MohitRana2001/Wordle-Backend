package com.example.wordle.config;

import com.example.wordle.domain.Word;
import com.example.wordle.repository.WordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner init(WordRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                List.of("MANGO", "GRAPE", "PEACH", "APPLE", "PLUMB").forEach(w -> {
                    repo.save(new Word(w, LocalDate.now().plusDays(repo.count())));
                });
            }
        };
    }
}
