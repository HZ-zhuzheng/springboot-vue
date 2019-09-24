package com.along.entity;

import java.util.Date;

import org.mockito.internal.matchers.And;

import com.frame.tool.GetDate;

/** 用户账户信息实体
 * @author zhuzheng
 *
 */
public class Count {
	
	private String id;
	private String number;
	private String name;
	private int age;
	private String sex;
	private Date birth;
	private String place;
	private String city;
	
	// 临时属性
	private String birthStr; // birth 的yy-mm-dd格式
	private String dates0;
	private String dates1;
	
	// 查询条件
	private String find; 	//查询类型 sure精准 dim模糊
	private String dates[];	//出入日期范围
	private int agestart;	//年龄范围开始
	private int ageend;	//年龄范围结束
	private String checkedCities[]; //city选择
	
	
	//删除id
	private String deleteID[]; //待删除的id
	
	// 分页
	private int pageNo;	//页码
	private int pageSize;	//页数
	private int countNum;	//总记录数
	
	
	
	public int getCountNum() {
		return countNum;
	}
	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getDates0() {
		return dates0;
	}
	public void setDates0(String dates0) {
		this.dates0 = dates0;
	}
	public String getDates1() {
		return dates1;
	}
	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}
	public String getBirthStr() {
		return birthStr;
	}
	public void setBirthStr(String birthStr) {
		this.birthStr = birthStr;
	}
	public String[] getDeleteID() {
		return deleteID;
	}
	public void setDeleteID(String[] deleteID) {
		this.deleteID = deleteID;
	}
	public String getFind() {
		return find;
	}
	public void setFind(String find) {
		this.find = find;
	}
	public String[] getDates() {
		return dates;
	}
	public void setDates(String[] dates) {
		this.dates = dates;
		if (dates != null && dates.length > 0 ) { //进行赋值
			this.dates0 = dates[0];
			this.dates1 = dates[1];
		}
	}
	public int getAgestart() {
		return agestart;
	}
	public void setAgestart(int agestart) {
		this.agestart = agestart;
	}
	
	public int getAgeend() {
		return ageend;
	}
	public void setAgeend(int ageend) {
		this.ageend = ageend;
	}
	public String[] getCheckedCities() {
		return checkedCities;
	}
	public void setCheckedCities(String[] checkedCities) {
		this.checkedCities = checkedCities;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
		this.setBirthStr(GetDate.DateToStr(birth)); //转yy-mm-dd
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
