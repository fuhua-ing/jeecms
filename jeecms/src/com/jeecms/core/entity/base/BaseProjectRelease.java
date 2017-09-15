package com.jeecms.core.entity.base;

import java.io.Serializable;


import java.util.Date;

import com.jeecms.core.entity.CmsUser;

/**
 * This is an object that contains data related to the jc_project_release table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_project_release"
 */

public abstract class BaseProjectRelease  implements Serializable {


     private Integer id;
     private String projectName;
     private String companyName;
     private String addressP;
     private String provinceName;
     private String addressC;
     private String cityName;
     private String addressD;
     private String stage;
     private String industry;
     private String otherIndustry;
     private String teamStand;
     private Double financingAmount; 
     private Integer financingYear;
     private Byte discussFinancing;
     private Double accountScaleMin;
     private Double accountPositionMax;
     private String investExit;
     private Byte discussExit;
     private String accountPurpose;
     private String projectSituation;
     private String projectAdvantage;
     private String notes;
     private Byte checkStatus;
     private Byte showStatus;
     private String reason;
     private Date checkTime;
     private Date createTime;
     private CmsUser cmsUser;

    public BaseProjectRelease() {
    	initialize();
    }

    public BaseProjectRelease (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}
	

	public BaseProjectRelease(Integer id, String projectName, String companyName, String addressP, String addressC, String addressD,
			String stage, String industry, String otherIndustry, String teamStand, Double financingAmount,
			Byte discussFinancing, Double accountScaleMin, Double accountPositionMax, String investExit, Byte discussExit,
			String accountPurpose, String projectSituation, String projectAdvantage, String notes, Byte checkStatus,
			Byte showStatus, String reason, Date checkTime, Date createTime, CmsUser cmsUser,String provinceName,String cityName,Integer financingYear) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.addressP = addressP;
		this.addressC = addressC;
		this.addressD = addressD;
		this.stage = stage;
		this.industry = industry;
		this.otherIndustry = otherIndustry;
		this.teamStand = teamStand;
		this.financingAmount = financingAmount;
		this.discussFinancing = discussFinancing;
		this.accountScaleMin = accountScaleMin;
		this.accountPositionMax = accountPositionMax;
		this.investExit = investExit;
		this.discussExit = discussExit;
		this.accountPurpose = accountPurpose;
		this.projectSituation = projectSituation;
		this.projectAdvantage = projectAdvantage;
		this.notes = notes;
		this.checkStatus = checkStatus;
		this.showStatus = showStatus;
		this.reason = reason;
		this.checkTime = checkTime;
		this.createTime = createTime;
		this.cmsUser = cmsUser;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.financingYear = financingYear;
		this.projectName = projectName;
	}

	protected void initialize () {}
   
    private int hashCode = Integer.MIN_VALUE;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddressP() {
		return addressP;
	}

	public void setAddressP(String addressP) {
		this.addressP = addressP;
	}

	public String getAddressC() {
		return addressC;
	}

	public void setAddressC(String addressC) {
		this.addressC = addressC;
	}

	public String getAddressD() {
		return addressD;
	}

	public void setAddressD(String addressD) {
		this.addressD = addressD;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getOtherIndustry() {
		return otherIndustry;
	}

	public void setOtherIndustry(String otherIndustry) {
		this.otherIndustry = otherIndustry;
	}

	public String getTeamStand() {
		return teamStand;
	}

	public void setTeamStand(String teamStand) {
		this.teamStand = teamStand;
	}

	public Double getFinancingAmount() {
		return financingAmount;
	}

	public void setFinancingAmount(Double financingAmount) {
		this.financingAmount = financingAmount;
	}

	public Byte getDiscussFinancing() {
		return discussFinancing;
	}

	public void setDiscussFinancing(Byte discussFinancing) {
		this.discussFinancing = discussFinancing;
	}

	public Double getAccountScaleMin() {
		return accountScaleMin;
	}

	public void setAccountScaleMin(Double accountScaleMin) {
		this.accountScaleMin = accountScaleMin;
	}

	public Double getAccountPositionMax() {
		return accountPositionMax;
	}

	public void setAccountPositionMax(Double accountPositionMax) {
		this.accountPositionMax = accountPositionMax;
	}

	public String getInvestExit() {
		return investExit;
	}

	public void setInvestExit(String investExit) {
		this.investExit = investExit;
	}

	public Byte getDiscussExit() {
		return discussExit;
	}

	public void setDiscussExit(Byte discussExit) {
		this.discussExit = discussExit;
	}

	public String getAccountPurpose() {
		return accountPurpose;
	}

	public void setAccountPurpose(String accountPurpose) {
		this.accountPurpose = accountPurpose;
	}

	public String getProjectSituation() {
		return projectSituation;
	}

	public void setProjectSituation(String projectSituation) {
		this.projectSituation = projectSituation;
	}

	public String getProjectAdvantage() {
		return projectAdvantage;
	}

	public void setProjectAdvantage(String projectAdvantage) {
		this.projectAdvantage = projectAdvantage;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Byte getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Byte checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Byte getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Byte showStatus) {
		this.showStatus = showStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public CmsUser getCmsUser() {
		return cmsUser;
	}

	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public Integer getFinancingYear() {
		return financingYear;
	}

	public void setFinancingYear(Integer financingYear) {
		this.financingYear = financingYear;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public boolean equals(Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.core.entity.ProjectRelease)) return false;
		else {
			com.jeecms.core.entity.ProjectRelease releaseProject = (com.jeecms.core.entity.ProjectRelease) obj;
			if (null == this.getId() || null == releaseProject.getId()) return false;
			else return (this.getId().equals(releaseProject.getId()));
		}
	}


	public String toString () {
		return super.toString();
	}


}


