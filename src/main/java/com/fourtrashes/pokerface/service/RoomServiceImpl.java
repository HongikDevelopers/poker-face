package com.fourtrashes.pokerface.service;

import com.fourtrashes.pokerface.constants.ExceptionType;
import com.fourtrashes.pokerface.domain.Room;
import com.fourtrashes.pokerface.core.game.GameManagerImpl;
import com.fourtrashes.pokerface.exception.WebSocketException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final AtomicInteger idGenerator;
    private final ConcurrentHashMap<Integer, Room> roomList;
    private final Integer ROOM_LIMIT = 5;

    @Override
    public Room createRoom() {
        Integer roomId = idGenerator.getAndIncrement();
        String roomUrl = UUID.randomUUID().toString();
        Room newRoom = new Room(roomId, roomUrl, ROOM_LIMIT, new GameManagerImpl());
        roomList.put(roomId, newRoom);

        return newRoom;
    }

    @Override
    public void joinRoom(Integer roomId, Object user) {
        Room room = roomList.get(roomId);
        if (room == null) {
            throw new WebSocketException("", ExceptionType.NOT_EXIST_ROOM);
        }

        ConcurrentHashMap<String, Object> userList = room.getUserList();
        if (userList.size() == ROOM_LIMIT) {
            throw new WebSocketException("", ExceptionType.ALREADY_FULL_ROOM);
        }

        // TODO : sessionId, User로 수정 필요
        userList.put("sessionId", new Object());
    }

    @Override
    public void leaveRoom(Integer roomId, Object user) {
        Room room = roomList.get(roomId);
        if (room == null) {
            throw new WebSocketException("", ExceptionType.NOT_EXIST_ROOM);
        }

        ConcurrentHashMap<String, Object> userList = room.getUserList();
        if (userList.size() <= 0) {
            throw new WebSocketException("", ExceptionType.ALREADY_EXIT_ROOM);
        }

        if (userList.size() == 1) {
            roomList.remove(roomId);
        }
        //TODO : userId로 삭제하도록 변경
        userList.remove("sessionId");
    }
}
