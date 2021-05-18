package com.bayside.app.opinion.mobile.war.publicopinion.dao;

import org.apache.ibatis.annotations.Param;

import com.bayside.app.opinion.mobile.war.publicopinion.model.SubjectMAarticle;


public interface SubjectMAarticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubjectMAarticle record);

    int insertSelective(SubjectMAarticle record);

    SubjectMAarticle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubjectMAarticle record);

    int updateByPrimaryKey(SubjectMAarticle record);
    /**
	 * 
	 * <p>方法名称：updateCollection</p>
	 * <p>方法描述：收藏</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @since  2016年12月6日
	 * <p> history 2016年12月6日 Administrator  创建   <p>
	 */
	int updateCollection(SubjectMAarticle record);
	SubjectMAarticle selectDetailInfo(@Param("id")String id,@Param("articleid")String articleid);
	SubjectMAarticle selectSimidsById(String id);
}