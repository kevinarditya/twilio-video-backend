package com.vinslab.twiliovideo.service;

import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;
import com.vinslab.twiliovideo.model.Room;
import com.vinslab.twiliovideo.model.RoomDTO;
import com.vinslab.twiliovideo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoomService {
    @Value("${twilio.account-sid}")
    private String twilioAccountSid;

    @Value("${twilio.api-key}")
    private String twilioApiKey;

    @Value("${twilio.api-secret}")
    private String twilioApiSecret;

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomDTO createRoom() {
        String roomName = UUID.randomUUID().toString();

        VideoGrant videoGrant = new VideoGrant().setRoom(roomName);

        AccessToken accessToken = new AccessToken.Builder(
                twilioAccountSid,
                twilioApiKey,
                twilioApiSecret
        ).identity("identity").grant(videoGrant).build();

        String token = accessToken.toJwt();

        Room room = new Room(roomName, token);
        roomRepository.save(room);

        return new RoomDTO(roomName);
    }
}
