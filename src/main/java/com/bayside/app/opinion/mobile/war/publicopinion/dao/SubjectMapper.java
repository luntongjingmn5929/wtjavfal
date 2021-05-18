package com.bayside.app.opinion.mobile.war.publicopinion.dao;

import java.util.List;

import com.bayside.app.opinion.mobile.war.publicopinion.model.Subject;


public interface SubjectMapper {
    int deleteByPrimaryKey(String id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKeyWithBLOBs(Subject record);

    int updateByPrimaryKey(Subject record);
    
    List<Subject> selectByUserId(String userid);
}