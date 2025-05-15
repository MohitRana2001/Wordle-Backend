package com.example.wordle.controller;

import com.example.wordle.service.WordService;
import org.springframework.web.bind.annotation.*;
import com.example.wordle.dto.GuessResponse;
import com.example.wordle.domain.*;
import com.example.wordle.repository.PlayerRepository;
import com.example.wordle.repository.GuessRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/word")
@CrossOrigin(origins = "http://localhost:4200")
public class WordController {

    private final WordService service;
    private final PlayerRepository players;
    private final GuessRepository guesses;

    public WordController(WordService service, PlayerRepository players, GuessRepository guesses) {
        this.service = service;
        this.players = players;
        this.guesses = guesses;
    }

    @GetMapping("/today")
    public Map<String, String> today() {
        return Map.of("wordLength", "5");
    }

    @PostMapping("/guess")
    public GuessResponse guess(@RequestBody Map<String, String> body) {
        UUID playerId = UUID.fromString(body.get("playerId"));
        String guess = body.get("guess");
        return service.evaluateGuess(playerId, guess);
    }

    @GetMapping("/stats/{playerId}")
    public Map<String, Integer> stats(@PathVariable UUID playerId) {
        Player p = players.findById(playerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        int games = (int) guesses.countByPlayer(p);
        int wins = (int) guesses.countByPlayerAndCorrectTrue(p);

        return Map.of("games", games, "wins", wins);
    }

}
