package com.jeecms.cms;

/**
 * 
 * <发送验证码的用图>
 * <功能详细描述>
 * 
 * @author gzhiwei
 * @version [V100R001C00, 2017-9-5]
 */
public enum MessageTypeEnum
{
	Register("register"),

	ForgetPassword("reset_password"),

	Validator("validator"),

	Change_Username("change_username"),

	Change_Email("change_email");

	private String value;

	private MessageTypeEnum(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

}
