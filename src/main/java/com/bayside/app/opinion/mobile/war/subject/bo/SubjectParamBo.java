package com.bayside.app.opinion.mobile.war.subject.bo;
/**
 * 
 * <p>Title: SubjectParamBo</P>
 * <p>Description:查询专题时用到的参数 </p>
 * <p>Copyright: 山东贝赛信息科技有限公司 Copyright (c) 2016</p>
 * @author sql
 * @version 1.0
 * @since 2016年9月22日
 */
public class SubjectParamBo {
	
	private String userid;//用户id
	
	private String subjectid;//专题id
	
	private String startTime;//开始是时间
	
	private String endTime;//结束时间
	
	private String viewPoint;//观点
	
	private String limit;//limit
	
	private String formats;//媒体类型
	
	private String emotion;//正负面
	
	private String dataSource;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getViewPoint() {
		return viewPoint;
	}
	public void setViewPoint(String viewPoint) {
		this.viewPoint = viewPoint;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getFormats() {
		return formats;
	}
	public void setFormats(String formats) {
		this.formats = formats;
	}
	public String getEmotion() {
		return emotion;
	}
	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
