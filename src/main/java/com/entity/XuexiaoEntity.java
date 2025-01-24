package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 支援学校
 *
 * @author 
 * @email
 */
@TableName("xuexiao")
public class XuexiaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XuexiaoEntity() {

	}

	public XuexiaoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 支援学校名称
     */
    @ColumnInfo(comment="支援学校名称",type="varchar(200)")
    @TableField(value = "xuexiao_name")

    private String xuexiaoName;


    /**
     * 支援学校类型
     */
    @ColumnInfo(comment="支援学校类型",type="int(11)")
    @TableField(value = "xuexiao_types")

    private Integer xuexiaoTypes;


    /**
     * 联系方式
     */
    @ColumnInfo(comment="联系方式",type="varchar(200)")
    @TableField(value = "xuexiao_phone")

    private String xuexiaoPhone;


    /**
     * 邮箱
     */
    @ColumnInfo(comment="邮箱",type="varchar(200)")
    @TableField(value = "xuexiao_email")

    private String xuexiaoEmail;


    /**
     * 支援学校封面
     */
    @ColumnInfo(comment="支援学校封面",type="varchar(200)")
    @TableField(value = "xuexiao_photo")

    private String xuexiaoPhoto;


    /**
     * 支援学校简介
     */
    @ColumnInfo(comment="支援学校简介",type="text")
    @TableField(value = "xuexiao_content")

    private String xuexiaoContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "xuexiao_delete")

    private Integer xuexiaoDelete;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 设置：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 设置：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：支援学校名称
	 */
    public String getXuexiaoName() {
        return xuexiaoName;
    }
    /**
	 * 设置：支援学校名称
	 */

    public void setXuexiaoName(String xuexiaoName) {
        this.xuexiaoName = xuexiaoName;
    }
    /**
	 * 获取：支援学校类型
	 */
    public Integer getXuexiaoTypes() {
        return xuexiaoTypes;
    }
    /**
	 * 设置：支援学校类型
	 */

    public void setXuexiaoTypes(Integer xuexiaoTypes) {
        this.xuexiaoTypes = xuexiaoTypes;
    }
    /**
	 * 获取：联系方式
	 */
    public String getXuexiaoPhone() {
        return xuexiaoPhone;
    }
    /**
	 * 设置：联系方式
	 */

    public void setXuexiaoPhone(String xuexiaoPhone) {
        this.xuexiaoPhone = xuexiaoPhone;
    }
    /**
	 * 获取：邮箱
	 */
    public String getXuexiaoEmail() {
        return xuexiaoEmail;
    }
    /**
	 * 设置：邮箱
	 */

    public void setXuexiaoEmail(String xuexiaoEmail) {
        this.xuexiaoEmail = xuexiaoEmail;
    }
    /**
	 * 获取：支援学校封面
	 */
    public String getXuexiaoPhoto() {
        return xuexiaoPhoto;
    }
    /**
	 * 设置：支援学校封面
	 */

    public void setXuexiaoPhoto(String xuexiaoPhoto) {
        this.xuexiaoPhoto = xuexiaoPhoto;
    }
    /**
	 * 获取：支援学校简介
	 */
    public String getXuexiaoContent() {
        return xuexiaoContent;
    }
    /**
	 * 设置：支援学校简介
	 */

    public void setXuexiaoContent(String xuexiaoContent) {
        this.xuexiaoContent = xuexiaoContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getXuexiaoDelete() {
        return xuexiaoDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setXuexiaoDelete(Integer xuexiaoDelete) {
        this.xuexiaoDelete = xuexiaoDelete;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Xuexiao{" +
            ", id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", xuexiaoName=" + xuexiaoName +
            ", xuexiaoTypes=" + xuexiaoTypes +
            ", xuexiaoPhone=" + xuexiaoPhone +
            ", xuexiaoEmail=" + xuexiaoEmail +
            ", xuexiaoPhoto=" + xuexiaoPhoto +
            ", xuexiaoContent=" + xuexiaoContent +
            ", xuexiaoDelete=" + xuexiaoDelete +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
