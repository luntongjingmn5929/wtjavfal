package com.bayside.app.opinion.mobile.war.mymessage.dao;

import java.util.List;

import com.bayside.app.opinion.mobile.war.mymessage.bo.MessageCenterBo;
import com.bayside.app.opinion.mobile.war.mymessage.model.Messagecenter;

public interface MessagecenterMapper {
    int deleteByPrimaryKey(String id);

    int insert(Messagecenter record);

    int insertSelective(Messagecenter record);

    Messagecenter selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(Messagecenter record);

    int updateByPrimaryKey(Messagecenter record);
    
    List<Messagecenter> selectAllMessageByTag(Messagecenter record);
    List<Messagecenter> checkAllMessage(MessageCenterBo record);
}