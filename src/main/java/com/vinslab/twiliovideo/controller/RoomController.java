package com.vinslab.twiliovideo.controller;

import com.vinslab.twiliovideo.model.AccesssRoomDTO;
import com.vinslab.twiliovideo.model.JoinRoomDTO;
import com.vinslab.twiliovideo.model.RoomDTO;
import com.vinslab.twiliovideo.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {
    private final RoomService roomService;
    private final Logger logger;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
        this.logger = LoggerFactory.getLogger(RoomController.class);
    }

    @PostMapping("/room")
    public ResponseEntity<RoomDTO> createRoom() {
        RoomDTO room = roomService.createRoom();

        return ResponseEntity.ok().body(room);
    }

    @GetMapping("/room/{roomName}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable String roomName) {
        RoomDTO roomDTO = roomService.getRoom(roomName);

        if (roomDTO.getRoomName() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(roomDTO);
    }

    @PostMapping("/room/{roomName}/join")
    public ResponseEntity<AccesssRoomDTO> joinRoom(@PathVariable String roomName, @RequestBody JoinRoomDTO joinRoomDTO) {
        AccesssRoomDTO accesssRoomDTO = roomService.join(roomName, joinRoomDTO.getUsername());

        if (accesssRoomDTO.getRoomName() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(accesssRoomDTO);
    }
}
