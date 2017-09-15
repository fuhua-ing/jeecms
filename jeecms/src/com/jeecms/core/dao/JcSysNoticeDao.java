package com.jeecms.core.dao;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.JcSysNotice;

public interface JcSysNoticeDao {
	/**
	 * 保存系统通知
	 * @param bean
	 * @return
	 */
	JcSysNotice save(JcSysNotice bean);
	
	/**
	 * 根据id记录
	 * @param id
	 * @return
	 */
	JcSysNotice findById(Integer id);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	JcSysNotice deleteById(Integer id);
	
	/**
	 * 获取系统消息分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(Integer pageNo,Integer pageSize,String title);
	
	/**
	 * 更新实体
	 * @param updater
	 * @return
	 */
	JcSysNotice updateByUpdater(Updater<JcSysNotice> updater);
}