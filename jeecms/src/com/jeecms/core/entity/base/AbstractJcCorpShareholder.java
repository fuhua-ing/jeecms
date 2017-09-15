package com.jeecms.core.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * AbstractJcCorpShareholder entity provides the base persistence definition of
 * the JcCorpShareholder entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractJcCorpShareholder implements java.io.Serializable
{

	// Fields    

	private Integer shareholderId;
	private String shareholderName;
	private Double shareholderDonate;
	private String shareholderPercent;
	private String shareholderPaytype;
	private Integer corpId;

	// Constructors

	/** default constructor */
	public AbstractJcCorpShareholder()
	{
	}

	/** minimal constructor */
	public AbstractJcCorpShareholder(Integer shareholderId, Integer corpId)
	{
		this.shareholderId = shareholderId;
		this.corpId = corpId;
	}

	/** full constructor */
	public AbstractJcCorpShareholder(Integer shareholderId, String shareholderName, Double shareholderDonate,
			String shareholderPercent, String shareholderPaytype, Integer corpId)
	{
		this.shareholderId = shareholderId;
		this.shareholderName = shareholderName;
		this.shareholderDonate = shareholderDonate;
		this.shareholderPercent = shareholderPercent;
		this.shareholderPaytype = shareholderPaytype;
		this.corpId = corpId;
	}

	// Property accessors

	@Id
	@Column(name = "shareholder_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getShareholderId()
	{
		return this.shareholderId;
	}

	public void setShareholderId(Integer shareholderId)
	{
		this.shareholderId = shareholderId;
	}

	public String getShareholderName()
	{
		return this.shareholderName;
	}

	public void setShareholderName(String shareholderName)
	{
		this.shareholderName = shareholderName;
	}

	public Double getShareholderDonate()
	{
		return this.shareholderDonate;
	}

	public void setShareholderDonate(Double shareholderDonate)
	{
		this.shareholderDonate = shareholderDonate;
	}

	public String getShareholderPercent()
	{
		return this.shareholderPercent;
	}

	public void setShareholderPercent(String shareholderPercent)
	{
		this.shareholderPercent = shareholderPercent;
	}

	public String getShareholderPaytype()
	{
		return this.shareholderPaytype;
	}

	public void setShareholderPaytype(String shareholderPaytype)
	{
		this.shareholderPaytype = shareholderPaytype;
	}

	public Integer getCorpId()
	{
		return this.corpId;
	}

	public void setCorpId(Integer corpId)
	{
		this.corpId = corpId;
	}

}