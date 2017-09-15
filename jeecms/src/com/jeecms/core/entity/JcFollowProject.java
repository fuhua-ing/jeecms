package com.jeecms.core.entity;


import java.util.Date;

public class JcFollowProject implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CmsUser cmsUser;
	private Integer projectId;
	private Integer tag;
	private Date createTime;
	private JcReleaseTransfer projectTransfer;
	private ProjectRelease projectRelease;

	public JcFollowProject() {
	}

	public JcFollowProject(CmsUser cmsUser, Integer projectId, Integer tag, Date createTime) {
		this.cmsUser = cmsUser;
		this.projectId = projectId;
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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public JcReleaseTransfer getProjectTransfer() {
		return projectTransfer;
	}

	public void setProjectTransfer(JcReleaseTransfer projectTransfer) {
		this.projectTransfer = projectTransfer;
	}

	public ProjectRelease getProjectRelease() {
		return projectRelease;
	}

	public void setProjectRelease(ProjectRelease projectRelease) {
		this.projectRelease = projectRelease;
	}
}
