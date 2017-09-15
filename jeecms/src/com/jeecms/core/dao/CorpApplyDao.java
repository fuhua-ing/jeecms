package com.jeecms.core.dao;

import java.util.Date;
import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.JcCorp;
import com.jeecms.core.entity.JcCorpShareholder;

public interface CorpApplyDao
{

	void saveEntity(JcCorp corpInfo, List<JcCorpShareholder> shareholderInfos);

	void saveCooperation(JcCorp corpInfo);

	Pagination getPage(String applyUsername, String corpName, Integer applyStatus, Date applyTimeBegin,
			Date applyTimeEnd, Integer pageNo, Integer pageSize);

	JcCorp findById(Integer ids);

	Pagination getPageOut(String applyUsername, String corpName, Integer applyStatus, Date applyTimeBegin,
			Date applyTimeEnd, int pageNo, int pageSize);

	JcCorp queryCorpInnerByUsername(String username);

	JcCorp queryCorpOutByUsername(String username);

	JcCorp updateByUpdater(Updater<JcCorp> updater);

	List<JcCorp> findByUsername(String oldUsername);

}
