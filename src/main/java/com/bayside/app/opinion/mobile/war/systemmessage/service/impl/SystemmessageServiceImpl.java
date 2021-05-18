package com.bayside.app.opinion.mobile.war.systemmessage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.mobile.war.systemmessage.dao.SystemmessageMapper;
import com.bayside.app.opinion.mobile.war.systemmessage.model.Systemmessage;
import com.bayside.app.opinion.mobile.war.systemmessage.service.SystemmessageService;
@Service
public class SystemmessageServiceImpl implements SystemmessageService {
	@Autowired
    private SystemmessageMapper systemmessageMapper;
	@Override
	public List<Systemmessage> selectByPrimaryKey(Systemmessage record) {
		// TODO Auto-generated method stub
		return systemmessageMapper.selectByPrimaryKey(record);
	}
	@Override
	public Systemmessage selectTotalSessmage(Systemmessage record) {
		// TODO Auto-generated method stub
		return systemmessageMapper.selectTotalSessmage(record);
	}
	
}
