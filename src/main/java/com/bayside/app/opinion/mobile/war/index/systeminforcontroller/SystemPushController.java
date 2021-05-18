package com.bayside.app.opinion.mobile.war.index.systeminforcontroller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayside.app.opinion.mobile.war.index.systeminforentity.MessagePush;
import com.bayside.app.opinion.mobile.war.index.systeminforservice.SystemSettingsService;
import com.bayside.app.opinion.mobile.war.user.bo.InforpushBo;
import com.bayside.app.opinion.mobile.war.user.model.Inforpush;
import com.bayside.app.opinion.mobile.war.user.service.InforpushService;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

@RestController
public class SystemPushController {
	@Autowired
	private InforpushService inforpushImpl;	
	@Autowired
	private SystemSettingsService systemSettingsImpl;
    @RequestMapping(value="/getInformationPush" , method=RequestMethod.GET)
	public Response getInformationPush(HttpServletRequest request,InforpushBo inforpush){
            int openPush=systemSettingsImpl.updateByCidSelective(inforpush);
			return new Response(ResponseStatus.Success,"数据库更新完毕",true);	
    }
}