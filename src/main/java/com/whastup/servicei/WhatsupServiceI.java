package com.whastup.servicei;

import java.util.List;

import com.whastup.entity.User;

public interface WhatsupServiceI {
	
	public String addUser(User user);
	
	public String addUsers(List<User>user);
	
	public List<User> getUser(String userId);
	
	public List<User> getAllUsers();
	
	public User updateUser(User user);
	
	public String deleteUser(User userId);

}
