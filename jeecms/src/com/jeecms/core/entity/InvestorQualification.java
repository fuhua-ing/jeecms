package com.jeecms.core.entity;

import java.util.Date;



/**
 * 投资人验证信息
 */
public class InvestorQualification implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CmsUser cmsUser;
	private Byte identity;
	private String realName;
	private String companyAddressProvince;
	private String provinceName;
	private String companyAddressCity;
	private String cityName;
	private String companyAddressDetail;
	private String idcardNumber;
	private String wechatNumber;
	private String investStage;
	private String intentionIndustry;
	private Double investMin;
	private Double investMax;
	private Double holdStockRatioMin;
	private Double holdStockRatioMax;
	private String successCase;
	private String comment;
	private Byte checkStatus;
	private Date applyTime;
	private String reason;
	private Date checkTime;
	private String userImg;
	private String companyPost;

	public InvestorQualification() {
	}

	public InvestorQualification(CmsUser cmsUser, Byte identity, String realName,
			String companyAddressProvince, String companyAddressCity, String companyAddressDetail,
			String idcardNumber, String wechatNumber, String investStage,
			String intentionIndustry, Double investMin, Double investMax,
			Double holdStockRatioMin, Double holdStockRatioMax, String successCase,
			String comment, Byte checkStatus, Date applyTime, String reason,Date checkTime,
			String provinceName, String cityName,String userImg,String companyPost) {
		this.cmsUser = cmsUser;
		this.identity = identity;
		this.realName = realName;
		this.companyAddressProvince = companyAddressProvince;
		this.companyAddressCity = companyAddressCity;
		this.companyAddressDetail = companyAddressDetail;
		this.idcardNumber = idcardNumber;
		this.wechatNumber = wechatNumber;
		this.investStage = investStage;
		this.intentionIndustry = intentionIndustry;
		this.investMin = investMin;
		this.investMax = investMax;
		this.holdStockRatioMin = holdStockRatioMin;
		this.holdStockRatioMax = holdStockRatioMax;
		this.successCase = successCase;
		this.comment = comment;
		this.checkStatus = checkStatus;
		this.applyTime = applyTime;
		this.reason = reason;
		this.checkTime = checkTime;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.userImg = userImg;
		this.companyPost = companyPost;
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

	public Byte getIdentity() {
		return this.identity;
	}

	public void setIdentity(Byte identity) {
		this.identity = identity;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCompanyAddressProvince() {
		return companyAddressProvince;
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

	public void setCompanyAddressProvince(String companyAddressProvince) {
		this.companyAddressProvince = companyAddressProvince;
	}

	public String getCompanyAddressCity() {
		return companyAddressCity;
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

	public String getIdcardNumber() {
		return this.idcardNumber;
	}

	public void setIdcardNumber(String idcardNumber) {
		this.idcardNumber = idcardNumber;
	}

	public String getWechatNumber() {
		return this.wechatNumber;
	}

	public void setWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
	}

	public String getInvestStage() {
		return this.investStage;
	}

	public void setInvestStage(String investStage) {
		this.investStage = investStage;
	}

	public String getIntentionIndustry() {
		return this.intentionIndustry;
	}

	public void setIntentionIndustry(String intentionIndustry) {
		this.intentionIndustry = intentionIndustry;
	}

	public Double getInvestMin() {
		return this.investMin;
	}

	public void setInvestMin(Double investMin) {
		this.investMin = investMin;
	}

	public Double getInvestMax() {
		return this.investMax;
	}

	public void setInvestMax(Double investMax) {
		this.investMax = investMax;
	}

	public Double getHoldStockRatioMin() {
		return this.holdStockRatioMin;
	}

	public void setHoldStockRatioMin(Double holdStockRatioMin) {
		this.holdStockRatioMin = holdStockRatioMin;
	}

	public Double getHoldStockRatioMax() {
		return this.holdStockRatioMax;
	}

	public void setHoldStockRatioMax(Double holdStockRatioMax) {
		this.holdStockRatioMax = holdStockRatioMax;
	}

	public String getSuccessCase() {
		return successCase;
	}

	public void setSuccessCase(String successCase) {
		this.successCase = successCase;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Byte getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Byte checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getApplyTime() {
		return applyTime;
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

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getCompanyPost() {
		return companyPost;
	}

	public void setCompanyPost(String companyPost) {
		this.companyPost = companyPost;
	}
	
}
