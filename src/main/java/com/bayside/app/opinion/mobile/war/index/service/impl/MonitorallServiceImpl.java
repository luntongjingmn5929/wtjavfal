package com.bayside.app.opinion.mobile.war.index.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.mobile.war.index.service.MonitorallService;
import com.bayside.app.opinion.mobile.war.publicopinion.bo.SubJectArticleBo;
import com.bayside.app.opinion.mobile.war.publicopinion.dao.SubjectArticleBoMapper;
import com.bayside.app.opinion.mobile.war.publicopinion.dao.SubjectMapper;
import com.bayside.app.opinion.mobile.war.publicopinion.model.Subject;
import com.bayside.app.opinion.mobile.war.subject.bo.SubjectStatisticalBo;
import com.bayside.app.opinion.mobile.war.subject.dao.SubjectStatisticalMapper;
import com.bayside.app.opinion.mobile.war.subject.model.SubjectStatistical;
import com.bayside.app.util.DateFormatUtil;



@Service("monitorallServiceImpl")

public  class MonitorallServiceImpl implements MonitorallService {
	
	@Resource
	private Environment environment;
   
    @Autowired
    private SubjectStatisticalMapper subjectStatisticalMapper;
    @Autowired
    private SubjectArticleBoMapper subjectArticleBoMapper;
   
 
    @Autowired
    private SubjectMapper subjectMapper;
	

	@Override
	public List<SubjectStatisticalBo> selectByTimeAcount(SubjectStatisticalBo record) {
		// TODO Auto-generated method stub
		return subjectStatisticalMapper.selectByTimeAcount(record);
	}
	@Override
	public List<SubJectArticleBo> selectlastsubjectarticle(String userid,List<String> list) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.selectlastsubjectarticle(userid,list);
	}
	@Override
	public SubjectStatistical selectMediaAcountByTime(String time, String userid) {
		// TODO Auto-generated method stub
		return subjectStatisticalMapper.selectMediaAcountByTime(time, userid);
	}
	@Override
	public List<SubJectArticleBo> selectnewSixsubjectarticle(List list, String userid) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.selectnewSixsubjectarticle(list, userid);
	}
	@Override
	public List<SubjectStatistical> selectzaitiByTime(String time, String userid,String emotion) {
		// TODO Auto-generated method stub
		return subjectStatisticalMapper.selectzaitiByTime(time, userid,emotion);
	}
	@Override
	public List<SubJectArticleBo> selectnewWarningarticle(String userid, int warning,List<String> list) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.selectnewWarningarticle(userid, warning,list);
	}
	
	
	@Override
	public SubjectStatistical selectSumAcountByTime(String time, String userid, String emotion) {
		// TODO Auto-generated method stub
		return subjectStatisticalMapper.selectSumAcountByTime(time, userid, emotion);
	}
	

	@Override
	public List<Subject> selectByUserId(String userid) {
		// TODO Auto-generated method stub
		return subjectMapper.selectByUserId(userid);
	}
	@Override
	public List<SubjectStatisticalBo> selectTodayzaitiByTime(SubjectStatisticalBo record,HttpServletRequest request) {
		// TODO Auto-generated method stub
		 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		 List<SubjectStatisticalBo> sBos = new ArrayList<SubjectStatisticalBo>();
		  String current = df.format(new Date());
		  record.setUpdatetime(current);
		 List<SubjectStatisticalBo> Bos = subjectStatisticalMapper.selectTodayzaitiByTime(record);
			List<String> listtime = DateFormatUtil.getHourList();
			Collections.sort(listtime);
		    
			List<String> odtime= new ArrayList<String>();
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH");//设置日期格式
			System.out.println(dft.format(new Date()));// new Date()为获取当前系统时间
			for(int i=0;i<Bos.size();i++){
				String uptime = Bos.get(i).getUpdatetime().substring(11, 13);
				Bos.get(i).setUpdatetime(uptime);
				odtime.add(uptime);
			}
			System.out.println(listtime);
			for(int i=0;i<listtime.size();i++){
				SubjectStatisticalBo ss = new SubjectStatisticalBo();
				if(odtime.contains(listtime.get(i))){
					for(int k=0;k<Bos.size();k++){
						if(Bos.get(k).getUpdatetime().contains(listtime.get(i))){
							ss = Bos.get(k);
						}
					}
				}else{
					ss.setUpdatetime(listtime.get(i));
					ss.setBbsAcount(0);
					ss.setBokeAcount(0);
					ss.setInfoAcount(0);
					ss.setNewsAcount(0);
					ss.setOtherAcount(0);
					ss.setPinglunAcount(0);
					ss.setPingmeiAcount(0);
					ss.setShipinAcount(0);
					ss.setTiebaAcount(0);
					ss.setWeiboAcount(0);
					ss.setWeixinAcount(0);
				}
				sBos.add(ss);
			}
		return sBos;
	}
	@Override
	public List<SubjectStatisticalBo> selectIndexyuqing(SubjectStatisticalBo record, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		
		 List<SubjectStatisticalBo> list = new ArrayList<SubjectStatisticalBo>();
		 String time = record.getUpdatetime();
		 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		 String current = df.format(new Date());
		 record.setUpdatetime(current);
		 list = this.selectByTimeAcount(record);
		
		return list;
	}
	
	
}
