package com.whastup.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whastup.entity.User;
import com.whastup.repository.WhatsupRepository;
import com.whastup.servicei.WhatsupServiceI;

@Service
public class WhastupServiceImpl implements WhatsupServiceI {

	@Autowired
	WhatsupRepository repo;

	@Override
	public String addUser(User user) {

		repo.save(user);

		return "User" + " " + user.getuId() + " " + "added Sucessfully";
	}

	@Override
	public String addUsers(List<User> user) {

		repo.saveAll(user);

		return "All Users Added Sucessfully";
	}

	@Override
	public List<User> getUser(String userId) {

		List<User> usersId = repo.findByuId(userId);

		return usersId;
	}

	@Override
	public List<User> getAllUsers() {

		List<User> allUsers = repo.getAllUsers();

		return allUsers;

	}

	@Override
	public User updateUser(User user) {
		
		Optional<User> userid = repo.findById(user.getuId());
		if(userid.isPresent()) {
			User userId = userid.get();
			userId.setuName(user.getuName());
			userId.setuAddress(user.getuAddress());
			userId.setMobileNumber(user.getMobileNumber());
			User updatedUser = repo.save(userId);
			
			return updatedUser;
		}
		return null;
	}

	@Override
	public String deleteUser(User userId) {
		
		repo.deleteById(userId.getuId());
		
		return "UserId"+" "+userId.getuId()+" "+"deleted sucessfully";
	}

}
