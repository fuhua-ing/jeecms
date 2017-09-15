package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.ActivitiesEnroll;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;

public interface ActivitiesEnrollDao {

	/**
	 * 保存记录
	 * @param bean
	 * @return
	 */
	ActivitiesEnroll save(ActivitiesEnroll bean);
	
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	ActivitiesEnroll findById(Integer id);
	
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	ActivitiesEnroll findByUserPhone(Integer contentId,String userphone);
	
	/**
	 * 删除记录
	 * @param id
	 */
	ActivitiesEnroll deleteById(Integer id);
	
	/**
	 * 查询分页记录
	 * @param title  活动名称
	 * @param enrollStatus	报名状态
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(String title,Integer enrollStatus,Integer pageNo,Integer pageSize);
	
	/**
	 * 查询分页记录
	 * @param contentId  活动Id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPersonPage(Integer contentId,Integer pageNo,Integer pageSize);
	
	/**
	 * 更新记录
	 * @param updater
	 * @return
	 */
	ActivitiesEnroll updateByUpdater(Updater<ActivitiesEnroll> updater);

}