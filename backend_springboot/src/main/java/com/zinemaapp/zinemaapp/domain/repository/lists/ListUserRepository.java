package com.zinemaapp.zinemaapp.domain.repository.lists;

import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;

import java.util.List;

public interface ListUserRepository  {
    List<ListUser> findByUser(Long userId);
    ListUser findById(Long listId);
    ListUser save(String name, Long userId);
    boolean delete(Long userId, Long listId);
}
