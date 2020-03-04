package com.bridgelabz.fundoonotes.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.DTO.UpdatePassword;
import com.bridgelabz.fundoonotes.Model.UserDemo;
@Repository
public interface UserRepository extends CrudRepository<UserDemo, Long> {

	UserDemo findFirstByOrderByUserIdDesc();

	Optional<UserDemo> findOneByUserIdAndPswd(long userId, Object pswd);

	Optional<UserDemo> findOneByEmail(String email);

	UserDemo findOneByUserId(long userId);

}