package com.hoaxify.hoaxify.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "hoaxify")
@Data
public class AppConfiguration {
    String uploadPath;

    String profileImagesFolder = "profile";
    String attachmentFolder = "attachment";

    public String getFullProfileImagesPath() {
        return this.uploadPath + "/" + profileImagesFolder;
    }

    public String getFullAttachmentPath() {
        return this.uploadPath + "/" + attachmentFolder;
    }
}
