package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentExt;

/**
 * This is an object that contains data related to the jc_activities_enroll table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_activities_enroll"
 */

public abstract class BaseActivitiesEnroll implements Serializable{

	private Integer id;
    private String enrollName;
    private String enrollPhone;
    private Date enrollTime;
    private Content content;
    
    public BaseActivitiesEnroll() {
    	initialize();
    }

    public BaseActivitiesEnroll (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	public BaseActivitiesEnroll(Integer id, String enrollName, String enrollPhone, Date enrollTime, Content content
			) {
		super();
		this.id = id;
		this.enrollName = enrollName;
		this.enrollPhone = enrollPhone;
		this.enrollTime = enrollTime;
		this.content = content;
	}
    
	protected void initialize () {}
	   
    private int hashCode = Integer.MIN_VALUE;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnrollName() {
		return enrollName;
	}

	public void setEnrollName(String enrollName) {
		this.enrollName = enrollName;
	}

	public String getEnrollPhone() {
		return enrollPhone;
	}

	public void setEnrollPhone(String enrollPhone) {
		this.enrollPhone = enrollPhone;
	}

	public Date getEnrollTime() {
		return enrollTime;
	}

	public void setEnrollTime(Date enrollTime) {
		this.enrollTime = enrollTime;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}
    
	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public boolean equals(Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivitiesEnroll)) return false;
		else {
			com.jeecms.cms.entity.main.ActivitiesEnroll activitiesEnroll = (com.jeecms.cms.entity.main.ActivitiesEnroll) obj;
			if (null == this.getId() || null == activitiesEnroll.getId()) return false;
			else return (this.getId().equals(activitiesEnroll.getId()));
		}
	}


	public String toString () {
		return super.toString();
	}
}
