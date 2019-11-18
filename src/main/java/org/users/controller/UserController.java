package org.users.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.users.domain.User;
import org.users.repository.UserRepository;

import javax.validation.constraints.NotNull;

@Api(value = "Users API", tags = "Users", description = "Users API")
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@ApiOperation(value = "create user", response = User.class)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@ApiParam(name = "user", required = true) @RequestBody User user){
		if (StringUtils.isEmpty(user.getId()) || userRepository.exists(user.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		userRepository.save(user);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}

	@ApiOperation(value = "get users", response = User.class, responseContainer = "list")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userRepository.findAll();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(users, httpHeaders, HttpStatus.OK);
	}

	@ApiOperation(value = "get user", response = User.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<User> getUser(@NotNull @ApiParam(required = true) @PathVariable("id") String id){
		if (!userRepository.exists(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		User user = userRepository.findOne(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "update user", response = User.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> updateUser(@NotNull @ApiParam(required = true) @PathVariable("id") String id,
										   @ApiParam(name = "user", required = true) @RequestBody User user){
		if (!id.equals(user.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (!userRepository.exists(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		User updatedUser = userRepository.save(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@ApiOperation(value = "delete user", response = ResponseEntity.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<HttpStatus> deleteUser(@NotNull @ApiParam(required = true) @PathVariable("id") String id){
		if (!userRepository.exists(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
