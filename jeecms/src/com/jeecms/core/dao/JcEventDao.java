package com.jeecms.core.dao;

import com.jeecms.core.entity.JcEvent;

public interface JcEventDao
{

	void saveEntity(JcEvent jcEvent);

	JcEvent queryByUuid(String reset);

	boolean exsitByUuidAndUserId(String userId, String eventUuid);

	JcEvent getByIdAndType(Integer id, String changeType);

}
