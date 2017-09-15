package com.jeecms.cms;

public enum EducationEnum
{
	Junior(1), Senior(2), College(3), Master(4), Doctor(5), Other(6);

	private EducationEnum(Integer value)
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
