package com.bayside.app.opinion.mobile.war.report.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayside.app.opinion.mobile.war.report.bo.OpinionReportBo;
import com.bayside.app.opinion.mobile.war.report.model.OpinionReport;
import com.bayside.app.opinion.mobile.war.report.service.OpinionReportService;
import com.bayside.app.opinion.mobile.war.user.bo.UserBo;
import com.bayside.app.util.AppConstant;
import com.bayside.app.util.IpUtil;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
 * <p>Title: OpinionReportController</P>
 * <p>Description:舆情报告的控制层 </p>
 * <p>Copyright: 山东贝赛信息科技有限公司 Copyright (c) 2016</p>
 * @author Administrator
 * @version 1.0
 * @since 2016年10月4日
 */
@RestController
public class OpinionReportController {
	@Autowired
	private OpinionReportService opinionReportServiceImpl;
	private static final Logger log = Logger.getLogger(OpinionReportController.class);
	/**
	 * 
	 * <p>方法名称：getOpinionReport</p>
	 * <p>方法描述：获取舆情报告</p>
	 * @param oReportBo
	 * @param page
	 * @param request
	 * @return
	 * @author Administrator
	 * @since  2016年10月3日
	 * <p> history 2016年10月3日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/getOpinionReport", method = RequestMethod.GET)
	public Response getOpinionReport(OpinionReportBo oReportBo,PageInfo page,HttpServletRequest request,String userid){
	//	String userid = (String) request.getSession().getAttribute("userid");
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
		oReportBo.setUserid(userid);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<OpinionReport> list = opinionReportServiceImpl.getOpinionReport(oReportBo);
		PageInfo<OpinionReport> pageInfo = new PageInfo<OpinionReport>(list);
		return new Response(ResponseStatus.Success, pageInfo, true);
	}
	/**
	 * 
	 * <p>方法名称：getUserInfo</p>
	 * <p>方法描述：获取用户的详情信息</p>
	 * @param oReportBo
	 * @param page
	 * @param request
	 * @return
	 * @author sql
	 * @since  2016年10月3日
	 * <p> history 2016年10月3日 sql  创建   <p>
	 */
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	public Response getUserInfo(String id,HttpServletRequest request){
		String userid = (String) request.getSession().getAttribute("userid");
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
		UserBo userBo = opinionReportServiceImpl.getUserInfo(id);
		return new Response(ResponseStatus.Success, userBo, true);
	}
	/**
	 * 
	 * <p>方法名称：saveImg</p>
	 * <p>方法描述：重点舆情</p>
	 * @param oReportBo
	 * @param page
	 * @param request
	 * @return
	 * @author sql
	 * @since  2016年10月3日
	 * <p> history 2016年10月3日 sql  创建   <p>
	 */
	@RequestMapping(value = "/downloadWorld", method = RequestMethod.GET)
	public void downloadWorld(String id,HttpServletRequest request,HttpServletResponse response){
		String userid = (String) request.getSession().getAttribute("userid");
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
		opinionReportServiceImpl.downloadWorld(id,request,response);
		//return new Response(ResponseStatus.Success, null, true);
	}
	/**
	 * 
	 * <p>方法名称：saveImg</p>
	 * <p>方法描述：下载html</p>
	 * @param oReportBo
	 * @param page
	 * @param request
	 * @return
	 * @author sql
	 * @since  2016年10月3日
	 * <p> history 2016年10月3日 sql  创建   <p>
	 */
	@RequestMapping(value = "/downloadHtml", method = RequestMethod.GET)
	public void downloadHtml(String id,HttpServletRequest request,HttpServletResponse response){
		/*String userid = (String) request.getSession().getAttribute("userid");
		subjectParamBo.setUserid(userid);*/
		opinionReportServiceImpl.downloadHtml(id,request,response);
		//return new Response(ResponseStatus.Success, null, true);
	}
	/**
	 * 
	 * <p>方法名称：saveImg</p>
	 * <p>方法描述：重点舆情</p>
	 * @param oReportBo
	 * @param page
	 * @param request
	 * @return
	 * @author sql
	 * @since  2016年10月3日
	 * <p> history 2016年10月3日 sql  创建   <p>
	 */
	@RequestMapping(value = "/privew", method = RequestMethod.GET)
	public Response privew(String id,HttpServletRequest request,HttpServletResponse response){
		/*String userid = (String) request.getSession().getAttribute("userid");
		subjectParamBo.setUserid(userid);*/
		String html = opinionReportServiceImpl.privew(id,request,response);
		if(html!=null&&!"".equals(html)){
			return new Response(ResponseStatus.Success, html, true);
		}else{
			return new Response(ResponseStatus.Error, AppConstant.responseInfo.SELECTEERRO, false);
		}
		
	}
}
