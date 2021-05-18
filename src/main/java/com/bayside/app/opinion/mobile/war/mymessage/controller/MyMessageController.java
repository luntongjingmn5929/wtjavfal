package com.bayside.app.opinion.mobile.war.mymessage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayside.app.opinion.mobile.war.mymessage.model.Messagecenter;
import com.bayside.app.opinion.mobile.war.mymessage.service.MyMessageService;
import com.bayside.app.opinion.mobile.war.user.model.User;
import com.bayside.app.util.AppConstant;
import com.bayside.app.util.IpUtil;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.bayside.app.util.UuidUtil;
/**
 * <p>Title: MyMessageController</P>
 * <p>Description:留言板 </p>
 * <p>Copyright: 山东贝赛信息科技有限公司 Copyright (c) 2016</p>
 * @author liuyy
 * @version 1.0
 * @since 2016年10月12日
 */
@RestController
public class MyMessageController {
	@Autowired
   private MyMessageService myMessageServiceImpl;
	private static final Logger log = Logger.getLogger(MyMessageController.class);
	/**
	 * <p>方法名称：insertMessage</p>
	 * <p>方法描述：保存留言板信息</p>
	 * @param record
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liuyy
	 * @since  2016年10月12日
	 * <p> history 2016年10月12日 Administrator  创建   <p>
	 */
	@RequestMapping(value ="/insertMessage",method=RequestMethod.GET)
   public Response insertMessage(Messagecenter record,HttpServletRequest request,String userid){
	 // String userid = (String)request.getSession().getAttribute("userid");
		String requestAddress = request.getRequestURI();
		String ip="";
		try {
			ip = IpUtil.getIpAddr(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		  log.info(e.getMessage());
			log.error(e.getMessage(),e);
		}
		log.info("userid:"+userid+",loginip:"+ip+",requestAddress:"+requestAddress);
	   record.setUserid(userid);
	   record.setId(UuidUtil.getUUID());
	   record.setUpdateTime(new Date());
	   System.out.println(record.getContent());
	   int num = myMessageServiceImpl.insertSelective(record);
	   if(num > 0){
		   return new Response(ResponseStatus.Success,num,true);
	   }else{
		   return new Response(ResponseStatus.Error,AppConstant.responseInfo.SAVEERRO,false);
	   }
	  
   }
	
	/**
	 * 
	 * <p>方法名称：daokongMessage</p>
	 * <p>方法描述：添加导控请求</p>
	 * @param record
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liuyy
	 * @since  2016年10月18日
	 * <p> history 2016年10月18日 Administrator  创建   <p>
	 */
	@RequestMapping(value ="/daokongMessage",method=RequestMethod.POST)
	   public Response daokongMessage(Messagecenter record,HttpServletRequest request) {
		  User user = (User)request.getSession().getAttribute("user");
		  String requestAddress = request.getRequestURI();
			String ip="";
			try {
				ip = IpUtil.getIpAddr(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			 log.info(e.getMessage());
				log.error(e.getMessage(),e);
			}
			log.info("userid:"+user.getId()+",loginip:"+ip+",requestAddress:"+requestAddress);
		   record.setUserid(user.getId());
		   record.setId(UuidUtil.getUUID());
		   record.setContent("用户名："+user.getName()+","+record.getContent());
		   record.setUpdateTime(new Date());
		   int num = myMessageServiceImpl.insertSelective(record);
		   if(num > 0){
			   return new Response(ResponseStatus.Success,num,true);
		   }else{
			   return new Response(ResponseStatus.Error,AppConstant.responseInfo.SAVEERRO,false);
		   }
		  
	   }
	

	
}
