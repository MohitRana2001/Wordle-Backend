package com.example.wordle.dto;

import java.util.List;

public record GuessResponse(List<LetterState> states, boolean correct) {
};
