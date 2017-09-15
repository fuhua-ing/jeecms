package com.jeecms.core.dao;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.JcUserNotice;

public interface JcUserNoticeDao {
	/**
	 * 保存用户消息通知
	 * @param bean
	 * @return
	 */
	JcUserNotice save(JcUserNotice bean);
	
	/**
	 * 根据id记录
	 * @param id
	 * @return
	 */
	JcUserNotice findById(Integer id);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	JcUserNotice deleteById(Integer id);
	
	/**
	 * 修改已读状态
	 * @param id
	 * @param status  状态
	 * @return
	 */
	JcUserNotice updateJcUserNotice(int id,int status);
	
	/**
	 * 获取消息通知分页信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(Integer userId,Integer pageNo,Integer pageSize);
}