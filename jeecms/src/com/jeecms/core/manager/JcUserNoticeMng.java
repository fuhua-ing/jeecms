package com.jeecms.core.manager;


import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcUserNotice;

public interface JcUserNoticeMng {
	/**
	 * 保存用户消息通知
	 * @param user
	 * @param result
	 * @param tag
	 * @param title
	 * @param content
	 * @return
	 */
	JcUserNotice saveUserNotice(CmsUser user,Integer result,Integer tag,String title,String content);
	
	/**
	 * 阅读消息通知
	 * @param id
	 * @return
	 */
	JcUserNotice readUserNotice(Integer... ids);
	
	/**
	 * 获取投资人分页信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getUserNoticePage(Integer userId,Integer pageNo,Integer pageSize);
	
	/**
	 * 删除审核记录
	 * @param ids
	 */
	void deleteUserNotice(Integer... ids);
	
	/**
	 * 接收系统通知
	 * @param userType 	用户类型
	 * @param title	   	标题
	 * @param content	内容
	 */
	void recvSysNotice(String[] userType,String title,String content);
}
