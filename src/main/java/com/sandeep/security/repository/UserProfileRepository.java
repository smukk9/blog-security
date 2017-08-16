package com.sandeep.security.repository;


import com.sandeep.security.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<User, Long> {
}
