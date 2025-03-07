package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.XuexiaoEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 支援学校
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("xuexiao")
public class XuexiaoView extends XuexiaoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 支援学校类型的值
	*/
	@ColumnInfo(comment="支援学校类型的字典表值",type="varchar(200)")
	private String xuexiaoValue;




	public XuexiaoView() {

	}

	public XuexiaoView(XuexiaoEntity xuexiaoEntity) {
		try {
			BeanUtils.copyProperties(this, xuexiaoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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




	@Override
	public String toString() {
		return "XuexiaoView{" +
			", xuexiaoValue=" + xuexiaoValue +
			"} " + super.toString();
	}
}
