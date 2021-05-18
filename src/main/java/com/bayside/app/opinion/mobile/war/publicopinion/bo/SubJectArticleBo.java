package com.bayside.app.opinion.mobile.war.publicopinion.bo;

import java.util.List;

import com.bayside.app.opinion.mobile.war.publicopinion.model.SubjectArticle;


public class SubJectArticleBo extends SubjectArticle{
	private String subjectid; 
	
    private String articleid;

    private String userid;
    
	private String keywordRule; //关键词规则

    private Boolean reportinfo; //是否上报信息     默认为0，为否，1为真

    private Boolean attention;  //是否关注      默认为0，为否，1为真

    private Boolean warning;  //是否加入预警   默认为0，为否，1为真

    private Boolean readsign;  //是否已读     默认为0，为否，1为真

    private Boolean briefing;  //加入简报    默认为0，为否，1为真
    private List<String> medialist;
    private List<String> emotionlist;
    private List<String> time1;
    private List<String> number1;
    private List<String> time2;
    private List<String> number2;
    private String time;
    
    private boolean positiveb;
    private boolean negativeb;
    private boolean neutralb;
 private String startTime;
    
    private String endTime;
    
    
    private List<String> articlesid;
    
    private List<String> formatslist;
    
    private List<String> timelist;
   
    private String attentiontime;
    
    private String classifyid;
    
   /* private String updatetime;*/
  
    
    private String mid;
    
    private String weidu;
    
    private String edtime;
    
    private boolean showupdatetime;
    private boolean showdatasource;
    private boolean showauthor;
    private boolean showformats;
    private boolean showkeyword;
    private boolean showPubdate;
    private String sttime;
    private String pubtime;
    private String uptime;
    private Integer collection;
    
    private String wei;
    
    private String papertag;
    
    private Integer start;
    private Integer size;
    
    private Integer total;
    
    private String warnWord;
    
    private List<String> mids;
    private Integer simnum;
    
    private Integer isrepeat;
    
    private Integer trade;
    
