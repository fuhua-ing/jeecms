package com.jeecms.core.dao;

import java.sql.Timestamp;

import com.jeecms.cms.MessageTypeEnum;
import com.jeecms.core.entity.JcMessageRecord;

public interface SmsManagerDao
{

	void saveEntity(JcMessageRecord jcMessageRecord);

	JcMessageRecord getBeanByUsernameAndType(String username, String value);

	boolean validatorrCode(String username, String messageCode, Timestamp currTime, MessageTypeEnum changeUsername);

}
