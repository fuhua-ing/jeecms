package com.jeecms.core.entity;


import java.util.Date;

public class JcUserNotice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CmsUser cmsUser;
	private Byte result;
	private String title;
	private String content;
	private Byte readStatus;
	private Byte tag;
	private Date createTime;

	public JcUserNotice() {
	}

	public JcUserNotice(CmsUser cmsUser, Byte result, String title,
			String content, Byte readStatus, Byte tag, Date createTime) {
		this.cmsUser = cmsUser;
		this.result = result;
		this.title = title;
		this.content = content;
		this.readStatus = readStatus;
		this.tag = tag;
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

	public Byte getResult() {
		return this.result;
	}

	public void setResult(Byte result) {
		this.result = result;
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

	public Byte getReadStatus() {
		return this.readStatus;
	}

	public void setReadStatus(Byte readStatus) {
		this.readStatus = readStatus;
	}

	public Byte getTag() {
		return this.tag;
	}

	public void setTag(Byte tag) {
		this.tag = tag;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
