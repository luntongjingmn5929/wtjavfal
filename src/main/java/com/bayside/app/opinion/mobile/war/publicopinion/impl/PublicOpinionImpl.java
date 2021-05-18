package com.bayside.app.opinion.mobile.war.publicopinion.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.mobile.war.publicopinion.dao.SubjectMapper;
import com.bayside.app.opinion.mobile.war.publicopinion.model.Subject;
import com.bayside.app.opinion.mobile.war.publicopinion.service.PublicOpinionService;
@Service("publicOpinionImpl")
public class PublicOpinionImpl implements PublicOpinionService {
	@Autowired
    private SubjectMapper subjectMapper;
	@Override
	public List<Subject> selectByUserId(String userid) {
		return subjectMapper.selectByUserId(userid);
	}

}
