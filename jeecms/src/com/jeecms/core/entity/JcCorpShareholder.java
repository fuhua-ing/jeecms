package com.jeecms.core.entity;

import com.jeecms.core.entity.base.AbstractJcCorpShareholder;

/**
 * JcCorpShareholder entity. @author MyEclipse Persistence Tools
 */
public class JcCorpShareholder extends AbstractJcCorpShareholder implements java.io.Serializable
{

	// Constructors

	/** default constructor */
	public JcCorpShareholder()
	{
	}

	/** minimal constructor */
	public JcCorpShareholder(Integer shareholderId, Integer corpId)
	{
		super(shareholderId, corpId);
	}

	/** full constructor */
	public JcCorpShareholder(Integer shareholderId, String shareholderName, Double shareholderDonate,
			String shareholderPercent, String shareholderPaytype, Integer corpId)
	{
		super(shareholderId, shareholderName, shareholderDonate, shareholderPercent, shareholderPaytype, corpId);
	}

}
