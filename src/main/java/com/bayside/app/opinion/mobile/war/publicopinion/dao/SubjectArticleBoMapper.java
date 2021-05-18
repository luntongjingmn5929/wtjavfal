package com.bayside.app.opinion.mobile.war.publicopinion.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayside.app.opinion.mobile.war.mynews.bo.PersonmanagemarticleBo;
import com.bayside.app.opinion.mobile.war.publicopinion.bo.SubJectArticleBo;



/**
 * <p>Title: SubjectArticleMapper</P>
 * <p>Description: 专题文章的映射表</p>
 * <p>Copyright: 山东贝赛信息科技有限公司 Copyright (c) 2016</p>
 * @author Lixp
 * @version 1.0
 * @since 2016年7月23日
 */
public interface SubjectArticleBoMapper {
   List<SubJectArticleBo> selectAllByUserId(SubJectArticleBo record);
   List<SubJectArticleBo> filterCom(SubJectArticleBo record);

   List<SubJectArticleBo> selectlastsubjectarticle(@Param("userid")String userid,@Param("list")List list);
   List<SubJectArticleBo>  selectnewSixsubjectarticle(@Param("list")List list,@Param("userid")String userid);
   List<SubJectArticleBo>  selectnewWarningarticle(@Param("userid")String userid,@Param("warning")int warning,@Param("list")List list);
   List<SubJectArticleBo> selectAllSubjectarticle(@Param("userid")String userid);
   List<SubJectArticleBo> selectAllByCollection(SubJectArticleBo record);
   List<SubJectArticleBo> selectPaperByCollection(SubJectArticleBo record);
   List<SubJectArticleBo> myPaperListInfo(PersonmanagemarticleBo record);
   SubJectArticleBo myPaperDetail (String id);
   SubJectArticleBo selectAllByPage(SubJectArticleBo record);
   SubJectArticleBo selectTotalCollection(SubJectArticleBo record);
   SubJectArticleBo selectPaperTotalCollection(SubJectArticleBo record);
   SubJectArticleBo selectTotalPaperList(PersonmanagemarticleBo record);
   SubJectArticleBo myArticleDetail(String id);
   List<SubJectArticleBo> filterSimlarArticle(SubJectArticleBo record);
   SubJectArticleBo repeatselectAllByPage(SubJectArticleBo record);
   List<SubJectArticleBo> repeatfilterCom(SubJectArticleBo stage);
}

