package com.qa.hwa.service;

import com.qa.hwa.domain.User;
import com.qa.hwa.dto.UserDTO;
import com.qa.hwa.exceptions.UserNotFoundException;
import com.qa.hwa.repo.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    public final UsersRepository repo;

    public final ModelMapper mapper;

    @Autowired
    public UserService(UsersRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    public List<UserDTO> readAllUsers(){
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO createUser(User user){
        User tempUser = this.repo.save(user);
        return this.mapToDTO(tempUser);
    }

    public UserDTO findUserById(Long id){
        return this.mapToDTO(this.repo.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    public UserDTO updateUser(Long id, User user){
        User update = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
        update.setUsername(user.getUsername());
        update.setTotalTimePlayed(user.getTotalTimePlayed());
        update.setFreeTime(user.getFreeTime());
        update.setTimeRemaining(user.getTimeRemaining());
        User tempUser = this.repo.save(update);
        return this.mapToDTO(tempUser);
    }
}
