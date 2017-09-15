package com.jeecms.core.manager;

import java.util.Date;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.ProjectRelease;

public interface ProjectReleaseMng {

	/**
	 * 保存记录
	 * @param bean
	 * @return
	 */
	ProjectRelease save(ProjectRelease bean,String userName);
	
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	ProjectRelease findById(Integer id);
	
	/**
	 * 根据userName查询记录
	 * @param userName
	 * @return
	 */
	ProjectRelease findByUserName(String userName);
	
	/**
	 * 删除记录
	 * @param ids
	 */
	void deleteByIds(Integer... ids);
	
	/**
	 * 修改状态
	 * @param id
	 * @param checkStatus  审核状态
	 * @param reason   原因
	 * @return
	 */
	ProjectRelease updateCheckStatus(int id,Integer checkStatus,String reason);
	
	/**
	 * 修改状态
	 * @param id
	 * @param showStatus  显示/不显示
	 * @return
	 */
	ProjectRelease updateShowStatus(int id,Integer showStatus);
	
	/**
	 * 查询分页记录
	 * @param userName  用户名
	 * @param stage		阶段
	 * @param industry	领域
	 * @param checkStatus	审核状态
	 * @param startTime 开始时间
	 * @param endTime	结束时间
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(String userName,Integer stage, Integer industry,
			Integer checkStatus, Date startTime,Date endTime,Integer pageNo,Integer pageSize);
	
	/**
	 * 更新记录
	 * @param bean
	 * @return
	 */
	ProjectRelease updateByUpdater(ProjectRelease bean);
	
	/**
	 * 互动平台项目信息
	 * @param money
	 * @param radio
	 * @param position
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getInteractionPage(String money,String radio,String position,
			String name,Integer pageNo,Integer pageSize);
}
