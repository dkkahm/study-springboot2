package com.hoaxify.hoaxify.hoax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/hoaxes")
public class HoaxController {
    @Autowired
    HoaxService hoaxService;

    @PostMapping
    public void postHoax(@RequestBody Hoax hoax) {
        hoaxService.save(hoax);
    }
}
