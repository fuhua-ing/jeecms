package com.jeecms.core.manager;

import java.util.Date;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.InvestorQualification;

public interface InvestorQualificationMng {
	/**
	 * 保存投资人认证信息
	 * @param bean
	 * @param userName
	 * @return
	 */
	InvestorQualification saveInvestorQualification(InvestorQualification bean,String userName);
	
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
	void deleteByIds(Integer... ids);
	
	
	/**
	 * 查询投资人认证详细信息
	 * @param userName 用户名
	 * @return
	 */
	InvestorQualification findByUserName(String userName);

	/**
	 * 更新投资人认证信息
	 * @param bean
	 * @return
	 */
	InvestorQualification modifyInvestorQualificationMax(InvestorQualification bean);
	
	/**
	 * 审核投资人认证信息
	 * @param id
	 * @param status 原因
	 * @param reason 状态
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
}
