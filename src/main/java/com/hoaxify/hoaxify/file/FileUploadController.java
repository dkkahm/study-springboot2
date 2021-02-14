package com.hoaxify.hoaxify.file;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/1.0")
public class FileUploadController {
    @PostMapping("/hoaxes/upload")
    FileAttachment uploadForHoax() {
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setDate(new Date());
        String randomName = UUID.randomUUID().toString().replaceAll("-", "");
        fileAttachment.setName(randomName);
        return fileAttachment;
    }
}
