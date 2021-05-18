package com.bayside.app.opinion.mobile.war.systemmessage.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.bayside.app.opinion.mobile.war.systemmessage.model.Systemmessage;
import com.bayside.app.opinion.mobile.war.systemmessage.service.SystemmessageService;
import com.bayside.app.util.AppConstant;
import com.bayside.app.util.IpUtil;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.bayside.app.util.SolrPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * <p>Title: SystemmessageController</P>
 * <p>Description: 系统消息</p>
 * <p>Copyright: 山东贝赛信息科技有限公司 Copyright (c) 2016</p>
 * @author liuyy
 * @version 1.0
 * @since 2016年10月12日
 */
@RestController

public class SystemmessageController {
  @Autowired
  private SystemmessageService systemmessageServiceImpl;
  private static final Logger log = Logger.getLogger(SystemmessageController.class);

  /**
 * <p>方法名称：selectAllMessage</p>
 * <p>方法描述：查询用户所有的消息</p>
 * @param request
 * @return
 * @author liuyy
 * @since  2016年10月12日
 * <p> history 2016年10月12日 Administrator  创建   <p>
 */
@RequestMapping(value="selectAllMessage",method=RequestMethod.GET)
  public Response selectAllMessage(HttpServletRequest request,PageInfo page,String userid){
   //  String userid = (String)request.getSession().getAttribute("userid");
	String requestAddress = request.getRequestURI();
	String ip="";
	try {
		ip = IpUtil.getIpAddr(request);
	} catch (Exception e) {
		// TODO Auto-generated catch block
	System.out.println(e.getMessage());
	log.error(e.getMessage(),e);
	}
	log.info("userid:"+userid+",loginip:"+ip+",requestAddress:"+requestAddress);
     Systemmessage record  = new Systemmessage();
     record.setUserid(userid);
   // PageHelper.startPage(page.getPageNum(), page.getPageSize());
     record.setStart((page.getPageNum()-1)*page.getPageSize());
     record.setSize(page.getPageSize());
	  List<Systemmessage> list = systemmessageServiceImpl.selectByPrimaryKey(record);
	  Systemmessage ss = systemmessageServiceImpl.selectTotalSessmage(record);
	  SolrPage<Systemmessage> info = new SolrPage<Systemmessage>();
	  info.setList(list);
	  if(null!=ss){
		  info.setTotal(ss.getTotal());
	  }else{
		  info.setTotal(0);
	  }
 // PageInfo<Systemmessage> info = new PageInfo<Systemmessage>(list);
	  if(info!=null){
		  return new Response(ResponseStatus.Success,info,true);
	  }else{
		  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	  }
  }
@RequestMapping(value="selectMessageNumber",method=RequestMethod.GET)
public Response selectMessageNumber(HttpServletRequest request,String userid){
 //  String userid = (String)request.getSession().getAttribute("userid");
   //  String tag = request.getParameter("tag");
     Systemmessage record  = new Systemmessage();
     record.setUserid(userid);
     record.setTag(0);
	  List<Systemmessage> list = systemmessageServiceImpl.selectByPrimaryKey(record);
	
		 return new Response(ResponseStatus.Success,list.size(),true);
	 
}

  
  
}
