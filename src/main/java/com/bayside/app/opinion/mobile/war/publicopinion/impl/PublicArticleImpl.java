package com.bayside.app.opinion.mobile.war.publicopinion.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.mobile.war.mynews.bo.PersonManageBo;
import com.bayside.app.opinion.mobile.war.mynews.bo.PersonmanagemarticleBo;
import com.bayside.app.opinion.mobile.war.mynews.dao.PersonManageMapper;
import com.bayside.app.opinion.mobile.war.mynews.dao.PersonmanagemarticleMapper;
import com.bayside.app.opinion.mobile.war.mynews.model.PersonManage;
import com.bayside.app.opinion.mobile.war.mynews.model.Personmanagemarticle;
import com.bayside.app.opinion.mobile.war.publicopinion.bo.SubJectArticleBo;
import com.bayside.app.opinion.mobile.war.publicopinion.controller.PublicArticleController;
import com.bayside.app.opinion.mobile.war.publicopinion.dao.SubjectArticleBoMapper;
import com.bayside.app.opinion.mobile.war.publicopinion.dao.SubjectArticleMapper;
import com.bayside.app.opinion.mobile.war.publicopinion.dao.SubjectMAarticleMapper;
import com.bayside.app.opinion.mobile.war.publicopinion.model.SubjectArticle;
import com.bayside.app.opinion.mobile.war.publicopinion.model.SubjectMAarticle;
import com.bayside.app.opinion.mobile.war.publicopinion.service.PublicArticleService;
import com.bayside.app.util.AppConstant;
import com.bayside.app.util.DateFormatUtil;
@Service("publicArticleImpl")
public class PublicArticleImpl implements PublicArticleService{
   @Autowired
	private SubjectArticleMapper subjectArticleMapper;
   @Autowired
   private SubjectArticleBoMapper subjectArticleBoMapper;
   @Autowired
   private SubjectMAarticleMapper subjectMAarticleMapper;
   @Autowired
   private PersonManageMapper personManageMapper;
   @Autowired
   private PersonmanagemarticleMapper personmanagemarticleMapper;
	private static final Logger log = Logger.getLogger(PublicArticleImpl.class);
	@Override
	public List<SubJectArticleBo> selectPublicArticle(String userid) {
     	/*return subjectArticleMapper.selectPublicArticle(userid);*/
		return null;
	}
	@Override
	public List<SubJectArticleBo> selectAllByUserId(SubJectArticleBo record) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.selectAllByUserId(record);
	}
	@Override
	public List<SubJectArticleBo> filterCom(SubJectArticleBo record) {
		List<SubJectArticleBo> list = subjectArticleBoMapper.filterCom(record);
	
	
		// TODO Auto-generated method stub
		return list;
	}
	
	@Override	
	public SubJectArticleBo showArticleDetailById(SubJectArticleBo record) {
		String shards = AppConstant.solrUrl.ARTICLE+","+AppConstant.solrUrl.METASEARCHPAGE+","+AppConstant.solrUrl.TIEBAPAGE+","+AppConstant.solrUrl.WEIBOPAGE+","+AppConstant.solrUrl.WEIXINPAGE;
		SolrQuery params = new SolrQuery();
		params.set("qt", "/select");
		params.set("q", "id"+":"+record.getArticleid());
		params.set("shards", shards);
		QueryResponse query;
		SubJectArticleBo sb = new SubJectArticleBo();
		HttpSolrClient solrServer = new HttpSolrClient(AppConstant.solrUrl.WEIBOPAGE);
		SubjectMAarticle sm = subjectMAarticleMapper.selectByPrimaryKey(record.getMid());
		try {
			query = solrServer.query(params);
			SolrDocumentList results = query.getResults();
			if(results.getNumFound()!=0){
				SolrDocument solrDocument = results.get(0);
				String id =  solrDocument.getFieldValue("id")!=null?solrDocument.getFieldValue("id").toString().replace("[", "").replace("]",""):"";
				String dataSource = solrDocument.getFieldValue("dataSource")!=null?solrDocument.getFieldValue("dataSource").toString().replace("[", "").replace("]",""):"";
				String time = solrDocument.getFieldValue("pubdate")!=null?solrDocument.getFieldValue("pubdate").toString().replace("[", "").replace("]",""):"";
				String emotion = solrDocument.getFieldValue("emotion")!=null?solrDocument.getFieldValue("emotion").toString().replace("[", "").replace("]",""):"";
				String author = solrDocument.getFieldValue("author")!=null?solrDocument.getFieldValue("author").toString().replace("[", "").replace("]",""):"";
				String repeatcount = solrDocument.getFieldValue("repeatcount")!=null?solrDocument.getFieldValue("repeatcount").toString().replace("[", "").replace("]",""):"";
				String commtcount = solrDocument.getFieldValue("commtcount")!=null?solrDocument.getFieldValue("commtcount").toString().replace("[", "").replace("]",""):"";
				String readcount = solrDocument.getFieldValue("readcount")!=null?solrDocument.getFieldValue("readcount").toString().replace("[", "").replace("]",""):"";
				String url = solrDocument.getFieldValue("url")!=null?solrDocument.getFieldValue("url").toString().replace("[", "").replace("]",""):"";
				String title = solrDocument.getFieldValue("title_cn")!=null?solrDocument.getFieldValue("title_cn").toString().replace("[", "").replace("]",""):"";
				String content = solrDocument.getFieldValue("content_cn")!=null?solrDocument.getFieldValue("content_cn").toString().replace("[", "").replace("]",""):"";
				String negativeWord = solrDocument.getFieldValue("negativeWord")!=null?solrDocument.getFieldValue("negativeWord").toString().replace("[", "").replace("]",""):"";
			    String positiveWord = solrDocument.getFieldValue("positiveWord")!=null?solrDocument.getFieldValue("positiveWord").toString().replace("[", "").replace("]",""):"";
			    String formats = solrDocument.getFieldValue("formats")!=null?solrDocument.getFieldValue("formats").toString().replace("[", "").replace("]",""):"";
			    String html = solrDocument.getFieldValue("html")!=null?solrDocument.getFieldValue("html").toString().replace("[", "").replace("]",""):"";
				sb.setId(id);
			   sb.setAuthor(author);
			   sb.setDataSource(dataSource);
			   sb.setEdtime(time);
			   sb.setEmotion(emotion);
			   sb.setAuthor(author);
			   sb.setUrl(url);
			   sb.setTittle(title);
			   sb.setContent(content);
			   sb.setNegativeWord(negativeWord);
			   sb.setPositiveWord(positiveWord);
			   sb.setFormats(AppConstant.covent.enToCn(formats));
			   sb.setHtml(html);
			   if(sm!=null){
				   sb.setKeywordRule(sm.getKeywordRule());
			   }
			   if("paper".equals(record.getPapertag())){
				   if(sb!=null){
					   sb.setKeywordRule(sb.getKeywordRule());
				   }else{
					   sb.setKeywordRule("");
				   }
				   
			   }
			   if(!"".equals(repeatcount) && null!=repeatcount){
				   int repeatco = Integer.parseInt(repeatcount);
				   sb.setRepeatcount(repeatco);
			   }else{
				   sb.setRepeatcount(-1);
			   }
			 
			   if(!"".equals(commtcount) && null!=commtcount){
				   int commt = Integer.parseInt(commtcount);
				   sb.setCommtcount(commt);
			   }else{
				   sb.setCommtcount(-1);
			   }
			   if(!"".equals(readcount) && null!=readcount){
				   int read= Integer.parseInt(readcount);
				   sb.setReadcount(read);
			   }else{
				   sb.setReadcount(-1);
			   }
			   return sb;
			}else{
				SubjectArticle subArticle = subjectArticleMapper.selectByPrimaryKey(record.getArticleid());
				if(subArticle==null){
					return null;
				}
				BeanUtils.copyProperties(subArticle, sb);
				sb.setFormats(AppConstant.covent.enToCn(subArticle.getFormats()));
				   if(subArticle.getPubdate()!=null){
					   sb.setEdtime(DateFormatUtil.dateFormatStringType(subArticle.getPubdate(), "yyyy-MM-dd HH:mm:ss")); 
				   }
				   if(subArticle.getRepeatcount()!=null){
					   sb.setRepeatcount(subArticle.getRepeatcount());
				   }else{
					   sb.setRepeatcount(-1);
				   }
				 
				   if(subArticle.getCommtcount()!=null){
					   sb.setCommtcount(subArticle.getCommtcount());
				   }else{
					   sb.setCommtcount(-1);
				   }
				   if(subArticle.getReadcount()!=null){
					   sb.setReadcount(subArticle.getReadcount());
				   }else{
					   sb.setReadcount(-1);
				   }
				   if(sm!=null){
					   sb.setKeywordRule(sm.getKeywordRule());
				   }
				  
				   return sb;
				   }
		} catch (SolrServerException e) {
			System.out.println(e);
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			System.out.println(e);
			log.error(e.getMessage(),e);
		}
		return sb;
	}
	
	@Override
	public SubJectArticleBo selectArticleDetailById(SubJectArticleBo record) {
		SubJectArticleBo suba = new SubJectArticleBo();
		if("paper".equals(record.getPapertag())){
			suba  = subjectArticleBoMapper.myPaperDetail(record.getMid());
		}else{
			suba = subjectArticleBoMapper.myArticleDetail(record.getMid());
		}
		return suba;
		
	}
	@Override
	public int updateCollection(SubjectMAarticle record) {
		// TODO Auto-generated method stub
		return subjectMAarticleMapper.updateCollection(record);
	}
	@Override
	public List<SubJectArticleBo> selectAllByCollection(SubJectArticleBo record) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.selectAllByCollection(record);
	}
	@Override
	public List<SubJectArticleBo> myPaperListInfo(PersonmanagemarticleBo record) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.myPaperListInfo(record);
	}
	@Override
	public List<PersonManage> selectpageAll(PersonManageBo record) {
		// TODO Auto-generated method stub
		return personManageMapper.selectpageAll(record);
	}
	@Override
	public int updatepaperCollection(Personmanagemarticle record) {
		// TODO Auto-generated method stub
		return personmanagemarticleMapper.updatepaperCollection(record);
	}
	@Override
	public SubjectMAarticle selectCollectoinById(String id) {
		// TODO Auto-generated method stub
		return subjectMAarticleMapper.selectByPrimaryKey(id);
	}
	@Override
	public Personmanagemarticle selectCollectionByPmId(String id) {
		// TODO Auto-generated method stub
		return personmanagemarticleMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<SubJectArticleBo> selectPaperByCollection(SubJectArticleBo record) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.selectPaperByCollection(record);
	}
	@Override
	public SubJectArticleBo selectArticleDetailById(String articleid) {
		// TODO Auto-generated method stub
		
       SubJectArticleBo sb = new SubJectArticleBo();
		
		SubjectArticle subArticle = subjectArticleMapper.selectByPrimaryKey(articleid);
		if (null == subArticle) {
			return null;
		}
		BeanUtils.copyProperties(subArticle, sb);
		sb.setFormats(AppConstant.covent.enToCn(subArticle.getFormats()));
		sb.setTime(DateFormatUtil.dateFormatStringType(subArticle.getUpdatetime(), "yyyy-MM-dd HH:mm:ss"));
		if (null!= subArticle.getPubdate()) {
			sb.setEdtime(DateFormatUtil.dateFormatStringType(subArticle.getPubdate(), "yyyy-MM-dd HH:mm:ss"));
		}
		if (null!=subArticle.getRepeatcount()) {
			sb.setRepeatcount(subArticle.getRepeatcount());
		} else {
			sb.setRepeatcount(-1);
		}
		if (null!=subArticle.getCommtcount()) {
			sb.setCommtcount(subArticle.getCommtcount());
		} else {
			sb.setCommtcount(-1);
		}
		if (null!=subArticle.getReadcount()) {
			sb.setReadcount(subArticle.getReadcount());
		} else {
			sb.setReadcount(-1);
		}
		return sb;
	}
	@Override
	public SubJectArticleBo selectAllByPage(SubJectArticleBo record) {
		SubJectArticleBo sac = new SubJectArticleBo();
		if(1== record.getIsrepeat()){
			sac = subjectArticleBoMapper.selectAllByPage(record);
		}else{
			sac = subjectArticleBoMapper.repeatselectAllByPage(record);
		}
		return sac;
	}
	@Override
	public SubJectArticleBo selectTotalCollection(SubJectArticleBo record) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.selectTotalCollection(record);
	}
	@Override
	public SubJectArticleBo selectPaperTotalCollection(SubJectArticleBo record) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.selectPaperTotalCollection(record);
	}
	@Override
	public SubJectArticleBo selectTotalPaperList(PersonmanagemarticleBo record) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.selectTotalPaperList(record);
	}
	@Override
	public SubJectArticleBo myArticleDetail(String id) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.myArticleDetail(id);
	}
	@Override
	public SubjectMAarticle selectSimidsById(String id) {
		// TODO Auto-generated method stub
		return subjectMAarticleMapper.selectSimidsById(id);
	}
	@Override
	public List<SubJectArticleBo> filterSimlarArticle(SubJectArticleBo record) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.filterSimlarArticle(record);
	}
	@Override
	public SubJectArticleBo repeatselectAllByPage(SubJectArticleBo record) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.repeatselectAllByPage(record);
	}
	@Override
	public List<SubJectArticleBo> repeatfilterCom(SubJectArticleBo stage) {
		// TODO Auto-generated method stub
		return subjectArticleBoMapper.repeatfilterCom(stage);
	}

}
