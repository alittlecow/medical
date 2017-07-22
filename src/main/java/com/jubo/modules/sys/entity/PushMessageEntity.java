package com.jubo.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public class PushMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//类别（0健康文章 1充值优惠活动 2广告）
	private Integer type;
	//标题
	private String title;
	//内容
	private String content;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：类别（0健康文章 1充值优惠活动 2广告）
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类别（0健康文章 1充值优惠活动 2广告）
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
}
