package com.jeecms.cms.manager.assist;

import java.util.Date;
import java.util.List;

import com.jeecms.cms.IdentityEnum;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.JcCorp;
import com.jeecms.core.entity.JcCorpShareholder;

public interface ManagerMng
{

	Pagination getPage(String applyUsername, String corpName, Integer status, Date timeBegin, Date timeEnd,
			Integer cpn, Integer pageSize);

	JcCorp findById(Integer ids);

	List<JcCorpShareholder> findShareById(Integer ids);

	void passCheck(String username, Integer id, Boolean pass);

	Pagination getPageOut(String applyUsername, String corpName, Integer audit, Date timeBegin, Date timeEnd, int cpn,
			int pageSize);

	void addIdentityById(Integer userId, IdentityEnum identityEnum);

	void addIdentityByUsername(String username, IdentityEnum identityEnum);

}
