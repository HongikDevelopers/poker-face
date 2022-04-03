package com.fourtrashes.pokerface.core.socket;

import com.fourtrashes.pokerface.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketEventHandler {
//    private final SimpMessagingTemplate messagingTemplate;
//    private final RoomService roomService;

    /*
    SessionConnectEvent : Websocket의 서브 프로토콜인 Simple Messaging 프로토콜
    (stomp와 같은)로 connect Request가 오는 경우 발생함 (실제 소켓이 connection 되는 이벤트를 잡는게 아님.
    TODO : 차후 기능 추가 필요
    */
    @EventListener
    public void webSocketConnectListener(SessionConnectEvent event) {
        log.info("@@@ init socket");
        event.getMessage();
    }

    @EventListener
    public void webSocketDisconnectListener(SessionDisconnectEvent event) {
        log.info("@@@ disconnect socket");
        SimpMessageHeaderAccessor.wrap(event.getMessage());
    }
}
