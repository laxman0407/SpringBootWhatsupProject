package com.whastup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.whastup.entity.User;


public interface WhatsupRepository extends CrudRepository<User, String> {

	
	public List<User> findByuId(String uId);
	
	@Query("select user from User user")
	public List<User> getAllUsers();
}
