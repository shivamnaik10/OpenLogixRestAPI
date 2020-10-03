package com.openlogix.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openlogix.model.User;

@RestController
public class UserController {
    List<User> userList = new ArrayList<>();
    
    @GetMapping(value="/users", produces = {"application/json"})
    public List<User> getAllUsers(){
        return userList;
    }
    
    @PostMapping(value="/users", produces = {"application/json"})
    public User setUsers(@RequestBody Map<String, Object> body){
    	int userId = (int) body.get("userId");
        String userName = (String) body.get("name");
        int age = (int) body.get("age");
        User user = new User(userId, userName, age);
    	userList.add(user);
		return user;
    }
    
   @DeleteMapping("/users/{userId}")
   public boolean delete(@PathVariable int userId){
       int userIndex = -1;
       for(User user: userList) {
           if(user.getUserId() == userId) {
        	   userIndex = userList.indexOf(user);
               continue;
           }
       }
       if(userIndex > -1){
           userList.remove(userIndex);
           return true;
       }
       return false;
   }
	
}

