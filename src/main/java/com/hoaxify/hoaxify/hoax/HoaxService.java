package com.hoaxify.hoaxify.hoax;

import com.hoaxify.hoaxify.user.User;
import com.hoaxify.hoaxify.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

@Service
public class HoaxService {
    HoaxRepository hoaxRepository;

    UserService userService;

    HoaxService(HoaxRepository hoaxRepository, UserService userService) {
        this.hoaxRepository = hoaxRepository;
        this.userService = userService;
    }

    public Hoax save(User user, Hoax hoax) {
        hoax.setTimestamp(new Date());
        hoax.setUser(user);
        return hoaxRepository.save(hoax);
    }

    public Page<Hoax> getAllHoaxes(Pageable pageable) {
        return hoaxRepository.findAll(pageable);
    }

    public Page<Hoax> getHoaxesOfUser(String username, Pageable pageable) {
        User inDB = userService.getByUsername(username);
        return hoaxRepository.findByUser(inDB, pageable);
    }

    public Page<Hoax> getOldHoaxes(long id, Pageable pageable) {
        return hoaxRepository.findByIdLessThan(id, pageable);
    }

    public Page<Hoax> getOldHoaxesOfUser(long id, String username, Pageable pageable) {
        User inDB = userService.getByUsername(username);
        return hoaxRepository.findByIdLessThanAndUser(id, inDB, pageable);
    }

    public List<Hoax> getNewHoaxes(long id, Pageable pageable) {
        return hoaxRepository.findByIdGreaterThan(id, pageable.getSort());
    }

    public List<Hoax> getNewHoaxesOfUser(long id, String username, Pageable pageable) {
        User inDB = userService.getByUsername(username);
        return hoaxRepository.findByIdGreaterThanAndUser(id, inDB, pageable.getSort());
    }


}