    private Double dependency;
    private Integer simnumorder;
    private Integer istrade;
  
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public String getArticleid() {
		return articleid;
	}
	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getKeywordRule() {
		return keywordRule;
	}
	public void setKeywordRule(String keywordRule) {
		this.keywordRule = keywordRule;
	}
	public Boolean getReportinfo() {
		return reportinfo;
	}
	public void setReportinfo(Boolean reportinfo) {
		this.reportinfo = reportinfo;
	}
	public Boolean getAttention() {
		return attention;
	}
	public void setAttention(Boolean attention) {
		this.attention = attention;
	}
	public Boolean getWarning() {
		return warning;
	}
	public void setWarning(Boolean warning) {
		this.warning = warning;
	}
	public Boolean getReadsign() {
		return readsign;
	}
	public void setReadsign(Boolean readsign) {
		this.readsign = readsign;
	}
	public Boolean getBriefing() {
		return briefing;
	}
	public void setBriefing(Boolean briefing) {
		this.briefing = briefing;
	}
	public List<String> getMedialist() {
		return medialist;
	}
	public void setMedialist(List<String> medialist) {
		this.medialist = medialist;
	}
	public List<String> getEmotionlist() {
		return emotionlist;
	}
	public void setEmotionlist(List<String> emotionlist) {
		this.emotionlist = emotionlist;
	}
	public List<String> getTime1() {
		return time1;
	}
	public void setTime1(List<String> time1) {
		this.time1 = time1;
	}
	public List<String> getNumber1() {
		return number1;
	}
	public void setNumber1(List<String> number1) {
		this.number1 = number1;
	}
	public List<String> getTime2() {
		return time2;
	}
	public void setTime2(List<String> time2) {
		this.time2 = time2;
	}
	public List<String> getNumber2() {
		return number2;
	}
	public void setNumber2(List<String> number2) {
		this.number2 = number2;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isPositiveb() {
		return positiveb;
	}
	public void setPositiveb(boolean positiveb) {
		this.positiveb = positiveb;
	}
	public boolean isNegativeb() {
		return negativeb;
	}
	public void setNegativeb(boolean negativeb) {
		this.negativeb = negativeb;
	}
	public boolean isNeutralb() {
		return neutralb;
	}
	public void setNeutralb(boolean neutralb) {
		this.neutralb = neutralb;
	}
	public List<String> getArticlesid() {
		return articlesid;
	}
	public void setArticlesid(List<String> articlesid) {
		this.articlesid = articlesid;
	}
	public List<String> getFormatslist() {
		return formatslist;
	}
	public void setFormatslist(List<String> formatslist) {
		this.formatslist = formatslist;
	}
	public List<String> getTimelist() {
		return timelist;
	}
	public void setTimelist(List<String> timelist) {
		this.timelist = timelist;
	}
	public String getAttentiontime() {
		return attentiontime;
	}
	public void setAttentiontime(String attentiontime) {
		this.attentiontime = attentiontime;
	}
	public String getClassifyid() {
		return classifyid;
	}
	public void setClassifyid(String classifyid) {
		this.classifyid = classifyid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getWeidu() {
		return weidu;
	}
	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}
	public String getEdtime() {
		return edtime;
	}
	public void setEdtime(String edtime) {
		this.edtime = edtime;
	}
	public boolean isShowupdatetime() {
		return showupdatetime;
	}
	public void setShowupdatetime(boolean showupdatetime) {
		this.showupdatetime = showupdatetime;
	}
	public boolean isShowdatasource() {
		return showdatasource;
	}
	public void setShowdatasource(boolean showdatasource) {
		this.showdatasource = showdatasource;
	}
	public boolean isShowauthor() {
		return showauthor;
	}
	public void setShowauthor(boolean showauthor) {
		this.showauthor = showauthor;
	}
	public boolean isShowformats() {
		return showformats;
	}
	public void setShowformats(boolean showformats) {
		this.showformats = showformats;
	}
	public boolean isShowkeyword() {
		return showkeyword;
	}
	public void setShowkeyword(boolean showkeyword) {
		this.showkeyword = showkeyword;
	}
	public boolean isShowPubdate() {
		return showPubdate;
	}
	public void setShowPubdate(boolean showPubdate) {
		this.showPubdate = showPubdate;
	}
	public String getSttime() {
		return sttime;
	}
	public void setSttime(String sttime) {
		this.sttime = sttime;
	}
	public String getPubtime() {
		return pubtime;
	}
	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
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
	public Integer getCollection() {
		return collection;
	}
	public void setCollection(Integer collection) {
		this.collection = collection;
	}
	public String getWei() {
		return wei;
	}
	public void setWei(String wei) {
		this.wei = wei;
	}
	public String getPapertag() {
		return papertag;
	}
	public void setPapertag(String papertag) {
		this.papertag = papertag;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getWarnWord() {
		return warnWord;
	}
	public void setWarnWord(String warnWord) {
		this.warnWord = warnWord;
	}
	public List<String> getMids() {
		return mids;
	}
	public void setMids(List<String> mids) {
		this.mids = mids;
	}
	public Integer getSimnum() {
		return simnum;
	}
	public void setSimnum(Integer simnum) {
		this.simnum = simnum;
	}
	public Integer getIsrepeat() {
		return isrepeat;
	}
	public void setIsrepeat(Integer isrepeat) {
		this.isrepeat = isrepeat;
	}
	public Integer getTrade() {
		return trade;
	}
	public void setTrade(Integer trade) {
		this.trade = trade;
	}
	public Double getDependency() {
		return dependency;
	}
	public void setDependency(Double dependency) {
		this.dependency = dependency;
	}
	public Integer getSimnumorder() {
		return simnumorder;
	}
	public void setSimnumorder(Integer simnumorder) {
		this.simnumorder = simnumorder;
	}
	public Integer getIstrade() {
		return istrade;
	}
	public void setIstrade(Integer istrade) {
		this.istrade = istrade;
	}
  }
