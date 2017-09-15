package com.jeecms.core.dao;

import java.util.Date;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.JcReleaseTransfer;

public interface JcReleaseTransferDao {
	/**
	 * 保存记录
	 * @param bean
	 * @return
	 */
	JcReleaseTransfer save(JcReleaseTransfer bean);
	
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	JcReleaseTransfer findById(Integer id);
	
	/**
	 * 根据userName查询记录
	 * @param id
	 * @return
	 */
	JcReleaseTransfer findByUserName(String userName);
	
	/**
	 * 删除记录
	 * @param id
	 */
	JcReleaseTransfer deleteById(Integer id);
	
	/**
	 * 修改状态
	 * @param id
	 * @param checkStatus  审核状态
	 * @param showStatus   显示状态
	 * @param reason 原因
	 * @return
	 */
	JcReleaseTransfer updateStatus(int id,Integer checkStatus,Integer showStatus, String reason);
	
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
	 * 首页互动分页信息
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
	
	/**
	 * 更新记录
	 * @param updater
	 * @return
	 */
	JcReleaseTransfer updateByUpdater(Updater<JcReleaseTransfer> updater);
}