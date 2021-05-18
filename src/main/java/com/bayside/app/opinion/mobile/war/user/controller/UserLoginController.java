package com.bayside.app.opinion.mobile.war.user.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayside.app.opinion.mobile.war.user.bo.InforpushBo;
import com.bayside.app.opinion.mobile.war.user.bo.UserBo;
import com.bayside.app.opinion.mobile.war.user.model.Inforpush;
import com.bayside.app.opinion.mobile.war.user.model.User;
import com.bayside.app.opinion.mobile.war.user.service.InforpushService;
import com.bayside.app.opinion.mobile.war.user.service.UserLogBoService;
import com.bayside.app.opinion.mobile.war.user.service.UserService;
import com.bayside.app.util.AppConstant;
import com.bayside.app.util.IpUtil;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.bayside.app.util.UuidUtil;

@RestController
public class UserLoginController {
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private UserLogBoService userLogBoImpl;
	@Autowired
	private InforpushService inforpushImpl;
	private static final Logger log = Logger.getLogger(UserLoginController.class);
	@RequestMapping(value = "/startLogin",method = RequestMethod.GET)
	public Response StartLogin(HttpServletResponse response,HttpServletRequest request,HttpSession session,User user){
		Boolean flag = true;
		User u = userServiceImpl.selectAll(user);
		String requestAddress = request.getRequestURI();
		String ipi="";
		try {
			ipi = IpUtil.getIpAddr(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		 UserBo ub = new UserBo();
		 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		
		if(null!=u){
			log.info("userid:"+u.getId()+",loginip:"+ipi+",requestAddress:"+requestAddress);
		}
		
		if(null!=u){
		    BeanUtils.copyProperties(u, ub);
			
			if (null!=u.getExpirydate()) {
				String time = sd.format(u.getExpirydate());
				ub.setExpirydate(time);
			}
			 Date endTime = new Date();
				String etime = sd.format(endTime);
				String pirtime = sd.format(u.getExpirydate());
				try {
					Date date_01 = sd.parse(etime);
					Date date_02 = sd.parse(pirtime);
					if(date_01.after(date_02) == true){
						ub.setIdcard("0");
					}else{
						ub.setIdcard("1");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					log.info(e.getMessage());
				}
			session.setAttribute("userid", u.getId());
			session.setAttribute("user", u);
			session.setAttribute("name", u.getName());
			session.setAttribute("usertype", u.getUsertype());
			List<String> formatslist = new ArrayList<String>();
 			String[] mediaType = AppConstant.mediaType.listformats;
			for(int i=0;i<mediaType.length;i++){
				formatslist.add(mediaType[i]);
			}
 			if(null!=ub.getSetTrade()){
 				if(1==ub.getSetTrade()){
 					formatslist.add(AppConstant.mediaType.TRADE);
 					session.setAttribute("setTrade", ub.getSetTrade());
 				}else{
 					session.setAttribute("setTrade", 0);
 				}
 			}else{
 				session.setAttribute("setTrade", 0);
 			}
 			session.setAttribute("formatslist",formatslist);
			if(flag){
				int num = userServiceImpl.insertSelective(u, request);
			}
			
		}else{
			flag = false;
		}
		//
		
		if(flag){
			return new Response(ResponseStatus.Success,ub, true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	}
	/**
	 * 
	 * <p>方法名称：selectUserById</p>
	 * <p>方法描述：查询我的报纸密码</p>
	 * @param request
	 * @return
	 * @author liuyy
	 * @since  2017年7月17日
	 * <p> history 2017年7月17日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectUserById", method = RequestMethod.GET)
	public Response selectUserById(String userid){
		User user = userServiceImpl.selectSysPassword(userid);
		if(null !=user){
			return new Response(ResponseStatus.Success, user.getAddress(), true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO, false);
		}
	}
	@RequestMapping(value="/getUserPower",method=RequestMethod.GET)
	public Response getUserPower(String id){
		UserBo ub = new UserBo();
		ub.setId(id);
		UserBo us = userServiceImpl.selectPowerByUserId(ub);
		if(us!=null){
			return new Response(ResponseStatus.Success,us,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	}
	@RequestMapping(value="/getUserPush",method=RequestMethod.GET)
	public Response getUserPush(HttpServletRequest request,InforpushBo infor,User user,HttpSession session){
		String requestAddress = request.getRequestURI();
		String ip="";
		try {
			ip = IpUtil.getIpAddr(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
			log.error(e.getMessage(),e);
		}
	
		String userid = (String)request.getSession().getAttribute("userid");
		log.info("userid:"+userid+",loginip:"+ip+",requestAddress:"+requestAddress);
		
   	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Inforpush> ccid = inforpushImpl.selectPush(infor);
	    if(ccid.size()>0){
	    	 Date date = new Date();
		     String da = df.format(date);
		     infor.setLogtime(da);
		     infor.setStartTime(infor.getStartTime());
		     infor.setEndTime(infor.getEndTime());
	    	 int insUserId = inforpushImpl.updateByCidSelective(infor);
		  return new Response(ResponseStatus.Success,"CID存在",true);	
	   }else{
		     infor.setId(UuidUtil.getUUID());
		     Date date = new Date();
		     String da = df.format(date);
		     infor.setLogtime(da);
		     infor.setStartTime(infor.getStartTime());
		     infor.setEndTime(infor.getEndTime());
		     int in =inforpushImpl.insertSelective(infor);
		     if(in >0 ){
		    	    return new Response(ResponseStatus.Success,"CID已插入",true);
		    }else{
		    	return new Response(ResponseStatus.Error,AppConstant.responseInfo.UPDATEEERRO,false);
		    }
		}
	}	
	/**
	 * <p>
	 * 方法名称：outlogin
	 * </p>
	 * <p>
	 * 方法描述：退出登录
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @author liuyy
	 * @since 2016年10月12日
	 *        <p>
	 *        history 2016年10月12日 Administrator 创建
	 *        <p>
	 */
	@RequestMapping(value = "/outlogin", method = RequestMethod.GET)
	public Response outlogin(HttpServletRequest request,String cid) {
		String requestAddress = request.getRequestURI();
		String ip="";
		try {
			ip = IpUtil.getIpAddr(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
			log.error(e.getMessage(),e);
		}
		String userid = (String)request.getSession().getAttribute("userid");
		log.info("userid:"+userid+",loginip:"+ip+",requestAddress:"+requestAddress);
		inforpushImpl.deleteByCid(cid);
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("user");
			session.removeAttribute("userid");
			session.removeAttribute("usertype");
			session.removeAttribute("name");
		}
		return new Response(ResponseStatus.Success, AppConstant.responseInfo.DELETESUCCESS, true);
	}
}
