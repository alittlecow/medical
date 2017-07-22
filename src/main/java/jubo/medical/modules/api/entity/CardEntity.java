package jubo.medical.modules.api.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 21:42:07
 */
public class CardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//用户id
	private String userId;
	//设备编码
	private String code;
	//剩余使用次数
	private Integer count;
	//创建时间
	private Date createTime;
	//上次使用时间
	private Date lastUseTime;

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
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：设备编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：设备编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：剩余使用次数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：剩余使用次数
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：上次使用时间
	 */
	public void setLastUseTime(Date lastUseTime) {
		this.lastUseTime = lastUseTime;
	}
	/**
	 * 获取：上次使用时间
	 */
	public Date getLastUseTime() {
		return lastUseTime;
	}
}
