package com.hoaxify.hoaxify.hoax;

import com.hoaxify.hoaxify.file.FileAttachment;
import com.hoaxify.hoaxify.file.FileAttachmentRepository;
import com.hoaxify.hoaxify.file.FileService;
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

    FileAttachmentRepository fileAttachmentRepository;

    FileService fileService;

    public HoaxService(HoaxRepository hoaxRepository, UserService userService, FileAttachmentRepository fileAttachmentRepository, FileService fileService) {
        this.hoaxRepository = hoaxRepository;
        this.userService = userService;
        this.fileAttachmentRepository = fileAttachmentRepository;
        this.fileService = fileService;
    }

    public Hoax save(User user, Hoax hoax) {
        hoax.setTimestamp(new Date());
        hoax.setUser(user);
        if(hoax.getAttachment() != null) {
            FileAttachment inDB = fileAttachmentRepository.findById(hoax.getAttachment().getId()).get();
            inDB.setHoax(hoax);
            hoax.setAttachment(inDB);
        }
        return hoaxRepository.save(hoax);
    }

    public Page<Hoax> getAllHoaxes(Pageable pageable) {
        return hoaxRepository.findAll(pageable);
    }

    public Page<Hoax> getHoaxesOfUser(String username, Pageable pageable) {
        User inDB = userService.getByUsername(username);
        return hoaxRepository.findByUser(inDB, pageable);
    }

    public Page<Hoax> getOldHoaxes(long id, String username, Pageable pageable) {
        if(username == null) {
            return hoaxRepository.findByIdLessThan(id, pageable);
        }
        User inDB = userService.getByUsername(username);
        return hoaxRepository.findByIdLessThanAndUser(id, inDB, pageable);
    }

    public List<Hoax> getNewHoaxes(long id, String username, Pageable pageable) {
        if(username == null) {
            return hoaxRepository.findByIdGreaterThan(id, pageable.getSort());
        }

        User inDB = userService.getByUsername(username);
        return hoaxRepository.findByIdGreaterThanAndUser(id, inDB, pageable.getSort());
    }

    public long getNewHoaxesCount(long id, String username) {
        if(username == null) {
            return hoaxRepository.countByIdGreaterThan(id);
        }

        User inDB = userService.getByUsername(username);
        return hoaxRepository.countByIdGreaterThanAndUser(id, inDB);
    }

    public void deleteHoax(long id) {
        Hoax hoax = hoaxRepository.getOne(id);
        if(hoax.getAttachment() != null) {
            fileService.deleteAttachmentImage(hoax.getAttachment().getName());
        }
        hoaxRepository.deleteById(id);
    }
}
