package com.vinslab.twiliovideo.service;

import com.twilio.exception.ApiException;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;
import com.twilio.rest.video.v1.Room;
import com.vinslab.twiliovideo.model.AccesssRoomDTO;
import com.vinslab.twiliovideo.model.RoomDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger;

    public RoomService() {
        this.logger = LoggerFactory.getLogger(RoomService.class);
    }

    public RoomDTO createRoom() {
        String roomName = UUID.randomUUID().toString();

        try{
            Room.fetcher(roomName).fetch();
        } catch(ApiException error) {
            logger.info("Create Room {}", roomName);
            Room.creator().setUniqueName(roomName).create();
        }

        return new RoomDTO(roomName);
    }

    public AccesssRoomDTO getRoom(String roomName, String username) {
        try {
            Room.fetcher(roomName).fetch();

            VideoGrant videoGrant = new VideoGrant().setRoom(roomName);

            AccessToken accessToken = new AccessToken.Builder(
                    twilioAccountSid,
                    twilioApiKey,
                    twilioApiSecret
            ).identity(username).grant(videoGrant).build();

            String token = accessToken.toJwt();

            logger.info("Generate token for username {} in room {}", username, roomName);

            return new AccesssRoomDTO(roomName, token);
        } catch(ApiException error) {
            logger.info("Room Not Found");
            return new AccesssRoomDTO();
        }
    }
}
