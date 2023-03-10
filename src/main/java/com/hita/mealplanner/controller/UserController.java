package com.hita.mealplanner.controller;

import java.util.List;

import com.hita.mealplanner.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hita.mealplanner.model.User;
import com.hita.mealplanner.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<User> readUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value="/users/{userId}", method=RequestMethod.PUT)
    public User readUsers(@PathVariable(value = "userId") Long id, @RequestBody User user) {
        if(!userService.getUser(id).isPresent())throw new UserNotFoundException();
        return userService.updateUser(id, user);
    }

    @RequestMapping(value="/users/{userId}", method=RequestMethod.DELETE)
    public void deleteUsers(@PathVariable(value = "userId") Long id) {
        if(!userService.getUser(id).isPresent())throw new UserNotFoundException();
        userService.deleteUser(id);
    }
}
