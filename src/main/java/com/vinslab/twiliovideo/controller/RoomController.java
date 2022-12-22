package com.vinslab.twiliovideo.controller;

import com.vinslab.twiliovideo.model.AccesssRoomDTO;
import com.vinslab.twiliovideo.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/room")
    public ResponseEntity<AccesssRoomDTO> createRoom() {
        AccesssRoomDTO accessRoom = roomService.createRoom();

        return ResponseEntity.ok().body(accessRoom);
    }
}