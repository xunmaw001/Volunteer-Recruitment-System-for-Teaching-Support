package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZhaopinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 职位招聘
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("zhaopin")
public class ZhaopinView extends ZhaopinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 招聘岗位的值
	*/
	@ColumnInfo(comment="招聘岗位的字典表值",type="varchar(200)")
	private String zhaopinValue;
	/**
	* 招聘类型的值
	*/
	@ColumnInfo(comment="招聘类型的字典表值",type="varchar(200)")
	private String leixingValue;

	//级联表 支援学校
		/**
		* 支援学校名称
		*/

		@ColumnInfo(comment="支援学校名称",type="varchar(200)")
		private String xuexiaoName;
		/**
		* 支援学校类型
		*/
		@ColumnInfo(comment="支援学校类型",type="int(11)")
		private Integer xuexiaoTypes;
			/**
			* 支援学校类型的值
			*/
			@ColumnInfo(comment="支援学校类型的字典表值",type="varchar(200)")
			private String xuexiaoValue;
		/**
		* 联系方式
		*/

		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String xuexiaoPhone;
		/**
		* 邮箱
		*/

		@ColumnInfo(comment="邮箱",type="varchar(200)")
		private String xuexiaoEmail;
		/**
		* 支援学校封面
		*/

		@ColumnInfo(comment="支援学校封面",type="varchar(200)")
		private String xuexiaoPhoto;
		/**
		* 支援学校简介
		*/

		@ColumnInfo(comment="支援学校简介",type="text")
		private String xuexiaoContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer xuexiaoDelete;



	public ZhaopinView() {

	}

	public ZhaopinView(ZhaopinEntity zhaopinEntity) {
		try {
			BeanUtils.copyProperties(this, zhaopinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 招聘岗位的值
	*/
	public String getZhaopinValue() {
		return zhaopinValue;
	}
	/**
	* 设置： 招聘岗位的值
	*/
	public void setZhaopinValue(String zhaopinValue) {
		this.zhaopinValue = zhaopinValue;
	}
	//当前表的
	/**
	* 获取： 招聘类型的值
	*/
	public String getLeixingValue() {
		return leixingValue;
	}
	/**
	* 设置： 招聘类型的值
	*/
	public void setLeixingValue(String leixingValue) {
		this.leixingValue = leixingValue;
	}


	//级联表的get和set 支援学校

		/**
		* 获取： 支援学校名称
		*/
		public String getXuexiaoName() {
			return xuexiaoName;
		}
		/**
		* 设置： 支援学校名称
		*/
		public void setXuexiaoName(String xuexiaoName) {
			this.xuexiaoName = xuexiaoName;
		}
		/**
		* 获取： 支援学校类型
		*/
		public Integer getXuexiaoTypes() {
			return xuexiaoTypes;
		}
		/**
		* 设置： 支援学校类型
		*/
		public void setXuexiaoTypes(Integer xuexiaoTypes) {
			this.xuexiaoTypes = xuexiaoTypes;
		}


			/**
			* 获取： 支援学校类型的值
			*/
			public String getXuexiaoValue() {
				return xuexiaoValue;
			}
			/**
			* 设置： 支援学校类型的值
			*/
			public void setXuexiaoValue(String xuexiaoValue) {
				this.xuexiaoValue = xuexiaoValue;
			}

		/**
		* 获取： 联系方式
		*/
		public String getXuexiaoPhone() {
			return xuexiaoPhone;
		}
		/**
		* 设置： 联系方式
		*/
		public void setXuexiaoPhone(String xuexiaoPhone) {
			this.xuexiaoPhone = xuexiaoPhone;
		}

		/**
		* 获取： 邮箱
		*/
		public String getXuexiaoEmail() {
			return xuexiaoEmail;
		}
		/**
		* 设置： 邮箱
		*/
		public void setXuexiaoEmail(String xuexiaoEmail) {
			this.xuexiaoEmail = xuexiaoEmail;
		}

		/**
		* 获取： 支援学校封面
		*/
		public String getXuexiaoPhoto() {
			return xuexiaoPhoto;
		}
		/**
		* 设置： 支援学校封面
		*/
		public void setXuexiaoPhoto(String xuexiaoPhoto) {
			this.xuexiaoPhoto = xuexiaoPhoto;
		}

		/**
		* 获取： 支援学校简介
		*/
		public String getXuexiaoContent() {
			return xuexiaoContent;
		}
		/**
		* 设置： 支援学校简介
		*/
		public void setXuexiaoContent(String xuexiaoContent) {
			this.xuexiaoContent = xuexiaoContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getXuexiaoDelete() {
			return xuexiaoDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setXuexiaoDelete(Integer xuexiaoDelete) {
			this.xuexiaoDelete = xuexiaoDelete;
		}


	@Override
	public String toString() {
		return "ZhaopinView{" +
			", zhaopinValue=" + zhaopinValue +
			", leixingValue=" + leixingValue +
			", xuexiaoName=" + xuexiaoName +
			", xuexiaoPhone=" + xuexiaoPhone +
			", xuexiaoEmail=" + xuexiaoEmail +
			", xuexiaoPhoto=" + xuexiaoPhoto +
			", xuexiaoContent=" + xuexiaoContent +
			", xuexiaoDelete=" + xuexiaoDelete +
			"} " + super.toString();
	}
}
