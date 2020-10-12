package com.neo.util;


import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("ALL")
@Slf4j
public class DateUtil {

    private static final Random jjj = new Random();

    public static void main(String[] args) {
        System.out.println(getCurrentYear());
        System.out.println(getCurrentMonth());
    }

    /**
     * 大-小    计算两个时间天数差
     * @param annualplanstarttine
     * @param annualplanendtine
     * @return
     */
    public static Integer getDifferenceFromTwoDate(String annualplanstarttine, String annualplanendtine) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        int i = 0;
        try {
            Date date2 = format.parse(annualplanstarttine);
            Date date = format.parse(annualplanendtine);

            i = differentDaysByMillisecond(date, date2);
        } catch (ParseException e) {
            log.error("【计算两个日期的时间差出错】-{}", e);
        }
        return i;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    /**
     * 给定的时间是否在第二个时间之前，也即一个时间是否过期的比较方法
     * @param beforeTime
     * @param nowTime
     * @return
     */
    public static boolean beforeTheEnd(String beforeTime, String nowTime) {
        DateFormat df;
        if (nowTime.length() >= 14) { // 带时分秒时间比较
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            beforeTime = getStringDate();
        } else {
            df = new SimpleDateFormat("yyyy-MM-dd");
        }


        try {
            Date dt1 = df.parse(beforeTime);
            Date dt2 = df.parse(nowTime);
            if (dt1.getTime() >= dt2.getTime()) {
                return true;
            }
        } catch (ParseException e) {
            log.error("【比较两个时间异常】- {}", e);
        }
        return false;
    }

