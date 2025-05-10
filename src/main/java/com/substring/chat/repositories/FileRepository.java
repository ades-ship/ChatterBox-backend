package com.substring.chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.substring.chat.entities.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

    void save(FileEntity fileEntity);
  
    
} 