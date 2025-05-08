package com.substring.chat.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.substring.chat.dto.RoomDTO;
import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.services.RoomServices;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/v1/rooms")
@CrossOrigin("http://localhost:3000")
public class RoomController {
  
    //private RoomServices roomService;
    private final RoomServices roomService;

    public RoomController(RoomServices roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody RoomDTO room) {
        
        RoomDTO roomdto=roomService.createRoom(room);
        System.out.println("room is :"+roomdto);
        if(roomdto==null){
            // System.out.println("room id is"+getRoom.getId());
            return  ResponseEntity.badRequest().body("Room is already exit");
        } 
       return ResponseEntity.ok(roomdto);
    }
    
    // get room : join room
    @GetMapping("/join/{roomId}")
    public ResponseEntity<?> getRoom(@PathVariable String roomId) {
        Room room=roomService.getRoom(roomId);
        if(room==null){
            return ResponseEntity.badRequest().body("Room is not found!!!!");
        }
        return ResponseEntity.ok(room);
    }

    // get messages of the room .
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId) {
    List<Message> messages=roomService.getMessages(roomId);
    if(!messages.isEmpty()){
        System.out.println("messages are"+messages);
        // ModelMapper modalMapper=new ModelMapper();
        // Message message=modalMapper.map(messages,Message.class);
        return ResponseEntity.ok(messages);
    }
    return null;
}
}
