package com.jeecms.cms;

public enum IdentityEnum
{
	//普通会员
	Common_Member(0),

	//投资人
	Investor(1),

	//入驻企业
	Settled_Enterprise(2),

	//外部合作机构
	External_Cooperation_Agencies(3),

	//投资人和入驻企业
	Investor__Settled_Enterprise(4),

	//投资人和外部合作机构
	Investor__External_Cooperation_Agencies(5),

	//入驻企业和外部合作机构
	Settled_Enterprise__External_Cooperation_Agencies(6),

	//投资人、入驻企业、外部合作机构
	Investor__External_Cooperation_Agencies__External_Cooperation_Agencies(7);

	private Integer value;

	private IdentityEnum(Integer value)
	{
		this.value = value;
	}

	public Integer getValue()
	{
		return value;
	}

	public void setValue(Integer value)
	{
		this.value = value;
	}

}
