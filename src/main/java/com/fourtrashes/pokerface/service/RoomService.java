package com.fourtrashes.pokerface.service;

import com.fourtrashes.pokerface.domain.Room;

public interface RoomService {
    Room createRoom();
    void joinRoom(Integer roomId, Object user);
    void leaveRoom(Integer roomId, Object user);
}
