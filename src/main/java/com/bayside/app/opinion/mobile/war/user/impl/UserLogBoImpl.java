package com.bayside.app.opinion.mobile.war.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.mobile.war.user.dao.UserLogBoMapper;
import com.bayside.app.opinion.mobile.war.user.model.UserLogBo;
import com.bayside.app.opinion.mobile.war.user.service.UserLogBoService;
@Service("userLogBoImpl")
public class UserLogBoImpl implements UserLogBoService {
    @Autowired
	private UserLogBoMapper userLogBoMapper;
	@Override
	public int insert(UserLogBo record) {
		return userLogBoMapper.insertSelective(record);
	}

}
