package org.quesle.tool.fileupload.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 日期时间工具类
 * java unix时间戳转换 
 * @author  作者：yinyang E-mail: 
 * @date 创建时间：2015年7月30日 上午09:51:55 
 * @version 1.0  
 */
public final class TimeUtils
{
    public static final String FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT2 = "yyyy-MM-dd";
    public static final String FORMAT3 = "MM/dd/yyyy HH:mm:ss";
    public static final String FORMAT4 = "MM/dd/yyyy";
    public static final String FORMAT5 = "HH:mm:ss";

    public static final int DATATYPE_YEAR = 1;
    public static final int DATATYPE_MONTH = 2;
    public static final int DATATYPE_DAY = 3;
    public static final int DATATYPE_HOUR = 4;
    public static final int DATATYPE_MINUTE = 5;
    public static final int DATATYPE_SECOND = 6;

    /**
     * 返回unix_time
     * @param ctimestamp
     * @return
     */
    public static String getUninTime(Date ctimestamp)
    {

        long s = ctimestamp.getTime();
        String unixDate = String.valueOf(s).substring(0, 10);
        return unixDate;
    }

    /**
     * unix_time 转成java date
     * @param cTimestamp
     * @return
     */
    public static Date getTimestamp(int cTimestamp)
    {

        return new java.util.Date(cTimestamp * 1000l);
    }

