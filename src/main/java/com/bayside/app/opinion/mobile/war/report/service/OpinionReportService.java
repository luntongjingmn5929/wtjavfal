package com.bayside.app.opinion.mobile.war.report.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bayside.app.opinion.mobile.war.report.bo.OpinionReportBo;
import com.bayside.app.opinion.mobile.war.report.model.OpinionReport;
import com.bayside.app.opinion.mobile.war.user.bo.UserBo;

public interface OpinionReportService {

	
	/**
	 * 
	 * <p>方法名称：getOpinionReport</p>
	 * <p>方法描述：分页查询</p>
	 * @param oReportBo
	 * @return
	 * @author Administrator
	 * @since  2016年10月3日
	 * <p> history 2016年10月3日 Administrator  创建   <p>
	 */
	List<OpinionReport> getOpinionReport(OpinionReportBo oReportBo);
	/**
	 * 
	 * <p>方法名称：getUserInfo</p>
	 * <p>方法描述： 获取用户的详情信息</p>
	 * @param userid
	 * @return
	 * @author Administrator
	 * @since  2016年10月4日
	 * <p> history 2016年10月4日 Administrator  创建   <p>
	 */
	UserBo getUserInfo(String userid);
	/**
	 * 
	 * <p>方法名称：downloadWorld</p>
	 * <p>方法描述：下载</p>
	 * @param id
	 * @param request
	 * @param response
	 * @author Administrator
	 * @since  2016年10月13日
	 * <p> history 2016年10月13日 Administrator  创建   <p>
	 */
	void downloadWorld(String id, HttpServletRequest request, HttpServletResponse response);
	/**
	 * 
	 * <p>方法名称：downloadHtml</p>
	 * <p>方法描述：下载html</p>
	 * @param id
	 * @param request
	 * @param response
	 * @author Administrator
	 * @since  2017年3月16日
	 * <p> history 2017年3月16日 Administrator  创建   <p>
	 */
	void downloadHtml(String id, HttpServletRequest request, HttpServletResponse response);
	/**
	 * 
	 * <p>方法名称：privew</p>
	 * <p>方法描述：预览</p>
	 * @param id
	 * @param request
	 * @param response
	 * @author Administrator
	 * @return 
	 * @since  2016年10月13日
	 * <p> history 2016年10月13日 Administrator  创建   <p>
	 */
	String privew(String id, HttpServletRequest request, HttpServletResponse response);

}
