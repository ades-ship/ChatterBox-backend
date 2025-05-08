package com.substring.chat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.substring.chat.entities.Room;
import java.util.List;


public interface RoomRepository extends MongoRepository<Room,String>  {

    // find the room by room id
    public Room findByRoomId(String roomId);
    
}
