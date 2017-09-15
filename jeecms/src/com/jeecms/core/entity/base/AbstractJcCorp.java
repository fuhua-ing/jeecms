package com.jeecms.core.entity.base;

import java.sql.Timestamp;

/**
 * AbstractJcCorp entity provides the base persistence definition of the JcCorp
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractJcCorp implements java.io.Serializable
{

	// Fields    

	private Integer corpId;
	private String corpName;
	private String corpLegalPerson;
	private String corpLegalPersonTel;
	private Double corpRegisteredFunds;
	private Double corpTrueFunds;
	private String corpManageScope;
	private String corpFixedTel;
	private String corpFax;
	private String corpHomepage;
	private String corpWechat;
	private String corpMainBusiness;
	private String corpSituationBusiness;
	private String corpPlanBusiness;
	private String corpChairman;
	private String corpChairmanTel;
	private String corpGeneralManager;
	private String corpGeneralManagerTel;
	private String corpOrganizationStructure;
	private String corpManagementStructure;
	private String corpLicense;
	private Timestamp corpApplyTime;
	private Integer corpAudit;
	private String corpApplyUsername;
	private Boolean corpIsCooperation;
	private String corpAuditPerson;
	private Timestamp corpAuditTime;
	private String corpAuditReason;

	// Constructors

	/** default constructor */
	public AbstractJcCorp()
	{
	}

	/** minimal constructor */
	public AbstractJcCorp(Timestamp corpApplyTime, Integer corpAudit, String corpApplyUsername,
			Boolean corpIsCooperation)
	{
		this.corpApplyTime = corpApplyTime;
		this.corpAudit = corpAudit;
		this.corpApplyUsername = corpApplyUsername;
		this.corpIsCooperation = corpIsCooperation;
	}

	/** full constructor */
	public AbstractJcCorp(String corpName, String corpLegalPerson, String corpLegalPersonTel,
			Double corpRegisteredFunds, Double corpTrueFunds, String corpManageScope, String corpFixedTel,
			String corpFax, String corpHomepage, String corpWechat, String corpMainBusiness,
			String corpSituationBusiness, String corpPlanBusiness, String corpChairman, String corpChairmanTel,
			String corpGeneralManager, String corpGeneralManagerTel, String corpOrganizationStructure,
			String corpManagementStructure, String corpLicense, Timestamp corpApplyTime, Integer corpAudit,
			String corpApplyUsername, Boolean corpIsCooperation, String corpAuditPerson, Timestamp corpAuditTime,
			String corpAuditReason)
	{
		this.corpName = corpName;
		this.corpLegalPerson = corpLegalPerson;
		this.corpLegalPersonTel = corpLegalPersonTel;
		this.corpRegisteredFunds = corpRegisteredFunds;
		this.corpTrueFunds = corpTrueFunds;
		this.corpManageScope = corpManageScope;
		this.corpFixedTel = corpFixedTel;
		this.corpFax = corpFax;
		this.corpHomepage = corpHomepage;
		this.corpWechat = corpWechat;
		this.corpMainBusiness = corpMainBusiness;
		this.corpSituationBusiness = corpSituationBusiness;
		this.corpPlanBusiness = corpPlanBusiness;
		this.corpChairman = corpChairman;
		this.corpChairmanTel = corpChairmanTel;
		this.corpGeneralManager = corpGeneralManager;
		this.corpGeneralManagerTel = corpGeneralManagerTel;
		this.corpOrganizationStructure = corpOrganizationStructure;
		this.corpManagementStructure = corpManagementStructure;
		this.corpLicense = corpLicense;
		this.corpApplyTime = corpApplyTime;
		this.corpAudit = corpAudit;
		this.corpApplyUsername = corpApplyUsername;
		this.corpIsCooperation = corpIsCooperation;
		this.corpAuditPerson = corpAuditPerson;
		this.corpAuditTime = corpAuditTime;
		this.corpAuditReason = corpAuditReason;
	}

	// Property accessors

	public Integer getCorpId()
	{
		return this.corpId;
	}

	public void setCorpId(Integer corpId)
	{
		this.corpId = corpId;
	}

	public String getCorpName()
	{
		return this.corpName;
	}

	public void setCorpName(String corpName)
	{
		this.corpName = corpName;
	}

	public String getCorpLegalPerson()
	{
		return this.corpLegalPerson;
	}

	public void setCorpLegalPerson(String corpLegalPerson)
	{
		this.corpLegalPerson = corpLegalPerson;
	}

	public String getCorpLegalPersonTel()
	{
		return this.corpLegalPersonTel;
	}

	public void setCorpLegalPersonTel(String corpLegalPersonTel)
	{
		this.corpLegalPersonTel = corpLegalPersonTel;
	}

	public Double getCorpRegisteredFunds()
	{
		return this.corpRegisteredFunds;
	}

	public void setCorpRegisteredFunds(Double corpRegisteredFunds)
	{
		this.corpRegisteredFunds = corpRegisteredFunds;
	}

	public Double getCorpTrueFunds()
	{
		return this.corpTrueFunds;
	}

	public void setCorpTrueFunds(Double corpTrueFunds)
	{
		this.corpTrueFunds = corpTrueFunds;
	}

	public String getCorpManageScope()
	{
		return this.corpManageScope;
	}

	public void setCorpManageScope(String corpManageScope)
	{
		this.corpManageScope = corpManageScope;
	}

	public String getCorpFixedTel()
	{
		return this.corpFixedTel;
	}

	public void setCorpFixedTel(String corpFixedTel)
	{
		this.corpFixedTel = corpFixedTel;
	}

	public String getCorpFax()
	{
		return this.corpFax;
	}

	public void setCorpFax(String corpFax)
	{
		this.corpFax = corpFax;
	}

	public String getCorpHomepage()
	{
		return this.corpHomepage;
	}

	public void setCorpHomepage(String corpHomepage)
	{
		this.corpHomepage = corpHomepage;
	}

	public String getCorpWechat()
	{
		return this.corpWechat;
	}

	public void setCorpWechat(String corpWechat)
	{
		this.corpWechat = corpWechat;
	}

	public String getCorpMainBusiness()
	{
		return this.corpMainBusiness;
	}

	public void setCorpMainBusiness(String corpMainBusiness)
	{
		this.corpMainBusiness = corpMainBusiness;
	}

	public String getCorpSituationBusiness()
	{
		return this.corpSituationBusiness;
	}

	public void setCorpSituationBusiness(String corpSituationBusiness)
	{
		this.corpSituationBusiness = corpSituationBusiness;
	}

	public String getCorpPlanBusiness()
	{
		return this.corpPlanBusiness;
	}

	public void setCorpPlanBusiness(String corpPlanBusiness)
	{
		this.corpPlanBusiness = corpPlanBusiness;
	}

	public String getCorpChairman()
	{
		return this.corpChairman;
	}

	public void setCorpChairman(String corpChairman)
	{
		this.corpChairman = corpChairman;
	}

	public String getCorpChairmanTel()
	{
		return this.corpChairmanTel;
	}

	public void setCorpChairmanTel(String corpChairmanTel)
	{
		this.corpChairmanTel = corpChairmanTel;
	}

	public String getCorpGeneralManager()
	{
		return this.corpGeneralManager;
	}

	public void setCorpGeneralManager(String corpGeneralManager)
	{
		this.corpGeneralManager = corpGeneralManager;
	}

	public String getCorpGeneralManagerTel()
	{
		return this.corpGeneralManagerTel;
	}

	public void setCorpGeneralManagerTel(String corpGeneralManagerTel)
	{
		this.corpGeneralManagerTel = corpGeneralManagerTel;
	}

	public String getCorpOrganizationStructure()
	{
		return this.corpOrganizationStructure;
	}

	public void setCorpOrganizationStructure(String corpOrganizationStructure)
	{
		this.corpOrganizationStructure = corpOrganizationStructure;
	}

	public String getCorpManagementStructure()
	{
		return this.corpManagementStructure;
	}

	public void setCorpManagementStructure(String corpManagementStructure)
	{
		this.corpManagementStructure = corpManagementStructure;
	}

	public String getCorpLicense()
	{
		return this.corpLicense;
	}

	public void setCorpLicense(String corpLicense)
	{
		this.corpLicense = corpLicense;
	}

	public Timestamp getCorpApplyTime()
	{
		return this.corpApplyTime;
	}

	public void setCorpApplyTime(Timestamp corpApplyTime)
	{
		this.corpApplyTime = corpApplyTime;
	}

	public Integer getCorpAudit()
	{
		return this.corpAudit;
	}

	public void setCorpAudit(Integer corpAudit)
	{
		this.corpAudit = corpAudit;
	}

	public String getCorpApplyUsername()
	{
		return this.corpApplyUsername;
	}

	public void setCorpApplyUsername(String corpApplyUsername)
	{
		this.corpApplyUsername = corpApplyUsername;
	}

	public Boolean getCorpIsCooperation()
	{
		return this.corpIsCooperation;
	}

	public void setCorpIsCooperation(Boolean corpIsCooperation)
	{
		this.corpIsCooperation = corpIsCooperation;
	}

	public String getCorpAuditPerson()
	{
		return this.corpAuditPerson;
	}

	public void setCorpAuditPerson(String corpAuditPerson)
	{
		this.corpAuditPerson = corpAuditPerson;
	}

	public Timestamp getCorpAuditTime()
	{
		return this.corpAuditTime;
	}

	public void setCorpAuditTime(Timestamp corpAuditTime)
	{
		this.corpAuditTime = corpAuditTime;
	}

	public String getCorpAuditReason()
	{
		return this.corpAuditReason;
	}

	public void setCorpAuditReason(String corpAuditReason)
	{
		this.corpAuditReason = corpAuditReason;
	}

}