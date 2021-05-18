package com.bayside.app.opinion.mobile.war.user.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.mobile.war.user.dao.UserMapper;
import com.bayside.app.opinion.mobile.war.user.model.User;
import com.bayside.app.opinion.mobile.war.user.service.TestService;
@Service
public class TestServiceImpl implements TestService{
	private static Logger log = Logger.getLogger(TestServiceImpl.class); 
	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> getUser(){
		User user =new User();
		List<User> list = userMapper.selectuserInfo(user);
		log.info("你好");
		return list;
	}
}
