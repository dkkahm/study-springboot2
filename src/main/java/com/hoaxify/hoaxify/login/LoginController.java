package com.hoaxify.hoaxify.login;

import com.hoaxify.hoaxify.error.ApiError;
import com.hoaxify.hoaxify.shared.CurrentUser;
import com.hoaxify.hoaxify.user.User;
import com.hoaxify.hoaxify.user.vm.UserVM;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.Map;

@RestController
public class LoginController {

    @PostMapping("/api/1.0/login")
    UserVM handleLogin(@CurrentUser User loggedInUser) {
        return new UserVM(loggedInUser);
    }
}
