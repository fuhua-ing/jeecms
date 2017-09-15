package com.jeecms.core.dao;

import java.util.Date;
import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.InvestorQualification;

public interface InvestorQualificationDao {
	/**
	 * 保存投资人认证信息
	 * @param bean
	 * @return
	 */
	InvestorQualification save(InvestorQualification bean);
	
	/**
	 * 根据id查询投资人认证详细信息
	 * @param id
	 * @return
	 */
	InvestorQualification findById(Integer id);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	InvestorQualification deleteById(Integer id);
	
	/**
	 * 查询投资人认证详细信息
	 * @param userName 用户名
	 * @return
	 */
	InvestorQualification findByUserName(String userName);

	/**
	 * 更新投资人认证信息
	 * @param updater
	 * @return
	 */
	InvestorQualification updateByUpdater(Updater<InvestorQualification> updater);
	
	/**
	 * 审核投资人认证信息
	 * @param id
	 * @param status  状态
	 * @param reason  原因
	 * @return
	 */
	InvestorQualification checkInvestorQualification(int id,int status,String reason);
	
	/**
	 * 获取投资人分页信息
	 * @param userName  用户名
	 * @param realName  真实姓名
	 * @param startTime 开始时间
	 * @param endTime	结束时间
	 * @param checkStatus	审核状态
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(String userName,String realName,Date startTime,Date endTime,
			Integer checkStatus,Integer pageNo,Integer pageSize);
	
	/**
	 * 获取活动平台分页数据
	 * @param name
	 * @param industry
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getInteractionPage(String name,String industry, Byte checkStatus,Integer pageNo,Integer pageSize);
	
	/**
	 * 获取已经审核通过的记录
	 * @return
	 */
	List<InvestorQualification> getHasChecked();
}