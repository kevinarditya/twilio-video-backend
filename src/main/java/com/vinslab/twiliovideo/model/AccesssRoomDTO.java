package com.vinslab.twiliovideo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccesssRoomDTO {
    private String roomName;
    private String token;
}
