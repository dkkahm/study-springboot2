package com.hoaxify.hoaxify.hoax;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/hoaxes")
public class HoaxController {
    @PostMapping
    public void postHoax() {}
}
