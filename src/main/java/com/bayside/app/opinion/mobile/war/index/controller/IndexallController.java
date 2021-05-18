package com.bayside.app.opinion.mobile.war.index.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayside.app.opinion.mobile.war.index.service.MonitorallService;
import com.bayside.app.opinion.mobile.war.publicopinion.bo.SubJectArticleBo;
import com.bayside.app.opinion.mobile.war.subject.bo.SubjectStatisticalBo;
import com.bayside.app.util.AppConstant;
import com.bayside.app.util.DateFormatUtil;
import com.bayside.app.util.IpUtil;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.mysql.jdbc.log.Log;

/**
 * <p>Title: IndexallController</P>
 * <p>Description: 首页信息</p>
 * <p>Copyright: 山东贝赛信息科技有限公司 Copyright (c) 2016</p>
 * @author liuyy
 * @version 1.0
 * @since 2016年10月12日
 */
@RestController

public class IndexallController {
 @Autowired
 private MonitorallService monitorallServiceImpl;
 private static final Logger log = Logger.getLogger(IndexallController.class);

 /**
 * <p>方法名称：getyuqingNumberByEmotion</p>
 * <p>方法描述： 查询舆情监测数量</p>
 * @param record
 * @param request
 * @return
 * @author liuyy
 * @since  2016年10月12日
 * <p> history 2016年10月12日 Administrator  创建   <p>
 * 
 */
@RequestMapping(value="/getyuqingNumberByEmotion", method=RequestMethod.GET)
 public Response getyuqingNumberByEmotion(HttpServletRequest request,String userid){
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
	SubjectStatisticalBo record = new SubjectStatisticalBo();
 //  String userid = (String)request.getSession().getAttribute("userid");
	record.setUserid(userid);
	List<SubjectStatisticalBo> list = monitorallServiceImpl.selectIndexyuqing(record, request);
	
	 if(list.size()>0){
		 return new Response(ResponseStatus.Success,list,true);
	 }else{
		 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	 }
	
 }
 /**
 * <p>方法名称：selectlastsubjectarticle</p>
 * <p>方法描述： 查询首页最新文章</p>
 * @param request
 * @return
 * @author liuyy
 * @since  2016年10月12日
 * <p> history 2016年10月12日 Administrator  创建   <p>
 */
@RequestMapping(value="/selectlastsubjectarticle",method=RequestMethod.GET)
 public Response selectlastsubjectarticle(HttpServletRequest request,String userid){
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
	 
	 long temp = System.currentTimeMillis();
	 List<String> formatslist = null;
	 Integer trade = (Integer)request.getSession().getAttribute("setTrade");
	if(trade!=1){
			formatslist = (List<String>) request.getSession().getAttribute("formatslist");
	}
	 
	 
	 List<SubJectArticleBo> list = monitorallServiceImpl.selectlastsubjectarticle(userid,formatslist);
	 List<SubJectArticleBo> li = new ArrayList<SubJectArticleBo>();
	 for(int i=0;i<list.size();i++){
		// SubjectMArticle s = monitorallServiceImpl.selectAttention(list.get(i).getId());
		 SubJectArticleBo  ss = new SubJectArticleBo();
		 BeanUtils.copyProperties(list.get(i), ss);
		// ss.setAttention(s.getAttention());
			 Date date = list.get(i).getPubdate();
			 SimpleDateFormat df=new SimpleDateFormat("MM-dd");
			 String d = df.format(date);
			 Date dat=new Date();
			 String current = df.format(new Date());
			 if(d.equals(current)){
				 ss.setTime(DateFormatUtil.timeString(list.get(i).getPubdate()));
			 }else{
				 ss.setTime(d);
			 }
			 
		 ss.setTittle(list.get(i).getTittle());
		 li.add(ss);
	 }
	if(li.size()>0){
		return new Response(ResponseStatus.Success,li,true);
	}else{
		return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	}		 
 }


