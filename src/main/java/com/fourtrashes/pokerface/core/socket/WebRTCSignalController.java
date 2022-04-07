package com.fourtrashes.pokerface.core.socket;

import com.fourtrashes.pokerface.constants.SignalCode;
import com.fourtrashes.pokerface.domain.Room;
import com.fourtrashes.pokerface.dto.SignalingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebRTCSignalController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ConcurrentHashMap<String, Object> usersRoomInfoList;
    private final ConcurrentHashMap<String, Room> roomList;

    // TODO : Add Payload Validator
    @MessageMapping(value = "/webrtc/room/{roomURL}")
    public void sendRtcSignal(@DestinationVariable String roomURL,
                              @Payload SignalingDTO request,
                              SimpMessageHeaderAccessor header) {

        messagingTemplate.setUserDestinationPrefix("/user");
        log.info(header.getSessionId());
        log.info(request.toString());
        String sessionId = header.getSessionId();
        isValid(roomURL, sessionId);
        String requestType = request.getType();
        SignalingDTO response = new SignalingDTO(sessionId, requestType);

        if (requestType.equals(SignalCode.Message.JOIN.getValue())) {
            messagingTemplate.convertAndSend("/sub/room/" + roomURL, response);
        } else {
            /* TODO : stack over flow 정보 보고 해결했으나 맞는 방식인지, 현재 방식처럼 헤더를 추가해서 보내는 경우
             *    destination Resolver가 어떻게 동작되는지 정확히 파악이 필요함.
             *    DOC 상에서는 userName, destination, payload 세가지 인자로 정보를 보내면
             *    UserDestinationResolver가 동작해서 SimpUserRegistry 에서 sessionId (userName)추출해서, 알맞은 end point로
             *    suffix 를 알아서 처리해준다고 하는데.. 일단 현재 상태에서는 connection 성립 이후에도 SimpUserRegistry가 항상 비어있음
             *    차후 실제 User, Login 구현 이후 SimpUserRegistry, SimpUser 구현체 구현후 빈으로 등록후 테스트 필요
             *    현재 방식은 header를 추가해서 보내는 방식인데 setSessionId를 통해 설정한 sessionId를 destination 처리해서 보내는 듯함.
             *  */
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
            headerAccessor.setSessionId(request.getTo());
            response.setSdp(request.getSdp());
            messagingTemplate.convertAndSendToUser(request.getTo(), "/queue/sub/test", response, headerAccessor.getMessageHeaders());
        }
    }

    private void isValid(String roomURL, String sessionId) {
        Object room = roomList.get(roomURL);
        Object userRoomMappingInfo = usersRoomInfoList.get(sessionId);
        if (room == null) {
            /*
             TODO 예외처리
               - 이미 종료되었거나, 없는 방인 경우 별도 메시지 내려줄 것
             */
        }

        if (userRoomMappingInfo != null) {
            /*
             * TODO 이미 접속한 방이 있는 경우
             *  방의 상태에 따라 분기처리 필요
             * 1. 게임이 진행중인 방인 경우 - 접속 가능 여부 판별후 -> 게임 재접속 or 패배 처리
             * 2. 방이 없어진 경우 해당 cash 정보 삭제
             * 3. 게임이 진행중이지 않은 방인 경우 -> 방이 꽉 찼는지 여부에 따라 분기 처리
             * */
        }
    }
}