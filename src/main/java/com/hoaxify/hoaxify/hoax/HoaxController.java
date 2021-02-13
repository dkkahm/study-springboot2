package com.hoaxify.hoaxify.hoax;

import com.hoaxify.hoaxify.error.ApiError;
import com.hoaxify.hoaxify.hoax.vm.HoaxVM;
import com.hoaxify.hoaxify.shared.CurrentUser;
import com.hoaxify.hoaxify.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/1.0")
public class HoaxController {
    @Autowired
    HoaxService hoaxService;

    @PostMapping("/hoaxes")
    public HoaxVM postHoax(@Valid @RequestBody Hoax hoax, @CurrentUser User user) {
        return new HoaxVM(hoaxService.save(user, hoax));
    }

    @GetMapping("/hoaxes")
    public Page<HoaxVM> getHoaxes(Pageable pageable) {
        return hoaxService.getAllHoaxes(pageable).map(HoaxVM::new);
    }

    @GetMapping("/users/{username}/hoaxes")
    public Page<HoaxVM> getHoaxesOfUser(@PathVariable String username, Pageable pageable) {
        return hoaxService.getHoaxesOfUser(username, pageable).map(HoaxVM::new);
    }

    @GetMapping("/hoaxes/{id:[0-9]+}")
    ResponseEntity<?> getHoaxesRelative(@PathVariable long id, Pageable pageable,
                                             @RequestParam(name="direction", defaultValue = "after") String direction) {
        if(!direction.equals("after")) {
            return ResponseEntity.ok(hoaxService.getOldHoaxes(id, pageable).map(HoaxVM::new));
        } else {
            List<HoaxVM> newHoaxes = hoaxService.getNewHoaxes(id, pageable).stream().map(HoaxVM::new).collect(Collectors.toList());
            return ResponseEntity.ok(newHoaxes);
        }
    }

    @GetMapping("/users/{username}/hoaxes/{id:[0-9]+}")
    public Page<HoaxVM> getHoaxesRelativeForUser(@PathVariable String username, @PathVariable long id, Pageable pageable) {
        return hoaxService.getHoaxesOfUser(id, username, pageable).map(HoaxVM::new);
    }
}
