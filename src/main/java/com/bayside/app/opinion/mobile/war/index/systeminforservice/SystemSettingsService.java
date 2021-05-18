package com.bayside.app.opinion.mobile.war.index.systeminforservice;

import com.bayside.app.opinion.mobile.war.index.systeminforentity.MessagePush;
import com.bayside.app.opinion.mobile.war.user.bo.InforpushBo;
import com.gexin.rp.sdk.base.IPushResult;


public interface SystemSettingsService {
	//默认推送打开    
	public  IPushResult getOpenPush(MessagePush messagePush);
	//对数据库进行更改
	int updateByCidSelective(InforpushBo record);
}
