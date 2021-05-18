package com.bayside.app.opinion.mobile.war.user.service;

import java.util.List;

import com.bayside.app.opinion.mobile.war.user.bo.InforpushBo;
import com.bayside.app.opinion.mobile.war.user.model.Inforpush;


public interface InforpushService {
	int insert(Inforpush cid);
	
    int setUpdateUserid(InforpushBo infor );
    
    int updateByCidSelective(InforpushBo infor);
    
    List<Inforpush> selectUserid(Inforpush userid);
    
    List<Inforpush> seCidByuid(String userid);
    
    List<Inforpush> selectPush(InforpushBo infor);
    
    int insertSelective(InforpushBo record);

	void deleteByCid(String cid);
}
