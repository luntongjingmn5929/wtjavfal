


package com.bayside.app.opinion.mobile.war.publicopinion.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayside.app.opinion.mobile.war.mynews.bo.PersonManageBo;
import com.bayside.app.opinion.mobile.war.mynews.bo.PersonmanagemarticleBo;
import com.bayside.app.opinion.mobile.war.mynews.model.PersonManage;
import com.bayside.app.opinion.mobile.war.mynews.model.Personmanagemarticle;
import com.bayside.app.opinion.mobile.war.publicopinion.bo.SubJectArticleBo;
import com.bayside.app.opinion.mobile.war.publicopinion.model.SubjectMAarticle;
import com.bayside.app.opinion.mobile.war.publicopinion.service.PublicArticleService;
import com.bayside.app.opinion.mobile.war.user.model.User;
import com.bayside.app.util.AppConstant;
import com.bayside.app.util.IpUtil;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.bayside.app.util.SolrPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
 * <p>Title: PublicArticleController</P>
 * <p>Description:舆情文章 </p>
 * <p>Copyright: 山东贝赛信息科技有限公司 Copyright (c) 2016</p>
 * @author liuyy
 * @version 1.0
 * @since 2016年12月5日
 */
@RestController
public class PublicArticleController {
	@Autowired
	public PublicArticleService publicArticleImpl;
	private static final Logger log = Logger.getLogger(PublicArticleController.class);
	/**
	 * 
	 * <p>方法名称：getPublicArticleImpl</p>
	 * <p>方法描述：查询用户下的所有文章</p>
	 * @param userid
	 * @param request
	 * @return
	 * @author liuyy
	 * @since  2016年12月5日PublicArticleBo record,HttpServletRequest request
	 * <p> history 2016年12月5日 Administrator创建   <p>
	 */
	@RequestMapping(value="/getPublicArticleImpl", method=RequestMethod.GET)
	public Response getPublicArticleImpl(SubJectArticleBo record,HttpServletRequest request,PageInfo pagen,String userid){
		//String userid=(String)request.getSession().getAttribute("userid");
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
		//PageHelper.startPage(pagen.getPageNum(), pagen.getPageSize());
		record.setStart((pagen.getPageNum()-1)*pagen.getPageSize());
		record.setSize(pagen.getPageSize());
		List<SubJectArticleBo> list= publicArticleImpl.selectAllByUserId(record);
		
		if(list.size()>0){
			return new Response(ResponseStatus.Success,list,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	
	}
	/**
	 * 
	 * <p>方法名称：getFilterOpinion</p>
	 * <p>方法描述：条件过滤</p>
	 * @param page
	 * @param stage
	 * @param subjectid
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liuyy
	 * @since  2016年12月8日
	 * <p> history 2016年12月8日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/getFilterOpinion", method = RequestMethod.GET)
	public Response getFilterOpinion(PageInfo page, SubJectArticleBo stage,String subjectid,
			HttpServletRequest request,String userid){
		String ip="";
		try {
			ip = IpUtil.getIpAddr(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		 log.info(e.getMessage());
			log.error(e.getMessage(),e);
		}
		String requestAddress = request.getRequestURI();
		log.info("userid:"+userid+",loginip:"+ip+",requestAddress:"+requestAddress);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			stage.setUserid(userid);
			stage.setSubjectid(subjectid);
			if (stage.getStartTime() != null) {
				if (stage.getStartTime().equals("current")) {
					stage.setStartTime(df.format(new Date()));
				} else if (stage.getStartTime().equals("sun")) {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE, -7);
					String str = df.format(c.getTime());
					stage.setStartTime(str);
				} else if (stage.getStartTime().equals("month")) {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE, -30);
					String str = df.format(c.getTime());
					stage.setStartTime(str);
				}else if(stage.getStartTime().equals("tianjia")){
					stage.setStartTime(stage.getSttime());
				}
			}
			/*if("".equals(stage.getFormats()) || null ==stage.getFormats()){
				if(request.getSession().getAttribute("setTrade")==null){
					System.out.println(request.getSession().getAttribute("setTrade"));
				}else{
					Integer trade = (Integer)request.getSession().getAttribute("setTrade");
					if(trade!=1){
						List<String> formatslist = (List<String>) request.getSession().getAttribute("formatslist");
						stage.setMedialist(formatslist);
					}
				}
			}else{
				
			}*/
			Integer trade = (Integer)request.getSession().getAttribute("setTrade");
			if(null == stage.getIstrade()){
				if(trade!=1){
					List<String> formatslist = (List<String>) request.getSession().getAttribute("formatslist");
					stage.setMedialist(formatslist);
				}	
			}else if(1 == stage.getIstrade()){
				//如果开通 交易类型 查询 交易类型 
				if(trade == 1){
					if(null == stage.getFormats() || "".equals(stage.getFormats())){
						stage.setFormats("trade");
						stage.setDependency(Double.parseDouble("2"));
					}else if("trade".equals(stage.getFormats())){
						
					}else{
						stage.setDependency(Double.parseDouble("2"));
					}
					stage.setTrade(trade);
				}else{
					//查询相关性小于2
					stage.setDependency(Double.parseDouble("2"));
					List<String> formatslist = (List<String>) request.getSession().getAttribute("formatslist");
					stage.setMedialist(formatslist);
				}
			}else{
				List<String> formatslist = (List<String>) request.getSession().getAttribute("formatslist");
				stage.setMedialist(formatslist);
		
			}
			
			
			
		//	PageHelper.startPage(page.getPageNum(), page.getPageSize());
			stage.setStart((page.getPageNum()-1)*page.getPageSize());
			stage.setSize(page.getPageSize());
			List<SubJectArticleBo> list = new ArrayList<SubJectArticleBo>();
			SubJectArticleBo sb = new SubJectArticleBo();
			if(null == stage.getIsrepeat()){
				 list = publicArticleImpl.filterCom(stage);
				 sb = publicArticleImpl.selectAllByPage(stage);
			}else if(1 == stage.getIsrepeat()){
				 list = publicArticleImpl.filterCom(stage);
				 sb = publicArticleImpl.selectAllByPage(stage);
			}else if(0 == stage.getIsrepeat()){
				 list = publicArticleImpl.repeatfilterCom(stage);
				 sb = publicArticleImpl.repeatselectAllByPage(stage);
			}
			
		  
			
			SolrPage<SubJectArticleBo> info = new SolrPage<SubJectArticleBo>();
		    info.setList(list);
		    if(null!=sb){
			    info.setTotal(sb.getTotal());
		    }else{
			    info.setTotal(0);
		    }
	
		//  PageInfo<SubJectArticleBo> info = new PageInfo<SubJectArticleBo>(list);
			if(info!=null){
				return new Response(ResponseStatus.Success, info, true);
			}else{
				return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
			}
		
	}
	/**
	 * 
	 * <p>方法名称：selectArticleDetailById</p>
	 * <p>方法描述：文章详情页</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @since  2016年12月6日
	 * <p> history 2016年12月6日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectArticleDetailById", method = RequestMethod.GET)
	public Response selectArticleDetailById(SubJectArticleBo record,HttpServletRequest request) {
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
		SubJectArticleBo sb = new SubJectArticleBo();
		//sb = publicArticleImpl.selectArticleDetailById(record.getArticleid(),record.getMid());
		
		if(null == record.getMid()){
			sb = publicArticleImpl.selectArticleDetailById(record.getArticleid());
		}else{
			sb = publicArticleImpl.selectArticleDetailById(record);
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
			sb.setEdtime((sd.format(sb.getPubdate())));
		}
		if (sb != null) {
			return new Response(ResponseStatus.Success, sb, true);
		} else {
			return new Response(ResponseStatus.Error, AppConstant.responseInfo.SELECTEERRO, false);
		}
	}
	/**
	 * 
	 * <p>方法名称：showArticleDetailById</p>
	 * <p>方法描述：文章详情页(new)(new)(new)</p>
	 * @param record
	 * @param request
	 * @author administor
	 * @since  2017年6月14日
	 * <p> history 2017年6月14日 administor  创建   <p>
	 */
	@RequestMapping(value = "/showArticleDetailById", method = RequestMethod.GET)
	public Response showArticleDetailById(SubJectArticleBo record,HttpServletRequest request) {
		String requestAddress = request.getRequestURI();
		String ip="";
		try {
			ip = IpUtil.getIpAddr(request);
		} catch (Exception e) {
		 log.info(e.getMessage());
		 log.error(e.getMessage(),e);
		}
		
		String userid = (String)request.getSession().getAttribute("userid");
		log.info("userid:"+userid+",loginip:"+ip+",requestAddress:"+requestAddress);
		SubJectArticleBo sb = new SubJectArticleBo();
		sb = publicArticleImpl.	showArticleDetailById(record);
		
		if (sb != null) {
			return new Response(ResponseStatus.Success, sb, true);
		} else {
			return new Response(ResponseStatus.Error, AppConstant.responseInfo.SELECTEERRO, false);
		}
	}

	/**
	 * 
	 * <p>方法名称：updateCollection</p>
	 * <p>方法描述：添加收藏</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @since  2016年12月7日
	 * <p> history 2016年12月7日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/updateCollection", method = RequestMethod.GET)
	public Response updateCollection(SubjectMAarticle record,HttpServletRequest request){
		String requestAddress = request.getRequestURI();
		String ip="";
		try {
			ip = IpUtil.getIpAddr(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		 log.info(e.getMessage());
			log.error(e.getMessage(),e);
		}
		/*User user = (User) request.getSession().getAttribute("user");*/
		String userid = (String)request.getSession().getAttribute("userid");
		log.info("userid:"+userid+",loginip:"+ip+",requestAddress:"+requestAddress);
		record.setCollection(1);
		int num = publicArticleImpl.updateCollection(record);
		if(num > 0){
			return new Response(ResponseStatus.Success,num,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.UPDATEEERRO,false);
		}
	}
	/**
	 * 
	 * <p>方法名称：updateCollection</p>
	 * <p>方法描述：取消收藏</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @since  2016年12月7日
	 * <p> history 2016年12月7日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/cellCollection", method = RequestMethod.GET)
	public Response cellCollection(SubjectMAarticle record,HttpServletRequest request){
		String requestAddress = request.getRequestURI();
		String ip="";
		try {
			ip = IpUtil.getIpAddr(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		 log.info(e.getMessage());
			log.error(e.getMessage(),e);
		}
		/*User user = (User) request.getSession().getAttribute("user");*/
		String userid = (String)request.getSession().getAttribute("userid");
		log.info("userid:"+userid+",loginip:"+ip+",requestAddress:"+requestAddress);
		record.setCollection(0);
		int num = publicArticleImpl.updateCollection(record);
		if(num > 0){
			return new Response(ResponseStatus.Success,num,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.UPDATEEERRO,false);
		}
	}
	@RequestMapping(value = "/updatePaperCollection", method = RequestMethod.GET)
	public Response updatePaperCollection(Personmanagemarticle record,HttpServletRequest request){
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
		record.setCollection(1);
		int num = publicArticleImpl.updatepaperCollection(record);
		if(num > 0){
			return new Response(ResponseStatus.Success,num,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.UPDATEEERRO,false);
		}
	}
	@RequestMapping(value = "/cellPaperCollection", method = RequestMethod.GET)
	public Response cellPaperCollection(Personmanagemarticle record,HttpServletRequest request){
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
		record.setCollection(0);
		int num = publicArticleImpl.updatepaperCollection(record);
		if(num > 0){
			return new Response(ResponseStatus.Success,num,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.UPDATEEERRO,false);
		}
	}
	/**
	 * 
	 * <p>方法名称：selectAllByCollection</p>
	 * <p>方法描述：我的收藏列表</p>
	 * @param request
	 * @param page
	 * @return
	 * @author liuyy
	 * @since  2016年12月6日
	 * <p> history 2016年12月6日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectAllByCollection", method = RequestMethod.GET)
	public Response selectAllByCollection(HttpServletRequest request,PageInfo page,String userid){
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
		SubJectArticleBo sab = new SubJectArticleBo();
		//String userid = (String)request.getSession().getAttribute("userid");
		sab.setUserid(userid);
		sab.setCollection(1);
		sab.setStart((page.getPageNum()-1)*page.getPageSize());
		sab.setSize(page.getPageSize());
		//PageHelper.startPage(, );
		List<SubJectArticleBo> list = publicArticleImpl.selectAllByCollection(sab);
		SubJectArticleBo sb  = publicArticleImpl.selectTotalCollection(sab);
		SolrPage<SubJectArticleBo> info = new SolrPage<SubJectArticleBo>();
		info.setList(list);
		if(null!=sb){
			info.setTotal(sb.getTotal());
		}else{
			info.setTotal(0);
		}
		if(info!=null){
			return new Response(ResponseStatus.Success,info,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	}
	/**
	 * 
	 * <p>方法名称：selectAllByCollection</p>
	 * <p>方法描述：我的收藏列表</p>
	 * @param request
	 * @param page
	 * @return
	 * @author liuyy
	 * @since  2016年12月6日
	 * <p> history 2016年12月6日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectpaperByCollection", method = RequestMethod.GET)
	public Response selectpaperByCollection(HttpServletRequest request,PageInfo page,String userid){
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
		SubJectArticleBo sab = new SubJectArticleBo();
		//String userid = (String)request.getSession().getAttribute("userid");
		sab.setUserid(userid);
		sab.setCollection(1);
		sab.setStart(page.getPageNum());
		sab.setSize(page.getPageSize());
	//	PageHelper.startPage(, );
		List<SubJectArticleBo> list = publicArticleImpl.selectPaperByCollection(sab);
		SubJectArticleBo sb = publicArticleImpl.selectPaperTotalCollection(sab);
	//	PageInfo<SubJectArticleBo> info = new PageInfo<SubJectArticleBo>(list);
		SolrPage<SubJectArticleBo> info = new SolrPage<SubJectArticleBo>();
		info.setList(list);
		if(null!=sb){
			info.setTotal(sb.getTotal());
		}else{
			info.setTotal(0);
		}
		if(info!=null){
			return new Response(ResponseStatus.Success,info,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	}
	
	/**
	 * 
	 * <p>方法名称：selectPaperInfo</p>
	 * <p>方法描述：我的报纸列表页</p>
	 * @param record
	 * @param request
	 * @param page
	 * @return
	 * @author liuyy
	 * @since  2016年12月7日
	 * <p> history 2016年12月7日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "selectPaperInfo", method = RequestMethod.GET)
	public Response selectPaperInfo(PersonmanagemarticleBo record,HttpServletRequest request,PageInfo page){
	   // String userid = (String)request.getSession().getAttribute("userid");
	  //  PageHelper.startPage(page.getPageNum(), page.getPageSize());
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
	/*	List<String> formatslist = (List<String>) request.getSession().getAttribute("formatslist");
		record.setMedialist(formatslist);*/
	/*	Integer trade = (Integer)request.getSession().getAttribute("setTrade");
		if(trade!=1){
			List<String> formatslist = (List<String>) request.getSession().getAttribute("formatslist");
			record.setMedialist(formatslist);
		}
		*/
		/*record.setStart((page.getPageNum()-1)*page.getPageSize());
		record.setSize(page.getPageSize());*/
		record.setPageNum((page.getPageNum()-1)*page.getPageSize());
		record.setPageSize(page.getPageSize());
		List<SubJectArticleBo> list = publicArticleImpl.myPaperListInfo(record);
		SubJectArticleBo sb = publicArticleImpl.selectTotalPaperList(record);
		SolrPage<SubJectArticleBo> info = new SolrPage<SubJectArticleBo>();
		info.setList(list);
		if(null!=sb){
			info.setTotal(sb.getTotal());
		}else{
			info.setTotal(0);
		}
		//PageInfo<SubJectArticleBo> info = new PageInfo<SubJectArticleBo>(list);
		if(info!=null){
			return new Response(ResponseStatus.Success,info,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	}
	/**
	 * 
	 * <p>方法名称：selectFormalPersonAllInfo</p>
	 * <p>方法描述：查询正常启用状态的人物</p>
	 * @param request
	 * @return
	 * @author liuyy
	 * @since  2016年12月7日
	 * <p> history 2016年12月7日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectFormalPersonAllInfo", method = RequestMethod.GET)
	public Response selectFormalPersonAllInfo(HttpServletRequest request,String userid){
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
		  PersonManageBo pb = new PersonManageBo();
		  pb.setUserid(userid);
		  pb.setIsenable(true);
		  List<PersonManage> list = publicArticleImpl.selectpageAll(pb);
		  if(list.size()>0){
			  return new Response(ResponseStatus.Success, list, true); 
		  }else{
			  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		  }
		  
	}
	@RequestMapping(value = "/selectCollectoinById", method = RequestMethod.GET)
	public Response selectCollectoinById(SubjectMAarticle record,HttpServletRequest request){
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
		SubjectMAarticle sm = publicArticleImpl.selectCollectoinById(record.getId());
		if(sm!=null){
			return new Response(ResponseStatus.Success,sm,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
		
	}
	@RequestMapping(value = "/selectCollectoinByPmId", method = RequestMethod.GET)
	public Response selectCollectoinByPmId(Personmanagemarticle record,HttpServletRequest request){
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
		Personmanagemarticle sm = publicArticleImpl.selectCollectionByPmId(record.getId());
		if(sm!=null){
			return new Response(ResponseStatus.Success,sm,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
		
	}
	/**
	 * 
	 * <p>方法名称：selectSimlarArticle</p>
	 * <p>方法描述： 详情页相似文章列表</p>
	 * @param mid
	 * @param page
	 * @return
	 * @author liuyy
	 * @since  2017年7月31日
	 * <p> history 2017年7月31日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectSimlarArticle", method = RequestMethod.GET)
	public Response selectSimlarArticle(String mid,PageInfo page){
		SubjectMAarticle sm = publicArticleImpl.selectSimidsById(mid);
		SolrPage<SubJectArticleBo> info = new SolrPage<SubJectArticleBo>();
		   if (null!=sm) {
			   List<String> mids = new ArrayList<String>();
			   String simids= sm.getSimids();
			   SubJectArticleBo stage = new SubJectArticleBo();
			   if(null!=simids && !"".equals(simids)&& simids.contains(",")){
		     		String[] listmids = simids.split(",");
		     	   info.setTotal(listmids.length);
		    		int start = (page.getPageNum()-1)*page.getPageSize();
		    	   int end = (page.getPageNum()-1)*page.getPageSize()+10;
		    	if(end>listmids.length){
	    			for(int i=start;i<listmids.length;i++){
		     			mids.add(listmids[i]);
		     		}
		    	}else{
	    			for(int j=start;j<end;j++){
		     			mids.add(listmids[j]);
		     		}
		    	}	
		     	}else{
		     	    info.setTotal(1);
		     		mids.add(sm.getSimids());
		     	}
		     	stage.setMids(mids);
		     	List<SubJectArticleBo> list = publicArticleImpl.filterSimlarArticle(stage);
		     	
				info.setList(list);
		     	if(info!=null){
		     		return new Response(ResponseStatus.Success,info, true);
		     	}else{
		     		return new Response(ResponseStatus.Error, AppConstant.responseInfo.SELECTEERRO, false);
		     	}
			}else {
				return new Response(ResponseStatus.Error, AppConstant.responseInfo.SELECTEERRO, false);
			}
		
	}
	

}
