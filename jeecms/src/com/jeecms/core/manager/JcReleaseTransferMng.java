package com.jeecms.core.manager;

import java.util.Date;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.JcReleaseTransfer;

public interface JcReleaseTransferMng {
	/**
	 * 保存记录
	 * @param bean
	 * @return
	 */
	JcReleaseTransfer save(JcReleaseTransfer bean,String userName);
	
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	JcReleaseTransfer findById(Integer id);
	
	/**
	 * 根据userName查询记录
	 * @param userName
	 * @return
	 */
	JcReleaseTransfer findByUserName(String userName);
	
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
	JcReleaseTransfer updateCheckStatus(int id,Integer checkStatus,String reason,String title);
	
	/**
	 * 修改状态
	 * @param id
	 * @param showStatus  显示/不显示
	 * @return
	 */
	JcReleaseTransfer updateShowStatus(int id,Integer showStatus);
	
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
	Pagination getInteractionPage(String money,String term,String position,
			String name,Integer pageNo,Integer pageSize);
	
	/**
	 * 更新记录
	 * @param bean
	 * @return
	 */
	JcReleaseTransfer updateByUpdater(JcReleaseTransfer bean);
}
