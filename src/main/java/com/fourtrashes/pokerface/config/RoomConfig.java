package com.fourtrashes.pokerface.config;

import com.fourtrashes.pokerface.domain.Room;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class RoomConfig {
    /**
     * 현재 생성된 Room들의 정보를 저장하고 있는 Map
     * */
    @Bean
    public ConcurrentHashMap<String, Room> roomList() {
        return new ConcurrentHashMap<>();
    }

    /**
     * 유저가 룸에 접속한 정보를 저장한 맵
     * */
    @Bean
    public ConcurrentHashMap<String, Object> usersRoomInfoList() {
        return new ConcurrentHashMap<>();
    }

    /**
     * Room Id를 고유한 integer 값으로 thread-safe 하게 생성하기 위한 도구
     * */
    @Bean
    public AtomicInteger idGenerator() {
        return new AtomicInteger();
    }
}
