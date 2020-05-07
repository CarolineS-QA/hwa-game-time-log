package com.qa.hwa.repo;

import com.qa.hwa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
User findUserByUsername(String username);
}
