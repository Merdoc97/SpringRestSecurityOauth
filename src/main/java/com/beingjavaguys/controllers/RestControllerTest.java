package com.beingjavaguys.controllers;

import com.beingjavaguys.models.User;
import com.beingjavaguys.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author Nagesh.Chauhan
 * @version 1.01
 * @since 2016
 *
 */
@RestController
@RequestMapping("/api/users")
public class RestControllerTest {

	@Autowired
	private DataService dataService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<User> list() {
		return dataService.getUserList();
	}
}
