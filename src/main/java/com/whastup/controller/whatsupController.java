package com.whastup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whastup.entity.User;
import com.whastup.servicei.WhatsupServiceI;

import jakarta.validation.Valid;

@RestController
@RequestMapping("admin")
public class whatsupController {

	@Autowired
	WhatsupServiceI si;
//Save single resource or create a new single Resource.
	@RequestMapping("/add/user")
	public ResponseEntity<String> addUser(@Valid @RequestBody User user, BindingResult result) {

		if (result.hasErrors()) {

			String ResponseMsg = "Invalid user " + result.getAllErrors();

			return new ResponseEntity<String>(ResponseMsg, HttpStatus.BAD_REQUEST);
		}

		String addUser = si.addUser(user);

		return new ResponseEntity<String>(addUser, HttpStatus.CREATED);

	}
//Save More than one resource.
	@PostMapping("/addAll/users")
	public ResponseEntity<String> addAllUsers(@Valid @RequestBody List<User> user, BindingResult result) {

		if (result.hasErrors()) {

			String responseMsg = "Invalid users" + result.getAllErrors();

			return new ResponseEntity<String>(responseMsg, HttpStatus.BAD_REQUEST);
		}
		String response = si.addUsers(user);

		return new ResponseEntity<String>(response, HttpStatus.CREATED);

	}
// Get Single Resource 	
	@GetMapping("/get/user/{userId}")
	public ResponseEntity<Object> getUser(@PathVariable("userId")String userId){
		
		List<User> listUser = si.getUser(userId);
		if(listUser!=null) {
			
			return new ResponseEntity<Object>(listUser,HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Object>("User id"+userId+"not exist",HttpStatus.NOT_FOUND);
		
	}
//Get All Resources.	
	@GetMapping("/get/allUsers")
	public ResponseEntity<Object> getAllUsers(){
		
		List<User> allUsers = si.getAllUsers();
		
		return new ResponseEntity<Object>(allUsers,HttpStatus.ACCEPTED);
		
	}
//Update Resource
	@PatchMapping("/update/user")
	public ResponseEntity<Object> updateUser(@Valid @RequestBody User user,BindingResult result){
		
		if(result.hasErrors()) {
			
			String Response="Invalid user"+result.getAllErrors();
			return new ResponseEntity<Object>(Response,HttpStatus.BAD_REQUEST);
		}
		User updateUser = si.updateUser(user);
		if(updateUser !=null) {
			
			return new ResponseEntity<Object>(updateUser,HttpStatus.OK);
		}
		return new ResponseEntity<Object>("User id"+user.getuId()+"does not exist",HttpStatus.NOT_FOUND);
		
	}
	@DeleteMapping("/delete/user/{userid}")
	public ResponseEntity<String> deleteUser(@PathVariable("userid") User userid){
		
		String deleteUser = si.deleteUser(userid);
		
		if(deleteUser!=null) {
			
			return new ResponseEntity<String>(deleteUser,HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("User id"+" "+userid.getuId()+" "+"does not exist",HttpStatus.NOT_FOUND);
		
	}
	
}

	
	
			
		
		
		
		
	
