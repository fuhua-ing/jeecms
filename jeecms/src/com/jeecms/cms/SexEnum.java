package com.jeecms.cms;

public enum SexEnum
{
	Male(1), Female(0);

	private SexEnum(Integer value)
	{
		this.value = value;
	}

	private Integer value;

	public Integer getValue()
	{
		return value;
	}

	public void setValue(Integer value)
	{
		this.value = value;
	}
}
