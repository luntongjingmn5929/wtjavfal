package com.bayside.app.opinion.mobile.war.user.service;

import javax.servlet.http.HttpServletRequest;

import com.bayside.app.opinion.mobile.war.user.bo.UserBo;
import com.bayside.app.opinion.mobile.war.user.model.User;
import com.bayside.app.opinion.mobile.war.user.model.WordSet;


public interface UserService {
   User CheckLogin(String name,String password);
   User selectAll(User user);
   WordSet selectPowerByName(WordSet record);
   UserBo selectPowerByUserId(UserBo record);
   int insertSelective(User newUser, HttpServletRequest request);
   User selectSysPassword(String id);
}
