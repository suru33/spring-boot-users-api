package com.suru.usersapi.services;

import com.suru.usersapi.domain.UserRequest;
import com.suru.usersapi.domain.entities.User;
import com.suru.usersapi.exceptions.ApplicationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User createUser(UserRequest userRequest) throws Exception;

    Page<User> getUsers(Pageable pageable);

    User getUserById(Long id) throws ApplicationException;

    void updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);
}
