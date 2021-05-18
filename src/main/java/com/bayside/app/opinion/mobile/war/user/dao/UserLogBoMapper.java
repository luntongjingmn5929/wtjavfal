package com.bayside.app.opinion.mobile.war.user.dao;

import com.bayside.app.opinion.mobile.war.user.model.UserLogBo;

public interface UserLogBoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserLogBo record);

    int insertSelective(UserLogBo record);

    UserLogBo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserLogBo record);

    int updateByPrimaryKey(UserLogBo record);
    
    
}