    /**
     * 获取日期格式
     * 支持的格式有：
     * yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd HH:mm:ss.SSS（归到yyyy-MM-dd HH:mm:ss中）
     * yyyy-MM-dd
     * MM/dd/yyyy HH:mm:ss
     * MM/dd/yyyy HH:mm:ss.SSS（归到MM/dd/yyyy HH:mm:ss中）
     * MM/dd/yyyy
     * @param date  日期
     * @return      格式
     */
    public static String getFormat(String date) throws Exception
    {
        String reg1 = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}(.\\d{1,3}){0,1}";
        String reg2 = "\\d{4}-\\d{1,2}-\\d{1,2}";
        String reg3 = "\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}(.\\d{1,3}){0,1}";
        String reg4 = "\\d{1,2}/\\d{1,2}/\\d{4}";
        String reg5 = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
        if (date.matches(reg1) || date.matches(reg5))
        {
            return FORMAT1;
        }
        else if (date.matches(reg2))
        {
            return FORMAT2;
        }
        else if (date.matches(reg3))
        {
            return FORMAT3;
        }
        else if (date.matches(reg4))
        {
            return FORMAT4;
        }
        else
        {
            throw new Exception("不支持的日期格式：" + date);
        }
    }

    /**
     * 获取当前日期（固定格式：yyyy-MM-dd）
     * @return  当前日期
     */
    public static String getCurrentDate()
    {
        return getCurrentDateTime(FORMAT2);
    }

    /**
     * 获取当前时间（固定格式：HH:mm:ss）
     * @return  当前时间
     */
    public static String getCurrentTime()
    {
        return getCurrentDateTime(FORMAT5);
    }

    /**
     * 获取当前日期和时间（固定格式：yyyy-MM-dd HH:mm:ss）
     * @return  当前日期和时间
     */
    public static String getCurrentDateTime()
    {
        return getCurrentDateTime(FORMAT1);
    }

    /**
     * 获取当前日期和时间（自定义格式）
     * 参考格式：yyyy年MM月dd日HH时（hh时）mm分ss秒ms毫秒E本月第F个星期
     * 对应的值：2009年07月22日15时（03时）05分30秒530毫秒星期三本月第4个星期
     * @param format    日期时间的格式
     * @return          当前日期和时间
     */
    public static String getCurrentDateTime(String format)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * 获取昨天的日期（固定格式：yyyy-MM-dd）
     * @return  日期
     */
    public static String getYesterday()
    {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT2);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        return df.format(c.getTime());
    }

    /**
     * 获取明天的日期（固定格式：yyyy-MM-dd）
     * @return  日期
     */
    public static String getTomorrow()
    {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT2);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        return df.format(c.getTime());
    }

    /**
     * 把String转换成日期
     * @param date      String型日期
     * @return          Date型日期
     */
    public static Date convertStringToDate(String date) throws Exception
    {
        SimpleDateFormat df = new SimpleDateFormat(getFormat(date));
        return df.parse(date);
    }

    /**
     * 把日期类型转换成String
     * @param date      Date型日期
     * @param format    转换成String型日期后的格式
     * @return          String型日期
     */
    public static String convertDateToString(Date date, String format)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 日期时间格式转换
     * @param value         转换前的值
     * @param format        转换后的格式
     * @return              转换后的值
     */
    public static String dateFormat(String value, String format)
    {
        try
        {
            Date date = convertStringToDate(value);
            return convertDateToString(date, format);
        }
        catch (Exception e)
        {
            return "";
        }
    }

    /**
     * 计算两个日期的间隔（yyyy MM dd HH mm ss）
     * @param type      间隔的单位：1-年，2-月，3-日，4-小时，5-分钟，6-秒，不填默认为日
     * @param sdate1    String型日期
     * @param sdate2    String型日期
     * @return          间隔的数量。如果日期sdate2在日期sdate1之后，则结果为正数；如果日期sdate2在日期sdate1之前，则结果为负数
     */
    public static int dateDiff(int type, String sdate1, String sdate2) throws Exception
    {
        Date date1 = new SimpleDateFormat(getFormat(sdate1)).parse(sdate1);
        Date date2 = new SimpleDateFormat(getFormat(sdate2)).parse(sdate2);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
        if (type == DATATYPE_YEAR)
        {
            return yearDiff;
        }
        else if (type == DATATYPE_MONTH)
        {//月
            int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
            return monthDiff;
        }
        else
        {
            long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);
            long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);
            if (type == DATATYPE_HOUR)
            {
                return (int) ((ldate2 - ldate1) / 3600000);
            }
            else if (type == DATATYPE_MINUTE)
            {
                return (int) ((ldate2 - ldate1) / 60000);
            }
            else if (type == DATATYPE_SECOND)
            {
                return (int) ((ldate2 - ldate1) / 1000);
            }
            else
            {
                return (int) ((ldate2 - ldate1) / (3600000 * 24));
            }
        }
    }

    /**
     * 计算日期加上一段间隔之后的新日期
     * @param type      间隔的单位：1-年，2-月，3-日，4-小时，5-分钟，6-秒，不填默认为日
     * @param sdate     String型日期
     * @param num       间隔数量
     * @return          返回新日期
     */
    public static String dateAdd(int type, String sdate, int num) throws Exception
    {
        SimpleDateFormat df = new SimpleDateFormat(getFormat(sdate));
        Date date = df.parse(sdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (type == DATATYPE_YEAR)
        {
            cal.add(Calendar.YEAR, num);
        }
        else if (type == DATATYPE_MONTH)
        {
            cal.add(Calendar.MONTH, num);
        }
        else if (type == DATATYPE_HOUR)
        {
            cal.add(Calendar.HOUR, num);
        }
        else if (type == DATATYPE_MINUTE)
        {
            cal.add(Calendar.MINUTE, num);
        }
        else if (type == DATATYPE_SECOND)
        {
            cal.add(Calendar.SECOND, num);
        }
        else
        {
            cal.add(Calendar.DATE, num);
        }
        return df.format(cal.getTime());
    }

    /**
     * 计算年龄
     * 1、对于天，两个日期直接相减
     * 2、对于月，如果2011年3月26日出生，则2011年4月27日才满一个月
     * 3、对于周岁，如果2010年4月26日出生，则2011年4月27日才满一周岁
     * @param birthday  出生日期
     * @param type      年龄类型，Y-周岁，M-月，D-天，默认为周岁
     * @return          年龄数值
     * @throws Exception 
     */
    public static int calAge(String birthday, String type) throws Exception
    {
        String currDate = getCurrentDate();
        if ("D".equalsIgnoreCase(type))
        {
            return dateDiff(DATATYPE_DAY, birthday, currDate);
        }
        else if ("M".equalsIgnoreCase(type))
        {
            int result = dateDiff(DATATYPE_MONTH, birthday, currDate);
            String temp = dateAdd(DATATYPE_MONTH, birthday, result);
            if (dateDiff(DATATYPE_DAY, temp, currDate) <= 0)
            {
                result--;
            }
            return result;
        }
        else
        {
            int result = dateDiff(DATATYPE_YEAR, birthday, currDate);
            String temp = dateAdd(DATATYPE_YEAR, birthday, result);
            if (dateDiff(DATATYPE_DAY, temp, currDate) <= 0)
            {
                result--;
            }
            return result;
        }
    }

    //函数1：time to 16位长度的字符串   :: Date->String

    public static String cTimeToString(Date cTimeStampDate)
    {
        int iMaxLen = 16;
        String strReturn = "";
        String strMask = "0000000000000000";
        if (cTimeStampDate == null)
        {
            return "";
        }
        strReturn = String.valueOf(cTimeStampDate.getTime());

        if (strReturn.length() < strMask.length())
        {
            strReturn = strMask.substring(0, iMaxLen - strReturn.length()) + strReturn;
        }
        else
        {
            strReturn = strReturn.substring(0, 16);
        }

        return strReturn;
    }

    //函数1： 16位长度的字符串 to time  :: String->Date

    public static Date stringToCTime(String str)
    {
        if (str == null || str.equals("") || str.equals("0"))
        {
            String string = "2000-01-01 00:00:00";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try
            {
                date = format.parse(string);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            return date;
        }

        Date dReturn = new Date();
        String strNew = "";
        int i = 0;
        for (i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) != '0')
            {
                break;
            }
        }
        strNew = str.substring(i, str.length());

        try
        {
            long lDate = Long.parseLong(strNew);
            dReturn = new Date(lDate);
        }
        catch (Exception e)
        {
            dReturn = new Date(); //返回当前时间
        }

        return dReturn;
    }
    
    /**
     * 将format1的日期型字符串转换为format2格式的日期字符串
     * @param format1 例如：yyyy-MM-dd HH:mm:ss
     * @param format2 例如：yyyyMMddHHmmss
     * @param objString
     * @return
     * @throws Exception 
     */
    public static String changeDateFormat(String format1,String format2,String objString) throws Exception{
    	SimpleDateFormat sdf1 = new SimpleDateFormat(format1); 
		Date date = sdf1.parse(objString); 
		SimpleDateFormat sdf2 = new SimpleDateFormat(format2); 
		String result = sdf2.format(date); 
		return result;
    }
    
    /**
     * 日期计算
     * @param date 待计算日期
     * @param cal 1为增加一天  -1为减少一天
     * @return
     */
    public static Date calDateOneDay(Date date,int cal) {
		if (null == date) {
			return date;
		}
		Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.DATE, cal); //日期加cal天
        date = c.getTime();
        return date;
	}
    
    /**
     * 月份计算
     * @param date 待计算日期
     * @param cal 1为增加一月  -1为减少一月
     * @return
     */
    public static Date calMonth(Date date,int cal) {
    	if (null == date) {
    		return date;
    	}
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);   //设置当前日期
    	c.add(Calendar.MONTH, cal); //月份加cal天
    	date = c.getTime();
    	return date;
    }
    
    /**
     * 获取月份第一天计算
     * @param date 待计算日期
     * @param cal <pre>1为下一个月的第一天   0为当月的第一天 -1为前一个月的第一天</pre><pre>正N为下N个月的第一天   负N为前N个月的第一天</pre>
     * @return
     */
    public static Date calFirstDayOfMonth(Date date,int cal) {
    	if (null == date) {
    		return date;
    	}
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);   //设置当前日期
    	c.add(Calendar.MONTH, cal);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
    	date = c.getTime();
    	return date;
    }
    
    /**
     * 根据用户生日计算年龄
     * <br>SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");</br>
     * <br>Date birthday = myFormatter.parse("1989-10-02");</br>
     * @param birthday
     * @return
     */
    public static int getAgeByBirthday(Date birthday) {
    	Calendar cal = Calendar.getInstance();

    	if (cal.before(birthday)) {
    		throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable! 出生日期不能超过当前日期!");
    	}

    	int yearNow = cal.get(Calendar.YEAR);
    	int monthNow = cal.get(Calendar.MONTH) + 1;
    	int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

    	cal.setTime(birthday);
    	int yearBirth = cal.get(Calendar.YEAR);
    	int monthBirth = cal.get(Calendar.MONTH) + 1;
    	int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

    	int age = yearNow - yearBirth;

    	if (monthNow <= monthBirth) {
    		if (monthNow == monthBirth) {
    			// monthNow==monthBirth 
    			if (dayOfMonthNow < dayOfMonthBirth) {
    				age--;
    			}
    		} else {
    			// monthNow>monthBirth 
    			age--;
    		}
    	}
    	return age;
    }
    
    public static void main(String[] args) throws ParseException {
    	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date birthday = myFormatter.parse("1989-10-02");
    	//Date birthday = new Date("2000-05-08");
    	int abc = getAgeByBirthday(birthday);
    	System.out.println(abc);
	}
}
