package com.fourtrashes.pokerface.core.game;

import com.fourtrashes.pokerface.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.concurrent.ConcurrentMap;

@Controller
@RequiredArgsConstructor
public class GameController {

    private final ConcurrentMap<String, Room> roomList;

    @MessageMapping(value = "/game/ready/{roomId}")
    public void ready(@DestinationVariable("roomId") Long roomId, SimpMessageHeaderAccessor header){
        Room room = roomList.get(roomId);
        GameManager gameManager = room.getGameManager();
        gameManager.ready(header.getSessionId());
        // 전달할 메세지 타입
        // 1. 모두 준비가 되었을 때 방장이 시작하면 준비된 플레이어들 전달
        // 2. 모든사람이 ready일때 자동 시작하려면 메세지 없어도 무방
        // 구현 내용에 따라 시작하는 함수 달라짐
    }

}
