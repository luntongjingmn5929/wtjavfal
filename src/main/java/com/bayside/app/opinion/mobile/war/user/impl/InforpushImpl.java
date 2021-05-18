package com.bayside.app.opinion.mobile.war.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.mobile.war.user.bo.InforpushBo;
import com.bayside.app.opinion.mobile.war.user.dao.InforpushMapper;
import com.bayside.app.opinion.mobile.war.user.model.Inforpush;
import com.bayside.app.opinion.mobile.war.user.service.InforpushService;
@Service("inforpushImpl")
public class InforpushImpl implements InforpushService {
    @Autowired
	private InforpushMapper inforpushMapper;
	@Override
	public int insert(Inforpush record) {
		return inforpushMapper.insert(record);
	}

	@Override
	public int setUpdateUserid(InforpushBo userid) {
		return inforpushMapper.setUpdateUserid(userid);
	}

	@Override
	public List<Inforpush> selectPush(InforpushBo record) {
		return inforpushMapper.selectPush(record);
	}

	@Override
	public List<Inforpush> selectUserid(Inforpush userid) {
		return inforpushMapper.selectUserid(userid);
	}

	@Override
	public List<Inforpush> seCidByuid(String userid) {
		return inforpushMapper.seCidByuid(userid);
	}

	@Override
	public int insertSelective(InforpushBo record) {
		return inforpushMapper.insertSelective(record);
	}

	@Override
	public int updateByCidSelective(InforpushBo record) {
		
		return inforpushMapper.updateByCidSelective(record);
	}

	@Override
	public void deleteByCid(String cid) {
		int a = inforpushMapper.deleteByCid(cid);
	}
    

}
