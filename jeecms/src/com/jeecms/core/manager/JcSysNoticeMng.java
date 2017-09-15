package com.jeecms.core.manager;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcSysNotice;


public interface JcSysNoticeMng {
	
	/**
	 * 保存记录
	 * @param bean
	 * @return
	 */
	JcSysNotice save(JcSysNotice bean,String userName);
	
	/**
	 * 保存记录
	 * @param userType  用户类型
	 * @param title  标题
	 * @param content 内容
	 * @param userName 用户名
	 * @return
	 */
	JcSysNotice save(Integer id,String[] userType,String title,String content,CmsUser user);
	
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	JcSysNotice findById(Integer id);
	
	/**
	 * 删除记录
	 * @param ids
	 */
	void deleteByIds(Integer... ids);
	
	/**
	 * 分页信息
	 * @param title 标题
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(String title,Integer pageNo,Integer pageSize);
	
	/**
	 * 更新记录
	 * @param bean
	 * @return
	 */
	JcSysNotice updateByUpdater(JcSysNotice bean);
}
