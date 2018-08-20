package com.github.xieyiming.idcard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class IdcardInfoExtractor {
	private Logger logger = LoggerFactory.getLogger(getClass());
	// 省份
		private String province;
		// 城市
		private String city;
		// 区县
		private String region;
		// 年份
		private int year;
		// 月份
		private int month;
		// 日期
		private int day;
		// 性别
		private String gender;
		// 出生日期
		private Date birthday;

		private IdcardValidator validator = null;

		/**
		 * 通过构造方法初始化各个成员属性
		 */
		public IdcardInfoExtractor(String idcard) {
			try {
				validator = new IdcardValidator();
				if (validator.isValidatedAllIdcard(idcard)) {
					if (idcard.length() == 15) {
						idcard = validator.convertIdcarBy15bit(idcard);
					}
					// 获取省份
					String provinceId = idcard.substring(0, 2);
					String cityId= idcard.substring(0, 4);
					String areaId= idcard.substring(0,6);
					this.province = IdcardUtil.getProvince(provinceId);
					this.city = IdcardUtil.getCity(cityId);
					this.region = IdcardUtil.getArea(areaId);
					// 获取性别
					String id17 = idcard.substring(16, 17);
					if (Integer.parseInt(id17) % 2 != 0) {
						this.gender = "男";
					} else {
						this.gender = "女";
					}

					// 获取出生日期
					String birthday = idcard.substring(6, 14);
					Date birthdate = new SimpleDateFormat("yyyyMMdd")
							.parse(birthday);
					this.birthday = birthdate;
					GregorianCalendar currentDay = new GregorianCalendar();
					currentDay.setTime(birthdate);
					this.year = currentDay.get(Calendar.YEAR);
					this.month = currentDay.get(Calendar.MONTH) + 1;
					this.day = currentDay.get(Calendar.DAY_OF_MONTH);
					if (province ==null || city==null || region==null || birthday==null){
						logger.error("身份证解析失败：" + idcard);
					}
				}
			} catch (Exception e) {
				logger.error("身份证解析失败：" + idcard);
			}
		}

		/**
		 * @return the province
		 */
		public String getProvince() {
			return province;
		}

		/**
		 * @return the city
		 */
		public String getCity() {
			return city;
		}

		/**
		 * @return the region
		 */
		public String getRegion() {
			return region;
		}

		/**
		 * @return the year
		 */
		public int getYear() {
			return year;
		}

		/**
		 * @return the month
		 */
		public int getMonth() {
			return month;
		}

		/**
		 * @return the day
		 */
		public int getDay() {
			return day;
		}

		/**
		 * @return the gender
		 */
		public String getGender() {
			return gender;
		}

		/**
		 * @return the birthday
		 */
		public Date getBirthday() {
			return birthday;
		}

		@Override
		public String toString() {
			return "省份：" + this.province + "，城市：" + this.city + "，地区：" + this.region + ",性别：" + this.gender + ",出生日期："
					+ this.birthday;
		}

		public static void main(String[] args) {
			String idcard = "330511197211250320";
			IdcardInfoExtractor extractor = new IdcardInfoExtractor(idcard);
						System.out.println(extractor.toString());
		}
}
