package com.substring.chat.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.substring.chat.entities.FileEntity;
import com.substring.chat.repositories.FileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    // Directory to save uploaded files
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private FileRepository fileRepository;
    // Endpoint to handle file uploads
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();

        // Check if the file is empty
        if (file.isEmpty()) {
            response.put("message", "File is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            // Create the uploads directory if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Save the file to the server
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.write(filePath, file.getBytes());

            // you can also save the file metadata to the database here
            FileEntity fileEntity=new FileEntity();
            fileEntity.setFileName(fileName);
            fileEntity.setFileType(file.getContentType());
            fileEntity.setFileSize(String.valueOf(file.getSize()));
            fileEntity.setFilePath(filePath.toString());
            fileEntity.setUploadTime(java.time.LocalDateTime.now());

            // save the file entity to the database
            fileRepository.save(fileEntity);

            // Return the file URL
            String fileUrl = "/uploads/" + fileName;
            response.put("fileUrl", fileUrl);
            response.put("message", "File uploaded successfully");
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            e.printStackTrace();
            response.put("message", "Error uploading file");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}