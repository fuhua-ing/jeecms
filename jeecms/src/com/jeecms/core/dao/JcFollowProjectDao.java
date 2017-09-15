package com.jeecms.core.dao;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.JcFollowProject;

public interface JcFollowProjectDao {
	/**
	 * 保存
	 * @param bean
	 * @return
	 */
	JcFollowProject save(JcFollowProject bean);
	
	/**
	 * 根据id记录
	 * @param id
	 * @return
	 */
	JcFollowProject findById(Integer id);
	
	/**
	 * 根据id查询
	 * @param userId  用户id 
	 * @param projectId 项目id
	 * @param tag 标识
	 * @return
	 */
	JcFollowProject findById(Integer userId,Integer projectId,Integer tag);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	JcFollowProject deleteById(Integer id);
	
	/**
	 * 删除
	 * @param jcFollowProject
	 * @return
	 */
	JcFollowProject delete(JcFollowProject jcFollowProject);
	
	/**
	 * 删除
	 * @param projectId
	 * @param tag
	 * @return
	 */
	void deleteByProjectId(Integer projectId, Integer tag);
	
	/**
	 * 获取系统消息分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(Integer pageNo,Integer pageSize,Integer uid);
	
}