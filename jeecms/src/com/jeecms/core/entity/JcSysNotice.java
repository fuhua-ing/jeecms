package com.jeecms.core.entity;


import java.util.Date;

public class JcSysNotice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CmsUser cmsUser;
	private String recvUserType;
	private String title;
	private String content;
	private Date createTime;

	public JcSysNotice() {
	}

	public JcSysNotice(CmsUser cmsUser, String recvUserType, String title,
			String content, Date createTime) {
		this.cmsUser = cmsUser;
		this.recvUserType = recvUserType;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CmsUser getCmsUser() {
		return this.cmsUser;
	}

	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}

	public String getRecvUserType() {
		return this.recvUserType;
	}

	public void setRecvUserType(String recvUserType) {
		this.recvUserType = recvUserType;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
