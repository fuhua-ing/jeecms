package com.jeecms.core.entity;


import java.util.Date;

public class JcReleaseTransfer implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CmsUser cmsUser;
	private String projectName;
	private String companyName;
	private String companyAddressProvince;
	private String provinceName;
	private String companyAddressCity;
	private String cityName;
	private String companyAddressDetail;
	private String industry;
	private String stage;
	private String projectDescribe;
	private Double evaluation;
	private Double transferStockRatio;
	private Double holdStockRatio;
	private String remark;
	private Byte checkStatus;
	private Byte showStatus;
	private Date applyTime;
	private String reason;
	private Date checkTime;

	public JcReleaseTransfer() {
	}

	public JcReleaseTransfer(CmsUser cmsUser, String projectName,String companyName,
			String companyAddressProvince, String companyAddressCity,
			String companyAddressDetail, String reason, String remark,
			String projectDescribe, Double evaluation, Double transferStockRatio,
			Double holdStockRatio, String stage,String industry, 
			Byte checkStatus, Byte showStatus, Date applyTime,Date checkTime,
			String provinceName, String cityName) {
		this.cmsUser = cmsUser;
		this.projectName = projectName;
		this.companyName = companyName;
		this.companyAddressProvince = companyAddressProvince;
		this.companyAddressCity = companyAddressCity;
		this.companyAddressDetail = companyAddressDetail;
		this.industry = industry;
		this.stage = stage;
		this.projectDescribe = projectDescribe;
		this.evaluation = evaluation;
		this.transferStockRatio = transferStockRatio;
		this.holdStockRatio = holdStockRatio;
		this.remark = remark;
		this.checkStatus = checkStatus;
		this.showStatus = showStatus;
		this.applyTime = applyTime;
		this.reason = reason;
		this.checkTime = checkTime;
		this.provinceName = provinceName;
		this.cityName = cityName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CmsUser getCmsUser() {
		return cmsUser;
	}

	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getCompanyAddressProvince() {
		return this.companyAddressProvince;
	}

	public void setCompanyAddressProvince(String companyAddressProvince) {
		this.companyAddressProvince = companyAddressProvince;
	}

	public String getCompanyAddressCity() {
		return this.companyAddressCity;
	}

	public void setCompanyAddressCity(String companyAddressCity) {
		this.companyAddressCity = companyAddressCity;
	}

	public String getCompanyAddressDetail() {
		return this.companyAddressDetail;
	}

	public void setCompanyAddressDetail(String companyAddressDetail) {
		this.companyAddressDetail = companyAddressDetail;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getProjectDescribe() {
		return this.projectDescribe;
	}

	public void setProjectDescribe(String projectDescribe) {
		this.projectDescribe = projectDescribe;
	}

	public Double getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Double evaluation) {
		this.evaluation = evaluation;
	}

	public Double getTransferStockRatio() {
		return this.transferStockRatio;
	}

	public void setTransferStockRatio(Double transferStockRatio) {
		this.transferStockRatio = transferStockRatio;
	}

	public Double getHoldStockRatio() {
		return this.holdStockRatio;
	}

	public void setHoldStockRatio(Double holdStockRatio) {
		this.holdStockRatio = holdStockRatio;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Byte checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Byte getShowStatus() {
		return this.showStatus;
	}

	public void setShowStatus(Byte showStatus) {
		this.showStatus = showStatus;
	}

	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
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

}
