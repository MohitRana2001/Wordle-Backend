package com.example.wordle.repository;

import com.example.wordle.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
}
