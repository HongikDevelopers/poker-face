package com.fourtrashes.pokerface.controller;

import com.fourtrashes.pokerface.dto.RoomDTO;
import com.fourtrashes.pokerface.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: 권한 관련 작업, 유저 관련 작업 추가 필요

@Api(tags = { "1.0 방 API" })
@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @ApiOperation(value = "방 생성 API", notes = "사용자가 게임방을 만듭니다.")
    @PostMapping("/room")
    public RoomDTO.BasicResponse createRoom() {
        return roomService.createRoom();
    }

    @ApiOperation(value = "방 접속 API", notes = "게임방 URL을 이용해 방에 접속합니다.")
    @GetMapping("/room/{roomURL}")
    public RoomDTO.BasicResponse joinRoom(@PathVariable(name = "roomURL") String roomURL) {
        return roomService.joinRoom(roomURL, null);
    }


    @ApiOperation(value = "방 접속종료 API", notes = "접속중인 게임방에서 사용자가 접속을 종료합니다.")
    @PostMapping("/room/{roomURL}")
    public void leaveRoom(@PathVariable(name = "roomURL") String roomURL) {
        roomService.leaveRoom(roomURL, null);
    }
}
