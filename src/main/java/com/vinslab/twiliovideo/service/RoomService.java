package com.vinslab.twiliovideo.service;

import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;
import com.vinslab.twiliovideo.model.AccesssRoomDTO;
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

    public RoomService() {
    }

    public AccesssRoomDTO createRoom() {
        String roomName = UUID.randomUUID().toString();

        VideoGrant videoGrant = new VideoGrant().setRoom(roomName);


        AccessToken accessToken = new AccessToken.Builder(
                twilioAccountSid,
                twilioApiKey,
                twilioApiSecret
        ).identity("identity").grant(videoGrant).build();

        return new AccesssRoomDTO(roomName, accessToken.toJwt());
    }
}
