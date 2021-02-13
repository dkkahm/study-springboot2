package com.hoaxify.hoaxify.hoax;

import com.hoaxify.hoaxify.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoaxRepository extends JpaRepository<Hoax, Long> {
    Page<Hoax> findByUser(User user, Pageable pageable);

    Page<Hoax> findByIdLessThan(long id, Pageable pageable);

    List<Hoax> findByIdGreaterThan(long id, Sort sort);

    Page<Hoax> findByIdLessThanAndUser(long id, User user, Pageable pageable);
}
