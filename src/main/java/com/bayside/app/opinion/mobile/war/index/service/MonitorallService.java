package com.bayside.app.opinion.mobile.war.index.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bayside.app.opinion.mobile.war.publicopinion.bo.SubJectArticleBo;
import com.bayside.app.opinion.mobile.war.publicopinion.model.Subject;
import com.bayside.app.opinion.mobile.war.subject.bo.SubjectStatisticalBo;
import com.bayside.app.opinion.mobile.war.subject.model.SubjectStatistical;

public interface MonitorallService {

 //舆情监测信息
 List<SubjectStatisticalBo> selectByTimeAcount(SubjectStatisticalBo record);
 //获取最新负面信息
 List<SubJectArticleBo> selectlastsubjectarticle(String userid,List<String> list);
 //查询媒体类型信息
 SubjectStatistical selectMediaAcountByTime(String time,String userid);
 //查询专题信息
 List<SubJectArticleBo>  selectnewSixsubjectarticle(List list,String userid);
 //载体趋势图
 List<SubjectStatistical> selectzaitiByTime(String time,String userid,String emotion);
 //预警推送
 List<SubJectArticleBo>  selectnewWarningarticle(String userid,int warning,List<String> list);

 //查询媒体类型信息 正面 负面 中性
 SubjectStatistical  selectSumAcountByTime(String time,String userid,String emotion);
 List<Subject> selectByUserId(String userid);
 List<SubjectStatisticalBo> selectTodayzaitiByTime(SubjectStatisticalBo record,HttpServletRequest request);
 List<SubjectStatisticalBo> selectIndexyuqing(SubjectStatisticalBo record,HttpServletRequest request);
}
