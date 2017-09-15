package com.jeecms.cms.action.member;

import java.io.Serializable;
import java.util.List;

import com.jeecms.core.entity.JcCorpShareholder;

public class JcCorpShareholderDto implements Serializable
{

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 99936482721049204L;

	private List<JcCorpShareholder> jcCorpShareholders;

	public List<JcCorpShareholder> getJcCorpShareholders()
	{
		return jcCorpShareholders;
	}

	public void setJcCorpShareholders(List<JcCorpShareholder> jcCorpShareholders)
	{
		this.jcCorpShareholders = jcCorpShareholders;
	}

}
