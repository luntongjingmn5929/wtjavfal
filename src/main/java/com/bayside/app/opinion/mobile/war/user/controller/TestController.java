package com.bayside.app.opinion.mobile.war.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayside.app.opinion.mobile.war.user.model.User;
import com.bayside.app.opinion.mobile.war.user.service.TestService;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
@RestController
public class TestController {
	@Autowired
	private TestService testService;
	@RequestMapping(value="/test",method = RequestMethod.GET)
	public Response Test(){
		List<User> list =testService.getUser();
		return new Response(ResponseStatus.Success, list, true);
	}
}
