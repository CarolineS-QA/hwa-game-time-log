package com.qa.hwa.service;

import com.qa.hwa.domain.User;
import com.qa.hwa.dto.UserDTO;
import com.qa.hwa.exceptions.UserNotFoundException;
import com.qa.hwa.repo.GameSessionsRepository;
import com.qa.hwa.repo.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    public final UsersRepository usersRepo;

    public final GameSessionsRepository sessionsRepo;

    public final ModelMapper mapper;

    @Autowired
    public UserService(UsersRepository usersRepo, GameSessionsRepository sessionsRepo, ModelMapper mapper) {
        this.usersRepo = usersRepo;
        this.sessionsRepo = sessionsRepo;
        this.mapper = mapper;
    }

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    public List<UserDTO> readAllUsers(){
        return this.usersRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO readUserByUsername(String username){
        if (this.usersRepo.findUserByUsername(username) == null){
            throw new UserNotFoundException();
        }
        return this.mapToDTO(this.usersRepo.findUserByUsername(username));
    }

    public UserDTO createUser(User user){
        User tempUser = this.usersRepo.save(user);
        return this.mapToDTO(tempUser);
    }

    public UserDTO findUserById(Long id){
        return this.mapToDTO(this.usersRepo.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    public UserDTO updateUser(Long id, User user){
        User update = this.usersRepo.findById(id).orElseThrow(UserNotFoundException::new);
        //update.setUsername(user.getUsername());
        update.setTotalTimePlayed(user.getTotalTimePlayed());
        update.setFreeTime(user.getFreeTime());
        update.setTimeRemaining(user.getTimeRemaining());
        User tempUser = this.usersRepo.save(update);
        return this.mapToDTO(tempUser);
    }

    public boolean deleteUser(Long id){
        if(!this.usersRepo.existsById(id)){
            throw new UserNotFoundException();
        }
        this.usersRepo.deleteById(id);
        return this.usersRepo.existsById(id);
    }
}
