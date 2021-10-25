package com.learnmodeon.controller;

import com.learnmodeon.dto.PlayerDto;
import com.learnmodeon.exception.RecordAlreadyExist;
import com.learnmodeon.model.Player;
import com.learnmodeon.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.ws.Response;
import java.util.List;
import java.util.function.Function;


@RestController
@RequestMapping("/cricket/")
public class PlayerController {

    @Autowired
    private PlayerService playerService;


    @PostMapping(value = "/player/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Player>> createPlayer(@RequestBody final PlayerDto playerDto) {
        return playerService.createPlayer(playerDto)
                .map(entity -> ResponseEntity.ok().body(entity));
    }

    @PostMapping(value = "/players/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Player> createPlayer(@RequestBody final List<PlayerDto> playerDtoList) {
        return playerService.createPlayers(playerDtoList);
    }

    @GetMapping(value = "/player/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Player>> getPlayer(@PathVariable final String id) {
        return playerService.getPlayer(id)
                .map(player -> ResponseEntity.ok().body(player))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
