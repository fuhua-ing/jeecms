package com.jeecms.cms;

public enum ChangeEunm
{
	Email("email"), Phone("phone");

	private ChangeEunm(String value)
	{
		this.value = value;
	}

	private String value;

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
