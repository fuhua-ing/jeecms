package com.jeecms.core.entity;

import java.sql.Timestamp;

import com.jeecms.core.entity.base.AbstractJcCorp;

/**
 * JcCorp entity. @author MyEclipse Persistence Tools
 */
public class JcCorp extends AbstractJcCorp implements java.io.Serializable
{

	// Constructors

	/** default constructor */
	public JcCorp()
	{
	}

	/** minimal constructor */
	public JcCorp(Timestamp corpApplyTime, Integer corpAudit, String corpApplyUsername, Boolean corpIsCooperation)
	{
		super(corpApplyTime, corpAudit, corpApplyUsername, corpIsCooperation);
	}

	/** full constructor */
	public JcCorp(String corpName, String corpLegalPerson, String corpLegalPersonTel, Double corpRegisteredFunds,
			Double corpTrueFunds, String corpManageScope, String corpFixedTel, String corpFax, String corpHomepage,
			String corpWechat, String corpMainBusiness, String corpSituationBusiness, String corpPlanBusiness,
			String corpChairman, String corpChairmanTel, String corpGeneralManager, String corpGeneralManagerTel,
			String corpOrganizationStructure, String corpManagementStructure, String corpLicense,
			Timestamp corpApplyTime, Integer corpAudit, String corpApplyUsername, Boolean corpIsCooperation,
			String corpAuditPerson, Timestamp corpAuditTime, String corpAuditReason)
	{
		super(corpName, corpLegalPerson, corpLegalPersonTel, corpRegisteredFunds, corpTrueFunds, corpManageScope,
				corpFixedTel, corpFax, corpHomepage, corpWechat, corpMainBusiness, corpSituationBusiness,
				corpPlanBusiness, corpChairman, corpChairmanTel, corpGeneralManager, corpGeneralManagerTel,
				corpOrganizationStructure, corpManagementStructure, corpLicense, corpApplyTime, corpAudit,
				corpApplyUsername, corpIsCooperation, corpAuditPerson, corpAuditTime, corpAuditReason);
	}

}
