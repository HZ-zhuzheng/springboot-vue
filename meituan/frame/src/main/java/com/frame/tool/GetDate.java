package com.frame.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**时间毫秒值转日期格式
 * @author zhuzheng
 *
 */
public class GetDate {
	 static public String TimeYY_MM_DD_HH_MM_SS(long lon) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(lon);
	}
	 
	 static public String TimeYY_MM_DD(long lon) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(lon);
	}
	 
	 static public String TimeYYYYMMDDHHMMSS(long lon) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSS");
			return sdf.format(lon);
		}
	 
	 // 日期字符串转时间毫秒值
	 static public long DateToTLong(String dateString) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
		Date date = sdf.parse(dateString);//String-->Date 
		Long dateLong = date.getTime();//Date-->Long
				
		return dateLong;
	}
	 
	// 日期字符串转时间毫秒值
	 static public String DateToStr(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);//Date-->
	}
	
	 
	 
	 
	
}
