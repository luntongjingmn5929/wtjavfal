package com.bayside.app.opinion.mobile.war.subject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayside.app.opinion.mobile.war.subject.bo.SubjectStatisticalBo;
import com.bayside.app.opinion.mobile.war.subject.model.SubjectStatistical;

public interface SubjectStatisticalMapper {
	List<SubjectStatisticalBo> selectTodayzaitiByTime(SubjectStatisticalBo record);
	 SubjectStatistical  selectSumAcountByTime(@Param("time")String time,@Param("userid")String userid,@Param("emotion")String emotion);
	   //首页查询媒体信息selectMediaAcountByTime
    SubjectStatistical selectMediaAcountByTime(@Param("time")String time,@Param("userid")String userid);
	List<SubjectStatisticalBo> selectByTimeAcount(SubjectStatisticalBo record);
	List<SubjectStatistical> selectzaitiByTime(@Param("updatetime")String time,@Param("userid")String userid,@Param("emotion")String emotion);
}