 /**
 * <p>方法名称：selectSubjectArticle</p>
 * <p>方法描述：//查询媒体分布信息 正面 负面 中性 //查询专题统计
</p>
 * @param list
 * @param userid
 * @param request
 * @return
 * @author liuyy
 * @since  2016年10月12日
 * <p> history 2016年10月12日 Administrator  创建   <p>
 */
@RequestMapping(value="/selectSubjectArticle",method=RequestMethod.GET)
 public Response selectSubjectArticle(List list,String userid,HttpServletRequest request){
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
	// String userid1 = (String)request.getSession().getAttribute("userid");
	 List<SubJectArticleBo> lis = monitorallServiceImpl.selectnewSixsubjectarticle(list, userid);
	 if(lis.size()>0){
		 return new Response(ResponseStatus.Success,lis,true);
	 }else{
		 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	 }
 }
 /**
 * <p>方法名称：selectzaitiNumber</p>
 * <p>方法描述： 载体趋势图</p>
 * @param record
 * @param request
 * @return
 * @author liuyy
 * @since  2016年10月12日
 * <p> history 2016年10月12日 Administrator  创建   <p>
 */
@RequestMapping(value="/selectzaitiNumber",method=RequestMethod.GET)
 public Response selectzaitiNumber(HttpServletRequest request,String userid){
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
	SubjectStatisticalBo record = new SubjectStatisticalBo();
//	String userid=(String)request.getSession().getAttribute("userid");
	record.setUserid(userid);
	 List<SubjectStatisticalBo> sBos = monitorallServiceImpl.selectTodayzaitiByTime(record,request);
	 if(sBos.size()>0){
		 return new Response(ResponseStatus.Success,sBos,true);
	 }else{
		 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	 }
	
 }
 /**
 * <p>方法名称：selectnewWarning</p>
 * <p>方法描述：查询预警推送</p>
 * @param request
 * @return
 * @author liuyy
 * @since  2016年10月12日
 * <p> history 2016年10月12日 Administrator  创建   <p>
 */
@RequestMapping(value="/selectnewWarning",method=RequestMethod.GET)
 public Response selectnewWarning(HttpServletRequest request,String userid){
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
	 int warning =1;
	// String userid=(String)request.getSession().getAttribute("userid");
   //  List<>
	
	 List<String> formatslist = null;
		Integer trade = (Integer)request.getSession().getAttribute("setTrade");
		if(trade!=1){
			formatslist = (List<String>) request.getSession().getAttribute("formatslist");
		}
	 List<SubJectArticleBo> list = monitorallServiceImpl.selectnewWarningarticle(userid,warning,formatslist);
	 List<SubJectArticleBo> li = new ArrayList<SubJectArticleBo>();
	 for(int i=0;i<list.size();i++){
		// SubjectMArticle s = monitorallServiceImpl.selectAttention(list.get(i).getId());
		 SubJectArticleBo  ss = new SubJectArticleBo();
		 BeanUtils.copyProperties(list.get(i), ss);
		// ss.setAttention(s.getAttention());
			 Date date = list.get(i).getPubdate();
			 SimpleDateFormat df=new SimpleDateFormat("MM-dd");
			 String d = df.format(date);
			 Date dat=new Date();
			 String current = df.format(new Date());
			 if(d.equals(current)){
				 ss.setTime(DateFormatUtil.timeString(list.get(i).getPubdate()));
			 }else{
				 ss.setTime(d);
			 }
		 ss.setTittle(list.get(i).getTittle());
		 ss.setEmotion(list.get(i).getEmotion());
		 li.add(ss);
	 }
	 
	 if(li.size()>0){
		 return new Response(ResponseStatus.Success,li,true);
	 }else{
		 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	 }
 }
@RequestMapping(value="/ceshi",method=RequestMethod.GET)
 public Response ceshi(){
	return new Response(ResponseStatus.Success,"连接成功",true);
 }



 
}
