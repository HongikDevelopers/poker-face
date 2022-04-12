package com.fourtrashes.pokerface.core.game;

import com.fourtrashes.pokerface.domain.Room;
import com.fourtrashes.pokerface.dto.BetAmountDTO;
import com.fourtrashes.pokerface.dto.PlayerStateDTO;
import com.fourtrashes.pokerface.dto.SelectCardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Controller
@RequiredArgsConstructor
public class GameController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ConcurrentMap<Integer, Room> roomList;

    // 추후 구현사항 room객체에 들어가야 할듯
    boolean isAllPlayerReady(){
        return true;
    }

    @MessageMapping(value = "/game/ready/{roomId}")
    public void ready(@DestinationVariable("roomId") Integer roomId, SimpMessageHeaderAccessor header){
        // 전달할 메세지 타입
        // 모든사람이 ready일때 자동 시작
        ConcurrentHashMap<String, Object> users = roomList.get(roomId).getUserList();
        PokerGameManager gameManager = roomList.get(roomId).getGameManager();
        if (isAllPlayerReady()) {
            gameManager.startGame(users);
            gameManager.firstDeal();

            ArrayList<Player> players = gameManager.getPlayers();
            for(Player player : players){
                // destination에 무엇이 들어가야 하는지 결정 필요
                simpMessagingTemplate.convertAndSendToUser(player.getSessionId(),"", new PlayerStateDTO(player));
            }
        }
    }

    @MessageMapping(value = "/game/firstDeal/{roomId}")
    public void firstDeal(@DestinationVariable("roomId") Integer roomId, @Payload SelectCardDTO selectCardDTO, SimpMessageHeaderAccessor header){
        PokerGameManager gameManager = roomList.get(roomId).getGameManager();
        gameManager.openAndDump(header.getSessionId(), selectCardDTO.getOpenCard(), selectCardDTO.getDump());
        if(gameManager.isAllPlayerReady()){
            gameManager.startBet();
            String nextPlayerSessionId = gameManager.getNextBetPlayer();

            //배팅할 사람에게 시그널을 줘야하는데 데이터 형식을 어떻게 해야할지 판단 부탁
            simpMessagingTemplate.convertAndSendToUser(nextPlayerSessionId, "", "");
        }
    }

    @MessageMapping(value = "/game/bet/{roomId}")
    public void bet(@DestinationVariable("roomId") Integer roomId, @Payload BetAmountDTO betAmountDTO, SimpMessageHeaderAccessor header){
        PokerGameManager gameManager = roomList.get(roomId).getGameManager();
        gameManager.bet(header.getSessionId(), betAmountDTO.getType(), betAmountDTO.getAmount());
        if(gameManager.isAllPlayerReady()){
            gameManager.deal();
            return;
        }
        String nextPlayerSessionId = gameManager.getNextBetPlayer();
        simpMessagingTemplate.convertAndSendToUser(nextPlayerSessionId, "", "");
    }

}
