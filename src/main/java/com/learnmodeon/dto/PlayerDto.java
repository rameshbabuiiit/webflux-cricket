package com.learnmodeon.dto;
import lombok.Data;

@Data
public class PlayerDto {
    private String id;
    private String name;
    private long matches;
    private long runs;
    private long wickets;
    private String playingRole;
    private String battingStyle;
    private String bowlingStyle;
}
