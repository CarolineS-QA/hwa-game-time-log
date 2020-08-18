package com.qa.hwa.repo;

import com.qa.hwa.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, Long> {
User findUserByUsername(String username);
}
