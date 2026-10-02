package com.zinemaapp.zinemaapp.infrastructure.persistence.repository.lists;

import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListUserJpaRepository extends JpaRepository<ListUserEntity, Long> {
    List<ListUserEntity> findByUserEntity(UserEntity userEntity);
}
