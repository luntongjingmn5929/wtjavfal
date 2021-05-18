package com.bayside.app.opinion.mobile.war.user.dao;

import java.util.List;

import com.bayside.app.opinion.mobile.war.user.model.WordSet;



public interface WordSetMapper {
    int deleteByPrimaryKey(String id);

    int insert(WordSet record);

    int insertSelective(WordSet record);

    List<WordSet> selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(WordSet record);

    int updateByPrimaryKey(WordSet record);
    
    WordSet selectPowerByName(WordSet record);
    
    int deleteByUserid(String userid);
    
    List<WordSet> selectPowerByUserId(WordSet record);
}