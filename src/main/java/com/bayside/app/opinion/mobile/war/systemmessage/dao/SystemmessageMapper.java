package com.bayside.app.opinion.mobile.war.systemmessage.dao;

import java.util.List;

import com.bayside.app.opinion.mobile.war.systemmessage.bo.SystemMessageBo;
import com.bayside.app.opinion.mobile.war.systemmessage.model.Systemmessage;

public interface SystemmessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(Systemmessage record);

    int insertSelective(Systemmessage record);

    List<Systemmessage> selectByPrimaryKey(Systemmessage record);

    int updateByPrimaryKeySelective(Systemmessage record);

    int updateByPrimaryKey(Systemmessage record);
    
    int updateSystemMessage(SystemMessageBo record);
    Systemmessage selectTotalSessmage(Systemmessage record);
    
    
}