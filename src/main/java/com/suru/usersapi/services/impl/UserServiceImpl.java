package com.suru.usersapi.services.impl;

import com.suru.usersapi.domain.UserRequest;
import com.suru.usersapi.domain.entities.User;
import com.suru.usersapi.exceptions.ApplicationException;
import com.suru.usersapi.repositories.UserRepository;
import com.suru.usersapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User createUser(UserRequest userRequest) throws Exception{
        User user = modelMapper.map(userRequest, User.class);
        user = repository.save(user);
        LOGGER.info("User created: " + user.getId());
        return user;
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public User getUserById(Long id) throws ApplicationException {
        Optional<User> byId = repository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        LOGGER.warn("User not found: " + id);
        throw new ApplicationException("User not found for ID: " + id);
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        user.setId(id);
        LOGGER.info("User updated: " + id);
        repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        LOGGER.info("User deleted: " + id);
        repository.deleteById(id);
    }
}
