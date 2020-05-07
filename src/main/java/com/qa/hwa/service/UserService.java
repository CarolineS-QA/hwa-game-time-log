package com.qa.hwa.service;

import com.qa.hwa.domain.User;
import com.qa.hwa.dto.UserDTO;
import com.qa.hwa.repo.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public final UsersRepository repo;

    public final ModelMapper mapper;

    public UserService(UsersRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }
}
