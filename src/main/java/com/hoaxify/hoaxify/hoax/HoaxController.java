package com.hoaxify.hoaxify.hoax;

import com.hoaxify.hoaxify.error.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/1.0/hoaxes")
public class HoaxController {
    @Autowired
    HoaxService hoaxService;

    @PostMapping
    public void postHoax(@Valid @RequestBody Hoax hoax) {
        hoaxService.save(hoax);
    }
}
