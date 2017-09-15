package com.jeecms.cms.manager.main;


import com.jeecms.cms.entity.main.ActivitiesEnroll;
import com.jeecms.common.page.Pagination;

public interface ActivitiesEnrollMng {

	/**
	 * 保存记录
	 * @param bean
	 * @return
	 */
	ActivitiesEnroll save(ActivitiesEnroll bean,Integer contentId);
	
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	ActivitiesEnroll findById(Integer id);
	
	/**
	 * 根据userphone查询记录
	 * @param contentId
	 * @param userphone
	 * @return
	 */
	ActivitiesEnroll findByUserPhone(Integer contentId,String userphone);
	
	/**
	 * 删除记录
	 * @param ids
	 */
	void deleteByIds(Integer... ids);
	
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
	 * @param bean
	 * @return
	 */
	ActivitiesEnroll updateByUpdater(ActivitiesEnroll bean);
	
}
