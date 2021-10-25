package com.learnmodeon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Data
@NoArgsConstructor
public class Player {

    @Id
    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;
    private String name;
    private long matches;
    private long runs;
    private long wickets;
    @Column("playing_role")
    private String playingRole;
    @Column("batting_style")
    private String battingStyle;
    @Column("bowling_style")
    private String bowlingStyle;
}
