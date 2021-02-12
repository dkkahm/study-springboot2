package com.hoaxify.hoaxify.hoax;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HoaxService {
    HoaxRepository hoaxRepository;

    HoaxService(HoaxRepository hoaxRepository) {
        this.hoaxRepository = hoaxRepository;
    }

    public Hoax save(Hoax hoax) {
        hoax.setTimestamp(new Date());
        return hoaxRepository.save(hoax);
    }
}
