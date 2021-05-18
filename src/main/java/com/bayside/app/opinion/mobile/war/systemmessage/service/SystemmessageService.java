package com.bayside.app.opinion.mobile.war.systemmessage.service;

import java.util.List;

import com.bayside.app.opinion.mobile.war.systemmessage.model.Systemmessage;

public interface SystemmessageService {
	List<Systemmessage> selectByPrimaryKey(Systemmessage record);
	  Systemmessage selectTotalSessmage(Systemmessage record);
	
}
