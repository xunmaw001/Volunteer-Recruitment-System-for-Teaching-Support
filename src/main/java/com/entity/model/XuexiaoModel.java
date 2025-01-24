package com.entity.model;

import com.entity.XuexiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 支援学校
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XuexiaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 支援学校名称
     */
    private String xuexiaoName;


    /**
     * 支援学校类型
     */
    private Integer xuexiaoTypes;


    /**
     * 联系方式
     */
    private String xuexiaoPhone;


    /**
     * 邮箱
     */
    private String xuexiaoEmail;


    /**
     * 支援学校封面
     */
    private String xuexiaoPhoto;


    /**
     * 支援学校简介
     */
    private String xuexiaoContent;


    /**
     * 逻辑删除
     */
    private Integer xuexiaoDelete;


    /**
     * 创建时间 show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
