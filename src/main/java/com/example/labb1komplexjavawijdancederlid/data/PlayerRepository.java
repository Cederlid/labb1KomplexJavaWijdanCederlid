package com.example.labb1komplexjavawijdancederlid.data;

import com.example.labb1komplexjavawijdancederlid.business.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player>findByName(String name);

}

