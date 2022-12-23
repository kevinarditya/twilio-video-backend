package com.vinslab.twiliovideo.controller;

import com.vinslab.twiliovideo.model.AccesssRoomDTO;
import com.vinslab.twiliovideo.model.RoomDTO;
import com.vinslab.twiliovideo.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/room")
    public ResponseEntity<RoomDTO> createRoom() {
        RoomDTO room = roomService.createRoom();

        return ResponseEntity.ok().body(room);
    }

    @GetMapping("/room/{roomName}")
    public ResponseEntity<AccesssRoomDTO> getRoom(@PathVariable String roomName, @RequestParam String username) {
        AccesssRoomDTO accessRoom = roomService.getRoom(roomName, username);

        if (accessRoom.getRoomName() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(accessRoom);
    }
}
