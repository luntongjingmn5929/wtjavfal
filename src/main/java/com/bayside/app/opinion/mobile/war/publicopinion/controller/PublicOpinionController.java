package com.bayside.app.opinion.mobile.war.publicopinion.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayside.app.opinion.mobile.war.publicopinion.model.Subject;
import com.bayside.app.opinion.mobile.war.publicopinion.service.PublicOpinionService;
import com.bayside.app.util.AppConstant;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
/**
 * 
 * <p>Title: PublicOpinionController</P>
 * <p>Description:查询用户专题 </p>
 * <p>Copyright: 山东贝赛信息科技有限公司 Copyright (c) 2016</p>
 * @author liuyy
 * @version 1.0
 * @since 2016年12月5日
 */
@RestController
public class PublicOpinionController {
	@Autowired
	private PublicOpinionService publicOpinionImpl;
	/**
	 * 
	 * <p>方法名称：getPublicOptionSpecial</p>
	 * <p>方法描述：查询 用户所有专题</p>
	 * @param request
	 * @param userid
	 * @return
	 * @author liuyy
	 * @since  2016年12月5日
	 * <p> history 2016年12月5日 Administrator  创建   <p>
	 */
    @RequestMapping(value="/getPublicOptionSpecial",method = RequestMethod.GET)
	public  Response getPublicOptionSpecial(HttpServletRequest request,String userid){
    	//String userid = (String)request.getSession().getAttribute("userid"); 
    	List<Subject> list = publicOpinionImpl.selectByUserId(userid);
       if(list.size()>0){
    	   return new Response(ResponseStatus.Success,list,true);
       }else{
    	   return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
       }
    } 
    
}
