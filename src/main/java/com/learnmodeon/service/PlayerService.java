package com.learnmodeon.service;

import com.learnmodeon.dto.PlayerDto;
import com.learnmodeon.dto.mapper.Mapper;
import com.learnmodeon.exception.RecordAlreadyExist;
import com.learnmodeon.model.Player;
import com.learnmodeon.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    Logger log = LoggerFactory.getLogger(PlayerService.class);
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private Mapper mapper;

    public Mono<Player> createPlayer(PlayerDto playerDto) {
        Player player = mapper.mapToTarget(playerDto, Player.class);
        //List<Player> players = mapper.mapList(playersDtoList, Player.class);
        return playerRepository.findById(player.getId())
                .map(player1 -> {
                    if (player != null)
                        throw new RecordAlreadyExist();
                    return player;
                })
                .switchIfEmpty(playerRepository.save(player));
    }

    public Flux<Player> createPlayers(List<PlayerDto> playerDtoList) {

        List<Player> players = mapper.mapList(playerDtoList, Player.class);
        List<String> playerIds = players.stream().map(player1 -> {
            return player1.getId();
        }).collect(Collectors.toList());
        Flux<Player> dbPlayersList = playerRepository.findAllById(Flux.fromIterable(playerIds)).mapNotNull(player -> {
            throw new RecordAlreadyExist();
        });
        return dbPlayersList.doOnComplete(() -> playerRepository.saveAll(players).subscribe()).switchIfEmpty(Flux.fromIterable(players));
    }

    public Mono<Player> getPlayer(String id) {
        return playerRepository.findById(id);
    }
}
