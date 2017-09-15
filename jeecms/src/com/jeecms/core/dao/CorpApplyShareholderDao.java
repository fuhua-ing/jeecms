package com.jeecms.core.dao;

import java.util.List;

import com.jeecms.core.entity.JcCorpShareholder;

public interface CorpApplyShareholderDao
{

	void insertBatch(Integer id, List<JcCorpShareholder> shareholderInfos);

	List<JcCorpShareholder> findShareById(Integer ids);

}
