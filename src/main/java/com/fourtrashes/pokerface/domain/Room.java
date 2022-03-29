package com.fourtrashes.pokerface.domain;

import com.fourtrashes.pokerface.core.game.GameManager;
import com.fourtrashes.pokerface.core.game.GameManagerImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

/**
 *  url : 방 접속 url
 *  capacity : 총 수용 가능 인원
 *  userList : 접속 중인 유저
 * */

@Data
public class Room extends BaseDomain {
    private String url;
    private Integer capacity;
    private ConcurrentHashMap<String, Object> userList;
    private GameManager gameManager;

    public Room(String url, Integer capacity) {
        this.url = url;
        this.capacity = capacity;
        this.userList = new ConcurrentHashMap<>();
        this.gameManager = new GameManagerImpl(userList);
    }
}
