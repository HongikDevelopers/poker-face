package com.fourtrashes.pokerface.domain;

import com.fourtrashes.pokerface.core.game.PokerGameManager;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;

/**
 *  url : 방 접속 url
 *  capacity : 총 수용 가능 인원
 *  userList : 접속 중인 유저
 * */

@Data
public class Room extends BaseDomain {
    private Integer id;
    private String url;
    private Integer capacity;
    private ConcurrentHashMap<String, Object> userList;
    private PokerGameManager gameManager;

    public Room(Integer id, String url, Integer capacity, PokerGameManager gameManager) {
        this.id = id;
        this.url = url;
        this.capacity = capacity;
        this.userList = new ConcurrentHashMap<>();
        this.gameManager = gameManager;
    }
}
