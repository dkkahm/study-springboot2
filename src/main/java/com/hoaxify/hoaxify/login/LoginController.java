package com.hoaxify.hoaxify.login;

import com.hoaxify.hoaxify.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/1.0/login")
public class LoginController {

    @PostMapping("")
    void handleLogin() {

    }
}
