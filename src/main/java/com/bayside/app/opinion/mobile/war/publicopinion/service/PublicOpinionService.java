package com.bayside.app.opinion.mobile.war.publicopinion.service;

import java.util.List;

import com.bayside.app.opinion.mobile.war.publicopinion.model.Subject;


public interface PublicOpinionService {
	List<Subject> selectByUserId(String userid);
}
