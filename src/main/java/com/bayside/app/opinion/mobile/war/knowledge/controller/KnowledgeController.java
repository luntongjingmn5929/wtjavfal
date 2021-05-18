package com.bayside.app.opinion.mobile.war.knowledge.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayside.app.opinion.mobile.war.knowledge.bo.RelevantBo;
import com.bayside.app.opinion.mobile.war.knowledge.bo.RelevantDataBo;
import com.bayside.app.opinion.mobile.war.knowledge.service.KnowledgeService;
import com.bayside.app.opinion.mobile.war.user.model.User;
import com.bayside.app.opinion.mobile.war.user.model.WordSet;
import com.bayside.app.opinion.mobile.war.user.service.UserService;
import com.bayside.app.util.AppConstant;
import com.bayside.app.util.IpUtil;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.bayside.app.util.SolrPage;

@RestController
public class KnowledgeController {
	@Autowired
	private KnowledgeService knowledgeServiceImpl;
	@Autowired
	private UserService userServiceImpl;
	private static final Logger log = Logger.getLogger(KnowledgeController.class);
	/**
	 * 
	 * <p>方法名称：getRelevant</p>
	 * <p>方法描述：申请开通</p>
	 * @param relevantBo
	 * @return
	 * @author sql
	 * @since  2016年9月30日
	 * <p> history 2016年9月30日 sql  创建   <p>
	 */
	@RequestMapping(value="/microopen",method = RequestMethod.GET)
	public Response  microopen(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
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
		if(user.getMicroopen()!=null&&user.getMicroopen()>0){
			return new Response(ResponseStatus.Success, "您已经开通了此功能", true);
		}
		String trialStatus = knowledgeServiceImpl.microopen(user);
		return new Response(ResponseStatus.Success, trialStatus, true);
	}
	
  @RequestMapping(value="/getTrialStatus",method = RequestMethod.GET)
	public Response  getTrialStatus(HttpServletRequest request,String userid) {
		//String userid = (String) request.getSession().getAttribute("userid");
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
		Boolean trialStatus = false;
		WordSet wordset = new WordSet();
		wordset.setUserid(userid);
	    wordset.setName(AppConstant.standPower.MICROOPENNAME);
		WordSet ws = userServiceImpl.selectPowerByName(wordset);
		if(ws.getStatus() == 1){
			trialStatus=true;
			return new Response(ResponseStatus.Success, trialStatus, true);
		}else{
			return new Response(ResponseStatus.Error, trialStatus, false);
		}
		
	}
	/**
	 * 
	 * <p>方法名称：getRelevant</p>
	 * <p>方法描述：获取监测项</p>
	 * @param relevantBo
	 * @param request
	 * @return
	 * @author liuyy
	 * @since  2016年12月6日
	 * <p> history 2016年12月6日 Administrator  创建   <p>
	 */
	@RequestMapping(value="/getRelevant",method = RequestMethod.GET)
	public Response  getRelevant(HttpServletRequest request,String userid) {
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
		//String userid = (String) request.getSession().getAttribute("userid");
		RelevantBo relevantBo = new RelevantBo();
		relevantBo.setUserid(userid);
		List<RelevantBo> list = knowledgeServiceImpl.getRelevant(relevantBo);
		return new Response(ResponseStatus.Success, list, true);
	}
	
	/**
	 * 
	 * <p>方法名称：getRelevantData</p>
	 * <p>方法描述：获取监测项的文章信息</p>
	 * @param relevantBo
	 * @param page
	 * @return
	 * @author liuyy
	 * @since  2016年12月6日
	 * <p> history 2016年12月6日 Administrator  创建   <p>
	 */
	@RequestMapping(value="/getRelevantData",method = RequestMethod.GET)
	public Response  getRelevantData(RelevantBo relevantBo,SolrPage page,HttpServletRequest request) {
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
	    
		SolrPage<RelevantDataBo> list = knowledgeServiceImpl.getRelevantData(relevantBo,page);
		
		return new Response(ResponseStatus.Success, list, true);
	}
	
}
