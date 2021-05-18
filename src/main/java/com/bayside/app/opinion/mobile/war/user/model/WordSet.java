package com.bayside.app.opinion.mobile.war.user.model;

import java.util.Date;

public class WordSet {
    private String id;

    private String name;

    private Integer setword;

    private Integer cansetword;

    private String userid;

    private Integer status;
    
    private Date endtime;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSetword() {
		return setword;
	}

	public void setSetword(Integer setword) {
		this.setword = setword;
	}

	public Integer getCansetword() {
		return cansetword;
	}

	public void setCansetword(Integer cansetword) {
		this.cansetword = cansetword;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

  
}