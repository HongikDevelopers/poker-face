package com.fourtrashes.pokerface.service;

import com.fourtrashes.pokerface.dto.RoomDTO;

public interface RoomService {
    RoomDTO.BasicResponse createRoom();
    RoomDTO.BasicResponse joinRoom(String roomURL, Object user);
    void leaveRoom(String roomURL, Object user);
}
