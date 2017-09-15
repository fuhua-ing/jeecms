package com.jeecms.core.manager;

import com.jeecms.cms.MessageTypeEnum;

public interface SmsManagerPlatform
{

	void sendMessage(String username, MessageTypeEnum messageTypeEnum);

	boolean validatorExsitor(String username, String messageCode, MessageTypeEnum changeUsername);

	void changeUsername(String userId, String username);

	void updateEmailBymessageValidator(String username, String email);

	void changeUsernameByOldUsername(String oldUsername, String username);

}
