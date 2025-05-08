package com.substring.chat.services;

import java.time.LocalDateTime;
import java.util.List;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.substring.chat.Exception.chatAppException;
import com.substring.chat.controllers.RoomController;
import com.substring.chat.dto.RoomDTO;
import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.repositories.RoomRepository;

@Service
public class RoomServices {

    private final RoomRepository roomRepo;
    public RoomServices(RoomRepository roomRepo){
        this.roomRepo=roomRepo;
    }
    public RoomDTO createRoom(RoomDTO room) {
        // TODO Auto-generated method stub
       Room newroom=roomRepo.findByRoomId(room.getRoomId());
       if(newroom!=null){
        return null;
       } 
       System.out.println("room is in service"+room);
       newroom=new Room();
       newroom.setRoomId(room.getRoomId());
       newroom.setUser(room.getUser());
       newroom.setMessages(room.getMessages());
       
       for (Message msg : room.getMessages()) {
        if (msg.getTimeStamp() == null) {
            msg.setTimeStamp(LocalDateTime.now());
           
        }
    }
  Room createdRoom=roomRepo.save(newroom);
  System.out.println("created user..."+createdRoom.getUser());
    return RoomDTO.builder()
    .roomId(createdRoom.getRoomId())
    .id(createdRoom.getId())
    .messages(createdRoom.getMessages())
    .user(createdRoom.getUser())
    .build();

    }
  
    public Room getRoom(String roomId){
        Room room=roomRepo.findByRoomId(roomId);
        return room;
    }
    public List<Message> getMessages(String roomId) {
      Room room = roomRepo.findByRoomId(roomId);
    
      if(room!=null){
        return room.getMessages();
      } else {
        throw new chatAppException("No room is found!!!!");
      }
    }

}
