package com.blz.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blz.DTO.UpdatePassword;
import com.blz.Model.UserDemo;
@Repository
public interface UserRepository extends CrudRepository<UserDemo, Long> {

	UserDemo findFirstByOrderByUserIdDesc();

	Optional<UserDemo> findOneByUserIdAndPswd(long userId, Object pswd);

	Optional<UserDemo> findOneByEmail(String email);

	//UserDemo getUser(String Name);

 //	UserDemo getUserById(Long id);

	//boolean upDate(UpdatePassword information, Long id);

//	boolean verify(Long id);

	//List<UserDemo> getUsers();
}