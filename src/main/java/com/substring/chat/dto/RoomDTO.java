package com.substring.chat.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.substring.chat.entities.Message;
import com.substring.chat.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class RoomDTO {
    private String id;  // mongo db unique identifier
    private String roomId;
    private User user;
    private List<Message> messages=new ArrayList<>();
}
