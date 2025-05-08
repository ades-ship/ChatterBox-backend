package com.substring.chat.controllers;

import java.time.LocalDateTime;

import javax.management.RuntimeErrorException;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.payload.MessageRequest;
import com.substring.chat.repositories.RoomRepository;

@Controller
@CrossOrigin("http://localhost:3000")
public class ChatController {
  
   private RoomRepository roomRepo;
   public ChatController(RoomRepository roomRepo){
    this.roomRepo=roomRepo;
   }


//    sending and receiving of the messages
   @MessageMapping("/sendMessage/{roomId}")  //app/sendMessage/{roomId}
   @SendTo("/topic/room/{roomId}")//subscribe
   public Message sendMessage(@DestinationVariable String roomId,
   @RequestBody MessageRequest request){
    Room room=roomRepo.findByRoomId((request.getRoomId()));
    Message message= new Message();
    message.setContent(request.getContent());
    message.setSender(request.getSender());
     message.setTimeStamp(LocalDateTime.now());
     if(room!=null){
        room.getMessages().add(message);
        roomRepo.save(room);
     }else{
        throw new RuntimeErrorException(null, "no room is found!!!");
     }
    return message;
   }

}
