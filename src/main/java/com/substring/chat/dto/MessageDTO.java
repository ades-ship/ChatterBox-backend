package com.substring.chat.dto;

import java.time.LocalDateTime;

import javax.swing.text.html.parser.DTD;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
   private String sender;
    private String content;
    private LocalDateTime timeStamp;
    public MessageDTO(String sender, String content){
        this.sender=sender;
        this.content=content;
        this.timeStamp=LocalDateTime.now();
       }
}