    /**
     * 给定的时间是否在第二个时间之前，也即一个时间是否过期的比较方法
     * @param beforeTime
     * @param nowTime
     * @return
     */
    public static boolean beforeTheEndDateFormat(Date beforeTime, Date nowTime) {
        if (beforeTime.getTime() >= nowTime.getTime()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取某个月的天数
     * @param strDate 2012-01
     * @return 31
     */
    public static Integer getDaysFromMonth(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            log.error("【日期解析错误】- {}", e);
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int days1 = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days1;
    }


    /**
     * 当年第一天
     *
     * @param date
     *            日期
     * @return 天
     * @throws Exception
     *             异常
     */
    public static String getThisYear(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy");
        Date time = null;
        String dates = null;
        try {
            time = format3.parse(date);
            String time1 = format3.format(time);
            Date startTime = format.parse(time1 + "-01-01");
            dates = format.format(startTime);
        } catch (ParseException e) {
            log.error("【日期解析异常】-{}", e);
        }
        return dates;
    }

    /**
     * 当年最后一天
     *
     * @param date
     *            日期
     * @return 天
     * @throws Exception
     *             异常
     */
    public static String getThisYearLastDay(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy");
        Date time = null;
        String dates = null;
        try {
            time = format3.parse(date);
            String time1 = format3.format(time);
            Date startTime = format.parse(time1 + "-12-31");
            dates = format.format(startTime);
        } catch (ParseException e) {
            log.error("【日期解析异常】-{}", e);
        }
        return dates;
    }



    /**
     * 当月第一天
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getFirstDayToMonth(String date){
        // 获取截止当前天数
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date nowDay = null;
        try {
            nowDay = format.parse(date);
        } catch (ParseException e) {
            log.error("【时间转化异常】- {}", e);
        }
        String time = format.format(nowDay);
        String firstDay = time + "-01";
        return firstDay;
    }


    /*
     * /**
     *
     * @title: getNowDate
     *
     * @description:
     *
     * @return yyyy-MM-dd HH:mm:ss
     *
     * @throws:
     *
     * @date: 2013-10-23
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    public static String dateToString(Date date, String format) {

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * @return yyyy-MM-dd
     * @title: getNowDateShort
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss
     * @title: getStringDate
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * @return 生成以年月日时分秒为编码的序列
     * @title: getStringDate
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static Long getStringDateCode() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return new Long(dateString);
    }

    /**
     * @return yyyy-MM-dd
     * @title: getStringDateShort
     * @description:
     * @throws:
     * @date: 2013-10-23
     */

    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * @return HH:mm:ss
     * @title: getTimeShort
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * @param strDate
     * @return yyyy-MM-dd HH:mm:ss
     * @title: strToDateLong
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static Date strToDateLong(String strDate) {
        if (StringUtil.isEmpty(strDate)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * @param dateDate
     * @return yyyy-MM-dd HH:mm:ss
     * @title: dateToStrLong
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 格式化时间
     *
     * @param dateStr
     * @return
     */
    public static String dateStrFormt(String dateStr) {
        String date = "";
        if (!StringUtil.isEmpty(dateStr)) {
            String[] data = dateStr.split(" ");
            date = data[0];
        }
        return date;
    }

    public static String dateToZHStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * @param dateDate
     * @return yyyy-MM-dd
     * @title: dateToStr
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * @param dateDate
     * @return yyyy-MM-dd
     * @title: dateToStr
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String dateToStr2(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }


    /**
     * @param strDate
     * @return
     * @title: strToDate
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static Date strToDate(String strDate) {
        if (StringUtil.isEmpty(strDate)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * @return
     * @title: getNow
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * @param day
     * @return
     * @title: getLastDate
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static Date getLastDate(long day) {
        Date date = new Date();
        long date_3_hm = date.getTime() - 3600000 * 24 * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }

    /**
     * @return
     * @title: getStringToday
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * @return
     * @title: getHour
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    /**
     * @return
     * @title: getTime
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }

    /**
     * @return
     * @title: getCurrentDay
     * @description: 返回当前的DAY
     * @throws:
     * @date: 2013-11-21
     */
    public static String getCurrentDay() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(8, 10);
        return min;
    }

    public static String getDay(String strdate) {

        String min;
        min = strdate.substring(8, 10);
        return min;
    }

    public static String getMonth(String strdate) {

        String min;
        min = strdate.substring(5, 7);
        return min;
    }

    public static String getYear(String strdate) {
        String min;
        min = strdate.substring(0, 4);
        return min;
    }

    /**
     * @return
     * @title: getCurrentMonth
     * @description: 返回当前的MONTH
     * @throws:
     * @date: 2013-11-21
     */
    public static String getCurrentMonth() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(5, 7);
        return min;
    }


    /**
     * @return
     * @title: getCurrentYear
     * @description: 返回当前的year
     * @throws:
     * @date: 2013-11-21
     */
    public static String getCurrentYear() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(0, 4);
        return min;
    }

    /**
     * @param sformat
     * @return
     * @title: getUserDate
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * @param st1
     * @param st2
     * @return
     * @title: getTwoHour
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getTwoHour(String st1, String st2) {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0])) {
            return "0";
        } else {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
            if ((y - u) > 0) {
                return y - u + "";
            } else {
                return "0";
            }
        }
    }

    /**
     * @param sj1
     * @param sj2
     * @return
     * @title: getTwoDay
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * @param sj1
     * @param jj
     * @return
     * @title: getPreTime
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * @param nowdate
     * @param delay
     * @return
     * @title: getNextDay
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = strToDate(nowdate);
            if (d != null) {
                long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
                d.setTime(myTime * 1000);
                mdate = format.format(d);
            }
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @param ddate
     * @return
     * @title: isLeapYear
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static boolean isLeapYear(String ddate) {
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0) {
            return true;
        } else if ((year % 4) == 0) {
            if ((year % 100) == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * @param str
     * @return
     * @title: getEDate
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }

    /**
     * @param dat
     * @return
     * @title: getEndDateOfMonth
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
        String str = dat.substring(0, 8);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "30";
        } else {
            if (isLeapYear(dat)) {
                str += "29";
            } else {
                str += "28";
            }
        }
        return str;
    }

    /**
     * @param date1
     * @param date2
     * @return
     * @title: isSameWeekDates
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {

            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return
     * @title: getSeqWeek
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1) {
            week = "0" + week;
        }
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;
    }

    /**
     * @param date1
     * @param date2
     * @return
     * @title: getDays
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || "".equals(date1)) {
            return 0;
        }
        if (date2 == null || "".equals(date2)) {
            return 0;
        }
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = 0;
        if (date != null && mydate != null) {
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        }
        return day;
    }

    /**
     * @param k
     * @return
     * @title: getNo
     * @description: 使用日期加随机数产生NO
     * @throws:
     * @date: 2013-10-23
     */
    public static String getNo(int k) {
        return getUserDate("yyyyMMddhhmmss") + getRandom(k);
    }

    /**
     * @param i
     * @return
     * @title: getRandom
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static String getRandom(int i) {
        // int suiJiShu = jjj.nextInt(9);
        if (i == 0) {
            return "";
        }
        String jj = "";
        for (int k = 0; k < i; k++) {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }

    /**
     * @param date
     * @return
     * @title: RightDate
     * @description:
     * @throws:
     * @date: 2013-10-23
     */
    public static boolean RightDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ;
        if (date == null) {
            return false;
        }
        if (date.length() > 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            sdf.parse(date);
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    /*
     * @title: GetDay
     *
     * @description: 获取date1-date2两个日期直接的差值（按天计算）
     *
     * @param date1 日期
     *
     * @param date2 日期
     *
     * @return 天数
     *
     * @date: 2014-02-19
     */
    public static int getDayNum(Date date1, Date date2) {
        long day = 24L * 60L * 60L * 1000L;
        Long time1 = null;
        Long time2=null;
        if(!CommonUtil.isEmpty(date1)) {
            time1 = date1.getTime();
        }
        if(!CommonUtil.isEmpty(date2)) {
            time2 = date2.getTime();
        }


        return (int) ((time1 - time2) / day);
    }

    /**
     * 使用默认连接符连接年月日（默认为“-”连接）
     *
     * @param year
     * @param month
     * @param day
     * @return
     * @title: unionDate
     * @description:
     * @throws:
     * @date: 2014-6-6
     */
    public static String unionDate(String year, String month, String day) {
        // 使用默认连接
        return unionDate(year, month, day, null);
    }

    /**
     * 使用指定连接符进行连接
     *
     * @param year
     * @param month
     * @param day
     * @param union
     * @return
     * @title: unionDate
     * @description:
     * @throws:
     * @date: 2014-6-6
     */
    public static String unionDate(String year, String month, String day, String union) {
        StringBuilder sb = new StringBuilder();
        year = (year == null) ? "0000" : year;
        month = (month == null) ? "00" : month;
        day = (day == null) ? "00" : day;
        union = (union == null) ? "-" : union;// 默认使用-连接
        return sb.append(year).append(union).append(month).append(union).append(day).toString();
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取两个时间的相距分钟数
     * @param dt1
     * @param dt2
     * @return
     */
    public static Long getTimeDiffMinute(String dt1, String dt2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long minCount = 0L;
        try {
            Date date1 = format.parse(dt1);
            Date date2 = format.parse(dt2);
            long time = date2.getTime() - date1.getTime();
            minCount = time / 1000 / 60;
        } catch (Exception e) {
        }
        return minCount;
    }


    /**
     * 获取两个时间的分钟数
     * @param starttime
     * @param endtime
     * @return
     */
    public static Integer getDifferenceMinutesFromTwoDate(String starttime, String endtime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从对象中拿到时间
        long start = 0;
        long end = 0;
        try {
            start = df.parse(starttime).getTime();
            end = df.parse(endtime).getTime();
            long diff = (end - start) / 1000 / 60;
            return Math.toIntExact(diff);
        } catch (ParseException e) {
            log.error("【计算两个字符串类型的分钟差异常】- {}", e);
        }
        return null;
    }

    /**
     * 获取两个时间相差秒数
     * @param starttime
     * @param endtime
     * @return
     */
    public static Integer getDifferenceSecondsFromTwoDate(String starttime, String endtime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从对象中拿到时间
        long start = 0;
        long end = 0;
        try {
            start = df.parse(starttime).getTime();
            end = df.parse(endtime).getTime();
            long diff = (end - start) / 1000;
            return Math.toIntExact(diff);
        } catch (ParseException e) {
            log.error("【计算两个字符串类型的分钟差异常】- {}", e);
        }
        return null;
    }

    /**
     * 获取两个时间相差秒数
     * @param starttime
     * @param endtime
     * @return
     */
    public static Integer getDifferenceSecondsFromTwoDate(Date starttime, Date endtime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从对象中拿到时间
        long start = 0;
        long end = 0;
        start = starttime.getTime();
        end = endtime.getTime();
        long diff = (end - start) / 1000;
        return Math.toIntExact(diff);
    }
}

