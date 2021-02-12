package com.hoaxify.hoaxify.etc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StaticResourceTest {

    @Test
    public void checkStaticFolder_whenApplicationIsInitialized_uploadFolderMustExist() {
        File uploadFolder = new File("uploads-test");
        boolean uploadFolderExists = uploadFolder.exists() && uploadFolder.isDirectory();
        assertThat(uploadFolderExists).isTrue();
    }
}
