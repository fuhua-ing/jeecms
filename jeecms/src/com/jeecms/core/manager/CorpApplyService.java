package com.jeecms.core.manager;

import java.sql.Timestamp;
import java.util.List;

import com.jeecms.core.entity.JcCorp;
import com.jeecms.core.entity.JcCorpShareholder;
import com.jeecms.core.entity.JcEvent;

public interface CorpApplyService
{

	void saveInfo(String username, JcCorp corpInfo, List<JcCorpShareholder> shareholderInfos);

	void saveCooperation(String username, JcCorp corpInfo);

	JcCorp queryCorpInnerByUsername(String username);

	JcCorp queryCorpOutByUsername(String username);

	JcEvent resetInfo(String reset, Timestamp requestTime);

	boolean resetNewEmail(String userId, String eventUuid, String email);

}
