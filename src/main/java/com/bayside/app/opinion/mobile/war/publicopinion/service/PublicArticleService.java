package com.bayside.app.opinion.mobile.war.publicopinion.service;

import java.util.List;

import com.bayside.app.opinion.mobile.war.mynews.bo.PersonManageBo;
import com.bayside.app.opinion.mobile.war.mynews.bo.PersonmanagemarticleBo;
import com.bayside.app.opinion.mobile.war.mynews.model.PersonManage;
import com.bayside.app.opinion.mobile.war.mynews.model.Personmanagemarticle;
import com.bayside.app.opinion.mobile.war.publicopinion.bo.SubJectArticleBo;
import com.bayside.app.opinion.mobile.war.publicopinion.model.SubjectMAarticle;


public interface PublicArticleService {
	List<SubJectArticleBo> selectPublicArticle(String userid);
	List<SubJectArticleBo> selectAllByUserId(SubJectArticleBo record);
	List<SubJectArticleBo> filterCom(SubJectArticleBo record);
	public SubJectArticleBo selectArticleDetailById(SubJectArticleBo record);
	int updateCollection(SubjectMAarticle record);
	/**
	 * 
	 * <p>方法名称：selectAllByCollection</p>
	 * <p>方法描述：收藏文章列表</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @since  2016年12月6日
	 * <p> history 2016年12月6日 Administrator  创建   <p>
	 */
	List<SubJectArticleBo> selectAllByCollection(SubJectArticleBo record);
	/**
	 * 
	 * <p>方法名称：myPaperListInfo</p>
	 * <p>方法描述：我的报纸列表</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @since  2016年12月7日
	 * <p> history 2016年12月7日 Administrator  创建   <p>
	 */
	List<SubJectArticleBo> myPaperListInfo(PersonmanagemarticleBo record);

	 List<PersonManage> selectpageAll(PersonManageBo record);
	 int updatepaperCollection(Personmanagemarticle record);
	 SubjectMAarticle selectCollectoinById(String id);
	 Personmanagemarticle selectCollectionByPmId(String id);
	 List<SubJectArticleBo> selectPaperByCollection(SubJectArticleBo record);
	 SubJectArticleBo selectArticleDetailById(String articleid);
	 SubJectArticleBo selectAllByPage(SubJectArticleBo record);
	 SubJectArticleBo selectTotalCollection(SubJectArticleBo record);
	 SubJectArticleBo selectPaperTotalCollection(SubJectArticleBo record);
	 SubJectArticleBo selectTotalPaperList(PersonmanagemarticleBo record);
	 SubJectArticleBo myArticleDetail(String id);
	 SubJectArticleBo showArticleDetailById(SubJectArticleBo record);
	 SubjectMAarticle selectSimidsById(String id);
	 List<SubJectArticleBo> filterSimlarArticle(SubJectArticleBo record);
	 SubJectArticleBo repeatselectAllByPage(SubJectArticleBo record);
	   List<SubJectArticleBo> repeatfilterCom(SubJectArticleBo stage);
	

}
