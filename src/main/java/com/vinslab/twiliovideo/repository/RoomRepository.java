package com.vinslab.twiliovideo.repository;


import com.vinslab.twiliovideo.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    Room findAllByName(String roomName);
}
