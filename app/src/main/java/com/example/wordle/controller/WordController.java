package com.example.wordle.controller;

import com.example.wordle.service.WordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/word")
@CrossOrigin(origins = "http://localhost:4200")
public class WordController {

    private final WordService service;

    public WordController(WordService service) {
        this.service = service;
    }

    @GetMapping("/today")
    public Map<String, String> today() {
        return Map.of("wordLength", "5");
    }

    @PostMapping("/guess")
    public ResponseEntity<?> guess(@RequestBody Map<String, String> body) {
        String attempt = body.get("guess");
        boolean correct = service.checkGuess(attempt);
        return ResponseEntity.ok(Map.of("correct", correct));
    }

}
