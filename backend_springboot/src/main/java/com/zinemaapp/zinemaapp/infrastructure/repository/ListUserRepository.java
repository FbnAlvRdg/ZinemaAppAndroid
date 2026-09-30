package com.zinemaapp.zinemaapp.infrastructure.repository;

import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListUserRepository extends JpaRepository<ListUserEntity, Long> {
    List<ListUserEntity> findByUser(UserEntity userEntity);
}
