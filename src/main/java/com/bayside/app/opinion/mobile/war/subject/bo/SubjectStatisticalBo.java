package com.bayside.app.opinion.mobile.war.subject.bo;


import java.util.List;


public class SubjectStatisticalBo {
    private String id;

    private String subjectid;
    
    private String subjectname;
    
    private Integer infoAcount;

    private Integer newsAcount;

    private Integer bbsAcount;

    private Integer bokeAcount;

    private Integer weiboAcount;

    private Integer pingmeiAcount;

    private Integer weixinAcount;

    private Integer shipinAcount;

    private Integer appAcount;

    private Integer pinglunAcount;

    private Integer otherAcount;
    
    private Integer negativeAcount;
    
    private Integer tiebaAcount;
    
    private String emotion;
    
    private String updatetime;
    
    private String pubdate;

    private String startTime;
    
    private String endTime;
    
    private String userid;
    
    private Integer positiveNumber;
    private Integer negitiveNumber;
    private Integer neturalNumber;
    private Integer sumPositiveaccount;
    private Integer sumnegitiveaccount;
    private Integer sumneturalaccount;
    private List<String> time;
    private List<Integer> nenumber;
    private List<Integer> allnumber;
    private String classifyid;
    private String articleid;
  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid == null ? null : subjectid.trim();
    }

    public Integer getInfoAcount() {
        return infoAcount;
    }

    public void setInfoAcount(Integer infoAcount) {
        this.infoAcount = infoAcount;
    }

    public Integer getNewsAcount() {
        return newsAcount;
    }

    public void setNewsAcount(Integer newsAcount) {
        this.newsAcount = newsAcount;
    }

    public Integer getBbsAcount() {
        return bbsAcount;
    }

    public void setBbsAcount(Integer bbsAcount) {
        this.bbsAcount = bbsAcount;
    }

    public Integer getBokeAcount() {
        return bokeAcount;
    }

    public void setBokeAcount(Integer bokeAcount) {
        this.bokeAcount = bokeAcount;
    }

    public Integer getWeiboAcount() {
        return weiboAcount;
    }

    public void setWeiboAcount(Integer weiboAcount) {
        this.weiboAcount = weiboAcount;
    }

    public Integer getPingmeiAcount() {
        return pingmeiAcount;
    }

    public void setPingmeiAcount(Integer pingmeiAcount) {
        this.pingmeiAcount = pingmeiAcount;
    }

    public Integer getWeixinAcount() {
        return weixinAcount;
    }

    public void setWeixinAcount(Integer weixinAcount) {
        this.weixinAcount = weixinAcount;
    }

    public Integer getShipinAcount() {
        return shipinAcount;
    }

    public void setShipinAcount(Integer shipinAcount) {
        this.shipinAcount = shipinAcount;
    }

    public Integer getAppAcount() {
        return appAcount;
    }

    public void setAppAcount(Integer appAcount) {
        this.appAcount = appAcount;
    }

    public Integer getPinglunAcount() {
        return pinglunAcount;
    }

    public void setPinglunAcount(Integer pinglunAcount) {
        this.pinglunAcount = pinglunAcount;
    }

    public Integer getOtherAcount() {
        return otherAcount;
    }

    public void setOtherAcount(Integer otherAcount) {
        this.otherAcount = otherAcount;
    }
	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
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

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getPositiveNumber() {
		return positiveNumber;
	}

	public void setPositiveNumber(Integer positiveNumber) {
		this.positiveNumber = positiveNumber;
	}

	public Integer getNegitiveNumber() {
		return negitiveNumber;
	}

	public void setNegitiveNumber(Integer negitiveNumber) {
		this.negitiveNumber = negitiveNumber;
	}

	public Integer getNeturalNumber() {
		return neturalNumber;
	}

	public void setNeturalNumber(Integer neturalNumber) {
		this.neturalNumber = neturalNumber;
	}


	public List<String> getTime() {
		return time;
	}

	public void setTime(List<String> time) {
		this.time = time;
	}

	public List<Integer> getNenumber() {
		return nenumber;
	}

	public void setNenumber(List<Integer> nenumber) {
		this.nenumber = nenumber;
	}

	public List<Integer> getAllnumber() {
		return allnumber;
	}

	public void setAllnumber(List<Integer> allnumber) {
		this.allnumber = allnumber;
	}

	public Integer getTiebaAcount() {
		return tiebaAcount;
	}

	public void setTiebaAcount(Integer tiebaAcount) {
		this.tiebaAcount = tiebaAcount;
	}

	public String getClassifyid() {
		return classifyid;
	}

	public void setClassifyid(String classifyid) {
		this.classifyid = classifyid;
	}

	public Integer getNegativeAcount() {
		return negativeAcount;
	}

	public void setNegativeAcount(Integer negativeAcount) {
		this.negativeAcount = negativeAcount;
	}

	public Integer getSumPositiveaccount() {
		return sumPositiveaccount;
	}

	public void setSumPositiveaccount(Integer sumPositiveaccount) {
		this.sumPositiveaccount = sumPositiveaccount;
	}

	public Integer getSumnegitiveaccount() {
		return sumnegitiveaccount;
	}

	public void setSumnegitiveaccount(Integer sumnegitiveaccount) {
		this.sumnegitiveaccount = sumnegitiveaccount;
	}

	public Integer getSumneturalaccount() {
		return sumneturalaccount;
	}

	public void setSumneturalaccount(Integer sumneturalaccount) {
		this.sumneturalaccount = sumneturalaccount;
	}

	public String getArticleid() {
		return articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

}