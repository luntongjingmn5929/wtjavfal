package com.bayside.app.opinion.mobile.war.user.dao;

import java.util.List;

import com.bayside.app.opinion.mobile.war.user.bo.InforpushBo;
import com.bayside.app.opinion.mobile.war.user.model.Inforpush;

public interface InforpushMapper {
    int deleteByPrimaryKey(String id);

    int insert(Inforpush record);

    int insertSelective(InforpushBo record);

    Inforpush selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Inforpush record);
    
    int updateByCidSelective(InforpushBo record);

    int updateByPrimaryKey(Inforpush record);

	int setUpdateUserid(InforpushBo userid);

	List<Inforpush> selectPush(InforpushBo record);

	List<Inforpush> selectUserid(Inforpush userid);

	List<Inforpush> seCidByuid(String userid);

	int deleteByCid(String cid);
	
}