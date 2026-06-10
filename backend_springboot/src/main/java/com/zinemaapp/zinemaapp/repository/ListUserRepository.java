package com.zinemaapp.zinemaapp.repository;

import com.zinemaapp.zinemaapp.domain.ListUser;
import com.zinemaapp.zinemaapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListUserRepository extends JpaRepository<ListUser, Long> {
    List<ListUser> findByUser(User user);
}
