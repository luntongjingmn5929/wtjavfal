package com.bayside.app.opinion.mobile.war.mymessage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.mobile.war.mymessage.bo.MessageCenterBo;
import com.bayside.app.opinion.mobile.war.mymessage.dao.MessagecenterMapper;
import com.bayside.app.opinion.mobile.war.mymessage.model.Messagecenter;
import com.bayside.app.opinion.mobile.war.mymessage.service.MyMessageService;
@Service("myMessageServiceImpl")
public class MyMessageServiceImpl implements MyMessageService {
    @Autowired
	private MessagecenterMapper messagecenterMapper;
	@Override
	public int insert(Messagecenter record) {
		// TODO Auto-generated method stub
		return messagecenterMapper.insert(record);
	}

	@Override
	public int insertSelective(Messagecenter record) {
		// TODO Auto-generated method stub
		return messagecenterMapper.insertSelective(record);
	}

	public int updateInfo(Messagecenter record) {
		// TODO Auto-generated method stub
		return messagecenterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Messagecenter> selectAllMessageByTag(Messagecenter record) {
		// TODO Auto-generated method stub
		return messagecenterMapper.selectAllMessageByTag(record);
	}

	@Override
	public List<Messagecenter> checkAllMessage(MessageCenterBo record) {
		// TODO Auto-generated method stub
		return messagecenterMapper.checkAllMessage(record);
	}
	

}
