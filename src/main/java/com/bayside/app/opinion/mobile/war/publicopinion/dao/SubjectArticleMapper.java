package com.bayside.app.opinion.mobile.war.publicopinion.dao;

import com.bayside.app.opinion.mobile.war.publicopinion.model.SubjectArticle;

public interface SubjectArticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubjectArticle record);

    int insertSelective(SubjectArticle record);

    SubjectArticle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubjectArticle record);

    int updateByPrimaryKeyWithBLOBs(SubjectArticle record);

    int updateByPrimaryKey(SubjectArticle record);
    
    
}