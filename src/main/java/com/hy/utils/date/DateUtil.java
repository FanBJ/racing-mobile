package com.hy.utils.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.http.util.TextUtils;

import com.hy.utils.validator.Validator;

public class DateUtil {
	private static final String DEFAULT_DATE_FORMAT = getDefaultDateFormat();
	public static final String DF = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 把String类型的值转化为java.util.Date
	 * 
	 * @param source
	 *            要转化的字符串
	 * @param format
	 *            转化格式，如：yyyy-MM-dd，yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date convertStringToDate(String source, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 当前时间加上N天
	 */
	public static Date Ds(int days) {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day + days);
		return calendar.getTime();
	}

	/**
	 * 当前时间加上N天
	 */
	public static Date Ds(int days, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day + days);
		return calendar.getTime();
	}

	/**
	 * 当前时间增加N月
	 */
	public static Date MonthAdd(int days) {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, day + days);
		return calendar.getTime();
	}

	/**
	 * 指定时间增加N月
	 */
	public static Date fixMonthAdd(String date, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(convertStringToDate(date, DF));
		int day = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, day + month);
		return calendar.getTime();
	}

	/**
	 * 获得系统当前时间
	 */
	public static String nowDate() {
		return getDateFormat().format(System.currentTimeMillis());
	}

	/**
	 * 获得系统当前时间
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp getTimestamp(Long l) {
		return new Timestamp(l);
	}

	/**
	 * 获得系统当前时间
	 */
	public static String nowDate(String df) {
		if (Validator.isBlank(df))
			return getDateFormat().format(System.currentTimeMillis());
		else
			return getDateFormat(df).format(System.currentTimeMillis());
	}

	/**
	 * 获得当前指定时间
	 */
	public static String nowDate(String df, Date date) {
		if (Validator.isBlank(df))
			return getDateFormat().format(date);
		else
			return getDateFormat(df).format(date);
	}

	/**
	 * 获得当前指定时间
	 */
	public static String nowDate(String df, long currentTimeMillis) {
		if (Validator.isBlank(df))
			return getDateFormat().format(currentTimeMillis);
		else
			return getDateFormat(df).format(currentTimeMillis);
	}

	/**
	 * 时间差计算
	 */
	public static String costTime(long time1, long time2) {
		long sub = time1 - time2;
		// yyyy-MM-dd HH:mm:ss
		String time = "";
		// 多少小时
		long remainder = sub % (3600 * 1000);
		long result = sub / (3600 * 1000);
		if (result < 10) {
			time += "00" + result;
		} else if (result < 100) {
			time += "0" + result;
		} else {
			time += "" + result;
		}
		// 多少分钟
		sub = remainder;
		remainder = sub % (60 * 1000);
		result = sub / (60 * 1000);
		if (result < 10) {
			time += ":0" + result;
		} else {
			time += ":" + result;
		}
		// 多少秒
		sub = remainder;
		result = sub / (1000);
		if (result < 10) {
			time += ":0" + result;
		} else {
			time += ":" + result;
		}

		return time;
	}

	/**
	 * 时间差计算
	 * 
	 * @param startTime
	 *            开始时间
	 * @param minute
	 *            限制时间
	 * @return 剩余毫秒数
	 */
	public static long costTime(String startTime, String minute) throws ParseException {
		Date date = getDateFormat("YYYY_MM_DD_HH_MM_SS").parse(startTime);
		long originalTimeMillis = date.getTime();
		long currentTimeMillis = System.currentTimeMillis();
		long minuteTimeMillis = Long.parseLong(minute) * 60 * 1000;

		return minuteTimeMillis - (currentTimeMillis - originalTimeMillis);
	}
	public static String nowDate(String data, String df) {
		Date date=null;
		try {
			date = getDateFormat(df).parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DateUtil.formatDateStr(date, df);
	}
	/**
	 * 时间差计算
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param minute
	 *            限制时间
	 * @return 剩余毫秒数
	 */
	public static long costTime(String startTime, String endTime, String minute) throws Exception {
		long originalTimeMillis = convertTimeMillis(startTime);
		long currentTimeMillis = convertTimeMillis(endTime);
		long minuteTimeMillis = Long.parseLong(minute) * 60 * 1000;
		return minuteTimeMillis - (currentTimeMillis - originalTimeMillis);
	}

	/**
	 * 将指定时间转换为毫秒数
	 * 
	 * @param time
	 *            指定时间
	 */
	public static long convertTimeMillis(String time) throws Exception {
		try {
			return getDateFormat("YYYY_MM_DD_HH_MM_SS").parse(time).getTime();
		} catch (Exception e) {
			return getDateFormat(getDefaultDateFormat()).parse(time).getTime();
		}
	}

	public static long convertTimeMillis(String time, String f) {
		try {
			return getDateFormat(f).parse(time).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 默认日期格式
	 */
	protected static String getDefaultDateFormat() {
		return DF;
	}

	/**
	 * 获得默认日期格式
	 */
	protected static DateFormat getDateFormat() {
		return new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	}

	/**
	 * 获得指定日期格式
	 */
	protected static DateFormat getDateFormat(String format) {
		return new SimpleDateFormat(format);
	}

	/**
	 * 根据日期格式格式化时间
	 */
	protected static String format(String format, Date date) {
		return getDateFormat(format).format(date);
	}

	/**
	 * 根据日期格式格式化时间
	 */
	protected static String format(String format, long date) {
		return getDateFormat(format).format(date);
	}

	/**
	 * 获取系统时间是星期几
	 * 
	 * @return 0=星期天,1＝星期一,...,6=星期六
	 */
	public static int getDayOfWeek() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 获取时间是星期几
	 * 
	 * @param time
	 * @return 0=星期天,1＝星期一,...,6=星期六
	 */
	public static int getDayOfWeek(long time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		return c.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 获取时间是一个月中的几号
	 * 
	 * @param time
	 * @return 对应的号数
	 */
	public static int getDayOfMonth(long time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取系统时间是一个月中的几号
	 * 
	 * @param time
	 * @return 对应的号数
	 */
	public static int getDayOfMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前时间所在的周数
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		return week;
	}

	/**
	 * 获得某年的最大周数
	 * 
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekr(c.getTime());
	}

	/**
	 * 获取当前年
	 * 
	 * @return
	 */
	public static int getNowYeay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取当前月
	 * 
	 * @return
	 */
	public static int getNowMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到某年某周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 设置周一
		c.setFirstDayOfWeek(Calendar.MONDAY);
		return c.getTime();
	}

	/**
	 * 得到某年某周的最后一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 得到某年第一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getFirstDayOfYear(int year) {
		return getFirstDayOfQuarter(year, 1);
	}

	/**
	 * 得到某年最后一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getLastDayOfYear(int year) {
		return getLastDayOfQuarter(year, 4);
	}

	/**
	 * 得到某年某季度第一天
	 * 
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getFirstDayOfQuarter(int year, int quarter) {
		int month = 0;
		if (quarter > 4) {
			return null;
		} else {
			month = (quarter - 1) * 3 + 1;
		}
		return getFirstDayOfMonth(year, month);
	}

	/**
	 * 得到某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 得到某年某月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));

		return c.getTime();
	}

	/**
	 * 得到某年某季度最后一天
	 * 
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(int year, int quarter) {
		int month = 0;
		if (quarter > 4) {
			return null;
		} else {
			month = quarter * 3;
		}
		return getLastDayOfMonth(year, month);
	}

	protected static int getWeekr(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		return week;
	}

	/**
	 * 获取某年某月有多少天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthDay(int year, int month) {
		int[] day = { 31, 28, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int rday = 0;
		if (month == 2) {
			if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {// 2月瑞年
				rday = day[2];
			} else {
				rday = day[1];
			}
		} else {
			if (month == 1) {
				rday = day[month - 1];
			} else {
				rday = day[month];
			}
		}
		return rday;
	}

	/**
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param type
	 *            1 获取秒 2 获取分 3 获取小时 4获取天
	 * @return
	 */
	public static long getMistiming(Date endDate, Date startDate, int type) {
		// milliseconds
		long different = endDate.getTime() - startDate.getTime();
		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;
		if (type == 4) {// 获取 天
			different = different / daysInMilli;
		}
		if (type == 3) { // 获取 小时
			different = different / hoursInMilli;
		}
		if (type == 2) { // 获取 分钟
			different = different / minutesInMilli;
		}
		if (type == 1) { // 获取 秒
			different = different / secondsInMilli;
		}
		return different;
	}

	/**
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param type
	 *            1 获取秒 2 获取分 3 获取小时 4获取天
	 * @return
	 */
	public static long getMistiming(String endTime, String startTime, int type) {
		return getMistiming(convertStringToDate(endTime, DF), convertStringToDate(startTime, DF), type);
	}

	/**
	 * 获取年
	 * 
	 * @param date
	 * @return
	 */
	public static int getDateYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取年
	 * 
	 * @param date
	 * @return
	 */
	public static int getDateYear(String date) {
		return getDateYear(convertStringToDate(date, DF));
	}

	/**
	 * 获取月
	 * 
	 * @param date
	 * @return
	 */
	public static int getDateMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取月
	 * 
	 * @param date
	 * @return
	 */
	public static int getDateMonth(String date) {
		return getDateMonth(convertStringToDate(date, DF));
	}

	/**
	 * 字符串转成时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDate(String date) {
		return convertStringToDate(date, DF);
	}

	public static String formatFirstTimeOfMonth() {
		return format("yyyy-MM-dd", getFirstDayOfMonth(getNowYeay(), getNowMonth())) + " 00:00:00";
	}

	public static String formatLastTimeOfMonth() {
		return format("yyyy-MM-dd", getLastDayOfMonth(getNowYeay(), getNowMonth())) + " 23:59:59";
	}

	/**
	 * 获取两段时间的月差
	 * 
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static int getMonthDiffer(String begin, String end) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;
		try {
			beginDate = df.parse(begin);
			endDate = df.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int beginYear = beginDate.getYear();
		int beginMonth = beginDate.getMonth();

		int endYear = endDate.getYear();
		int endMonth = endDate.getMonth();
		int difMonth = (endYear - beginYear) * 12 + (endMonth - beginMonth) + 1;
		return difMonth;
	}

	public static String getTimeAddHour(String time, int hour) {
		Date d = convertStringToDate(time, DF);
		Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
		cal.setTime(d);
		cal.add(Calendar.HOUR_OF_DAY, hour);
		Date date = cal.getTime();
		return nowDate(DF, date);
	}

	/**
	 * 比较两个时间大小
	 * 
	 * @param time1
	 *            一个时间
	 * @param time2
	 *            第二个时间
	 * @return 1 time1>time2 2 time1=time2 3 time1<time2
	 */
	public static int compareTime(String time1, String time2) {
		Long t1 = convertStringToDate(time1, DF).getTime();
		Long t2 = convertStringToDate(time2, DF).getTime();
		if (t1 > t2) {
			return 1;
		} else if (t1 == t2) {
			return 2;
		} else {
			return 3;
		}
	}

	/**
	 * 比较两个时间大小
	 * 
	 * @param time1
	 *            一个时间
	 * @param time2
	 *            第二个时间
	 * @return 1 time1>time2 2 time1=time2 3 time1<time2
	 */
	public static int compareTime(long time1, long time2) {
		if (time1 > time2) {
			return 1;
		} else if (time1 == time2) {
			return 2;
		} else {
			return 3;
		}
	}

	/**
	 * 比较两个时间大小
	 * 
	 * @param time1
	 *            一个时间
	 * @param time2
	 *            第二个时间
	 * @return 1 time1>time2 2 time1=time2 3 time1<time2
	 */
	public static int compareTime(Date time1, Date time2) {
		return compareTime(time1.getTime(), time2.getTime());
	}

	/**
	 * 比较两个时间大小
	 * 
	 * @param time1
	 *            一个时间
	 * @param time2
	 *            第二个时间
	 * @return 1 time1>time2 2 time1=time2 3 time1<time2
	 */
	public static int compareTime(Timestamp time1, Timestamp time2) {
		return compareTime(time1.getTime(), time2.getTime());
	}

	/**
	 * 是否大于系统时间
	 * 
	 * @param time
	 * @return true=大于系统时间,false=小于或等于系统时间
	 */
	public static boolean isGtSysTime(long time) {
		return time > System.currentTimeMillis();
	}

	/**
	 * <pre>
	 * 日期相同，比较两个时间大小
	 * 注：只比较时、分、秒
	 * </pre>
	 * 
	 * @param time1
	 *            一个时间
	 * @param time2
	 *            第二个时间
	 * @return 1 time1>time2 2 time1=time2 3 time1<time2
	 */
	public static int compareSmallTime(long time1, long time2) {
		String date = format("yyyy-MM-dd", System.currentTimeMillis());

		String one = format("HH:mm:ss", time1);
		String two = format("HH:mm:ss", time2);

		return compareTime(date + " " + one, date + " " + two);
	}

	/**
	 * 判断系统时间是否在两个时间之间，只比较时、分、秒
	 * 
	 * @param begin
	 *            开始
	 * @param end
	 *            结束
	 * @return true=是
	 */
	public static boolean isBetweenSysTime(long begin, long end) {
		String date = format("yyyy-MM-dd", System.currentTimeMillis());
		String one = date + " " + format("HH:mm:ss", begin);
		String two = date + " " + format("HH:mm:ss", end);

		long beginTime = convertStringToDate(one, DF).getTime();
		long endTime = convertStringToDate(two, DF).getTime();
		return (!isGtSysTime(beginTime) && isGtSysTime(endTime));
	}

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
	}
	
	 /** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String dateTimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
    public static final int COMPARE_TYPE_YEAR = 0;
    public static final int COMPARE_TYPE_MONTH = 1;
    public static final int COMPARE_TYPE_DAY = 2;

    public static final int SECOND = 1;
    public static final int MINUTE = 60 * SECOND;
    public static final int HOUR = 60 * MINUTE;
    public static final int DAY = 24 * HOUR;
    public static final int WEEK = 7 * DAY;
    public static final int YEAR = 365 * DAY;

    /**
     * 生日转年龄
     * @param date 8位整形数如 19890604
     * @return 返回年龄，负数表示发送错误
     */
    public static int birthdayToAge(int date) {
        return DateUtil.birthdayToAge(String.valueOf(date), "yyyyMMdd");
    }

    /**
     * 生日转年龄
     * @param dateString 生日日期字符串 "1999-09-09"
     * @param formatString 日期格式 "yyyy-MM-dd"
     * @return 返回年龄，负数表示发送错误
     */
    public static int birthdayToAge(String dateString, String formatString) {
        return DateUtil.compareDate(dateString, null, formatString, COMPARE_TYPE_YEAR);
    }

    /**
     * 生日转年龄
     * @param date 生日日期
     * @return 返回年龄，负数表示发生错误
     */
    public static int birthdayToAge(Date date) {
        return DateUtil.compareDate(date, null, COMPARE_TYPE_YEAR);
    }

    /**
     * 日期比较
     * @param dateString1 要比较的日期字符串1 "1999-09-09"
     * @param dateString2 要比较的日期字符串2 "2009-09-09" 此参数传null表示当前日期
     * @param formatString 日期格式 "yyyy-MM-dd"
     * @param type 要比较的类型 COMPARE_TYPE_YEAR 返回相差的年 COMPARE_TYPE_MONTH 返回相差的月
     * COMPARE_TYPE_DAY 返回相差的天
     * @return 返回相差类型的数目，负数表示发生错误
     */
    public static int compareDate(String dateString1, String dateString2, String formatString, int type) {
        if (TextUtils.isEmpty(dateString1)) {
            return -1;
        }
        Date date1 = DateUtil.parseDate(dateString1, formatString);
        Date date2 = null;
        if (!TextUtils.isEmpty(dateString2)) {
            date2 = DateUtil.parseDate(dateString2, formatString);
            if (date2 == null) {
                return -1;
            }
        }
        return compareDate(date1, date2, type);
    }

    /**
     * 日期比较
     * @param date1 要比较的日期1 "1999-09-09"
     * @param date2 要比较的日期2 "2009-09-09" 此参数传null表示当前日期
     * @param type 要比较的类型 COMPARE_TYPE_YEAR 返回相差的年 COMPARE_TYPE_MONTH 返回相差的月
     * COMPARE_TYPE_DAY 返回相差的天
     * @return 返回相差类型的数目，负数表示发生错误
     */
    public static int compareDate(Date date1, Date date2, int type) {
        long ret = 0;
        if (date1 == null) {
            return -1;
        }
        if (date2 == null) {
            date2 = new Date();
        }
        switch (type) {
            case COMPARE_TYPE_DAY:
                ret = Math.abs(date1.getTime() - date2.getTime()) / 1000 / DAY;
                break;
            case COMPARE_TYPE_MONTH:
                ret = Math.abs(getYear(date1) - getYear(date2)) * 12;
                if (ret == 0) {
                    ret = Math.abs(getMonth(date1) - getMonth(date2));
                }
                else {
                    ret -= (getMonth(date1) - getMonth(date2));
                }
                break;
            case COMPARE_TYPE_YEAR:
                ret = Math.abs(getYear(date1) - getYear(date2));
                break;
        }
        return (int) ret;
    }

    public enum DateType {
        TODAY, YESTERDAY, THIS_YEAR, OTHER_YEAR
    }

    /**
     * 获取日期类型
     * @param dstDate 日期
     * @return {@link DateType} 今天、昨天、今年、非今年
     */
    public static DateType getDateType(Date dstDate) {
        if (dstDate == null) {
            return DateType.OTHER_YEAR;
        }
        int dstYear = getYear(dstDate);
        int dstMonth = getMonth(dstDate);
        int dstDay = getDay(dstDate);

        Date currentDate = new Date();
        int currentYear = getYear(currentDate);
        int currentMonth = getMonth(currentDate);
        int currentDay = getDay(currentDate);

        if (dstYear == currentYear) {
            // 这个月的日期
            if (dstMonth == currentMonth) {
                // 今天
                if (dstDay == currentDay) {
                    return DateType.TODAY;
                }
                // 昨天
                else if (currentDay - dstDay == 1) {
                    return DateType.YESTERDAY;
                }
            }
            // 今年的日期
            return DateType.THIS_YEAR;
        }
        // 不是今年的日期
        return DateType.OTHER_YEAR;
    }

    /**
     * 获取日期类型
     * @param time 从 1970-01-01 00:00:00 开始的秒数
     * @return {@link DateType} 今天、昨天、今年、非今年
     */
    public static DateType getDateType(long time) {
        return getDateType(new Date(time * 1000));
    }

    /**
     * 获取上个月的最后一天
     * @return 上个月最后一天
     */
    public static String lastMonLastDay() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String months = "";
        String days = "";
        if (month > 1) {
            month--;
        }
        else {
            year--;
            month = 12;
        }
        if (!(String.valueOf(month).length() > 1)) {
            months = "0" + month;
        }
        else {
            months = String.valueOf(month);
        }
        if (!(String.valueOf(day).length() > 1)) {
            days = "0" + day;
        }
        else {
            days = String.valueOf(day);
        }
        String lastDay = "" + year + "-" + months + "-" + days;
        String[] lastMonth = new String[2];
        lastMonth[1] = lastDay;
        return days;
    }

    public static boolean isSameDay(long timeA, long timeB) {
        return isSameDay(new Date(timeA), new Date(timeB));
    }

    public static boolean isSameDay(Date dateA, Date dateB) {
        return DateUtil.getYear(dateA) == DateUtil.getYear(dateB) && DateUtil.getMonth(dateA) == DateUtil.getMonth(dateB) && DateUtil.getDay(dateA) == DateUtil.getDay(dateB);
    }

    /**
     * 解析日期
     * @param year 日期的8位整数如19890604
     * @return 返回日期 null表示发生错误
     */
    public static Date parseDate(int year) {
        return DateUtil.parseDate(String.valueOf(year), "yyyyMMdd");
    }

    /**
     * 解析日期
     * @param year 4位年份 2008
     * @param month 2位月份 05
     * @param day 2位日份 12
     * @return 返回日期 null表示发生错误
     */
    public static Date parseDate(int year, int month, int day) {
        return parseDate(year, month, day, 0, 0, 0);
    }

    /**
     * 解析日期
     * @param year 4位年份 2008
     * @param month 2位月份 05
     * @param day 2位日份 12
     * @param hour 2位小时 15
     * @param minute 2位分钟 11
     * @param second 2位秒数 11
     * @return 返回日期 null表示发生错误
     */
    public static Date parseDate(int year, int month, int day, int hour, int minute, int second) {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        String dateString = String.format(Locale.getDefault(), "%04d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
        return parseDate(dateString, dateFormat);
    }

    /**
     * 解析日期
     * @param dateString 日期字符串 "1989-06-04 18:00:00"
     * @param dateFormat 日期格式 "yyyy-MM-dd HH:mm:ss"
     * @return 返回日期 null表示发生错误
     */
    public static Date parseDate(String dateString, String dateFormat) {
        Date date = null;
        SimpleDateFormat format = null;
        try {
            format = new SimpleDateFormat(dateFormat, Locale.getDefault());
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (format != null) {
            try {
                date = format.parse(dateString);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 日期转字符串
     * @param date 要转换的日期
     * @param dateFormat "yyyy-MM-dd HH:mm:ss"
     * @return 返回日期 null表示发生错误
     */
    public static String parseString(Date date, String dateFormat) {
        String dateString = null;
        SimpleDateFormat format = null;
        try {
            format = new SimpleDateFormat(dateFormat, Locale.getDefault());
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (format != null && date != null) {
            dateString = format.format(date);
        }
        return dateString;
    }

    /**
     * 日期转字符串
     * @param date 要转换的日期
     * @return 返回日期整数 如199890604 错误返回-1
     */
    public static int parseInt(Date date) {
        String dateString = parseString(date, "yyyyMMdd");
        if (TextUtils.isEmpty(dateString)) {
            return -1;
        }
        else {
            try {
                return Integer.parseInt(dateString);
            }
            catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    /**
     * 获取date的年份
     * @param date
     * @return 返回年份 -1表示发生错误
     */
    public static int getYear(Date date) {
        return calendarGetField(Calendar.YEAR, date);
    }

    /**
     * 获取date的月份 (1-12月)
     * @param date
     * @return 返回月份 -1表示发生错误
     */
    public static int getMonth(Date date) {
        // 返回的month -1 是发生了异常 0-11 表示12个月 12表示转换时错误
        int month = calendarGetField(Calendar.MONTH, date);
        return month <= Calendar.DECEMBER && month >= Calendar.JANUARY ? month + 1 : -1;
    }

    /**
     * 获取date的日份(该日是该月中的哪天)
     * @param date
     * @return 返回日份 -1表示发生错误
     */
    public static int getDay(Date date) {
        return calendarGetField(Calendar.DAY_OF_MONTH, date);
    }

    /**
     * 获取date的日份是星期几(该日是星期几)
     * @param date
     * @return 返回星期几 -1表示发生错误
     */
    public static int getDayOfWeek(Date date) {
        return calendarGetField(Calendar.DAY_OF_WEEK, date);
    }

    /**
     * 获取date的小时
     * @param date 日期
     * @return 返回小时 -1表示发生错误
     */
    public static int getHour(java.util.Date date) {
        return calendarGetField(Calendar.HOUR_OF_DAY, date);
    }

    /**
     * 获取date的分钟
     * @param date 日期
     * @return 返回分钟 -1表示发生错误
     */
    public static int getMinute(Date date) {
        return calendarGetField(Calendar.MINUTE, date);
    }

    /**
     * 获取date的秒钟
     * @param date 日期
     * @return 返回秒钟 -1表示发生错误
     */
    public static int getSecond(Date date) {
        return calendarGetField(Calendar.SECOND, date);
    }

    public static String formatElapsedTime(int seconds) {
        return String.format(Locale.CHINA, "%02d:%02d", seconds / 60, seconds % 60);
    }

    private static int calendarGetField(int field, Date date) {
        int ret = -1;
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(date);
            ret = calendar.get(field);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    public static String formatDateStr(Calendar cal, String format) {
        SimpleDateFormat formator = new SimpleDateFormat(format);
        return formator.format(cal.getTime());
    }

    public static String formatDateStr(Date date, String format) {
        SimpleDateFormat formator = new SimpleDateFormat(format);
        return formator.format(date);
    }
    
    public static String formatDateStr(Timestamp timestamp, String format) {
        SimpleDateFormat formator = new SimpleDateFormat(format);
        return formator.format(timestamp.getTime());
    }
}