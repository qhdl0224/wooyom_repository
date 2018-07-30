package com.lectopia.lab.domain;

import java.util.Date;

public class UserVO {
	private int idx;
	private String userid;
	private String userpwd;
	private Date regdate;
	
	public UserVO() {
		super();
	}
	public UserVO(int idx, String userid, String userpwd, Date regdate) {
		super();
		this.idx = idx;
		this.userid = userid;
		this.userpwd = userpwd;
		this.regdate = regdate;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
