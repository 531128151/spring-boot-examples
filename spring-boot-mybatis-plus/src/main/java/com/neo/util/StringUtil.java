package com.neo.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
@SuppressWarnings("ALL")
@Slf4j
public class StringUtil {


    public static void main(String[] args) {

        System.out.println(humpToUnderline("createTime desc"));

    }

    public static Integer judgeTowerNumber(String towers) {
        if (StringUtil.isEmpty(towers)) {
            return 0;
        }
        String[] towerSplit = towers.split(",");
        return towerSplit.length;
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    public static String getMachine(String machine) {
        if (machine == null || ("").equals(machine)) {
            return "default";
        }
        return machine;
    }

    /**
     *
     * @title: isEmptyArray
     * @description: 判断数组是否为空
     * @param str
     * @return
     * @throws:
     */
    public static boolean isEmptyArray(String[] str) {
        if (str == null || str.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为数字
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * <p>
     * 检查字符串是否为空格（" "）, 空 ("") 或者 null.
     * </p>
     *
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank(&quot;&quot;)        = true
     * StringUtil.isBlank(&quot; &quot;)       = true
     * StringUtil.isBlank(&quot;bob&quot;)     = false
     * StringUtil.isBlank(&quot;  bob  &quot;) = false
     * </pre>
     *
     * @param str
     *            待检查的字符串,可能为null
     * @return <code>true</code> 如果字符串是空格（" "）, 空 ("") 或者 null
     */
    public static boolean isBlank(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     * 将字符串用MD5算法加密
     * </p>
     *
     * @param str
     *            待加密的字符串
     * @return MD5加密后的字符串
     */
    public static String md5(String str) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));

            byte[] b = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                int v = (int) b[i];
                v = v < 0 ? 0x100 + v : v;
                String cc = Integer.toHexString(v);
                if (cc.length() == 1) {
                    sb.append('0');
                }
                sb.append(cc);
            }

            return sb.toString();
        } catch (Exception e) {
            log.error("call md5 is invalid" + e);
        }
        return "";
    }

    /**
     * <p>
     * 将字符串（yyyy-MM-dd格式）转化为Date类型
     * </p>
     *
     * @param strDate
     *            日期字符串
     * @return "yyyy-MM-dd HH:mm:ss"格式的日期
     */
    public static Date string2Date(String strDate) {
        return string2Date(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * <p>
     * 将字符串按指定格式转换成日期
     * </p>
     *
     * @param strDate
     *            要转换的字符串
     * @param strFormat
     *            字符串的格式
     * @return 字符串所代表的日期
     */
    public static Date string2Date(String strDate, String strFormat) {
        if (isBlank(strDate) || isBlank(strFormat)) {
            return null;
        }
        try {
            SimpleDateFormat sf = new SimpleDateFormat(strFormat);
            Date date = sf.parse(strDate);
            return date;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    /**
     * Date对象转换成字符串
     *
     * @param date
     * @param strFormat
     * @return date或strFormat参数为空时返回null
     */
    public static String date2String(Date date, String strFormat) {
        if (isBlank(strFormat) || date == null) {
            return null;
        }
        try {
            SimpleDateFormat sf = new SimpleDateFormat(strFormat);
            return sf.format(date);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public static String convertArray(String[] str) {
        if (str == null) {
            return null;
        }
        String result = "";
        StringBuffer res = new StringBuffer();
        res.append("[");
        for (int i = 0; i < str.length; i++) {
            res.append(str[i]);
            res.append(",");

        }
        if (str.length > 0) {
            result = res.substring(0, res.length() - 1);
        }
        result = result + "]";
        return result;
    }

    /**
     * Date对象转换成yyyy-MM-dd HH:mm:ss格式的时间字符串
     *
     * @param date
     * @return date参数为空时返回null
     */
    public static String date2String(Date date) {
        return date2String(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Date对象转换成yyyyMMddThhmmssZ格式的时间字符串
     *
     * @param date
     * @return date参数为空时返回null
     */
    public static String date2ZTString(Date date) {
        String str = date2String(date, "yyyyMMdd hhmmss");
        if (str != null) {
            str = str.replace(' ', 'T') + "Z";
        }
        return str;
    }

    /**
     * 字符串转换成整型数字
     *
     * @param value
     * @param defaultVal
     * @return 转换出错时返回默认值
     */
    public static Integer parseInt(String value, Integer defaultVal) {
        Integer v = defaultVal;
        try {
            if (value != null && CommonUtil.isInt(value)) {
                v = Integer.parseInt(value);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return v;
    }

    /**
     * 字符串转换成整型数字
     *
     * @param value
     * @return 转换出错时返回null
     */
    public static Integer parseInt(String value) {
        return parseInt(value, null);
    }

    /**
     * 字符串转换成长整型数字
     *
     * @param value
     * @param defaultVal
     * @return 转换出错时返回默认值
     */
    public static Long parseLong(String value, Long defaultVal) {
        Long v = defaultVal;
        try {
            v = Long.parseLong(value);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return v;
    }

    /**
     * 字符串转换成长整型数字
     *
     * @param value
     * @return 转换出错时返回null
     */
    public static Long parseLong(String value) {
        return parseLong(value, null);
    }

    /**
     *
     * @Title: @Description: 字符串转换成数字型数组 @param @param value @param @return
     *         设定文件 @return 返回类型 @throws
     */
    public static long[] parseArrayLong(String value) {
        if (value != null && value.length() > 0) {
            String[] strs = value.split(",");
            long[] rs = new long[strs.length];
            for (int i = 0; i < strs.length; i++) {
                rs[i] = parseLong(strs[i]);
            }
            return rs;
        }
        return null;
    }

    /**
     * 字符串转换成单精度浮点数字
     *
     * @param value
     * @param defaultVal
     * @return 转换出错时返回默认值
     */
    public static Float parseFloat(String value, Float defaultVal) {
        Float v = defaultVal;
        try {
            v = Float.parseFloat(value);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return v;
    }

    /**
     * 字符串转换成单精度浮点数字
     *
     * @param value
     * @return 转换出错时返回null
     */
    public static Float parseFloat(String value) {
        return parseFloat(value, null);
    }

    /**
     * 字符串转换成双精度浮点数字
     *
     * @param value
     * @param defaultVal
     * @return 转换出错时返回默认值
     */
    public static Double parseDouble(String value, Double defaultVal) {
        Double v = defaultVal;
        try {
            v = Double.parseDouble(value);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return v;
    }

    /**
     * 字符串转换成双精度浮点数字
     *
     * @param value
     * @return 转换出错时返null
     */
    public static Double parseDouble(String value) {
        return parseDouble(value, null);
    }

    /**
     * 字符串转换成布尔型对象
     *
     * @param value
     * @return 转换出错时返回null
     */
    public static Boolean parseBoolean(String value) {
        return Boolean.parseBoolean(value);
    }

    /**
     *
     * @Title: @Description: 对mysql的查询中，有特殊字符：_ % ' \ 都需要调用该方法转义 @param @param
     *         str @param @return 设定文件 @throws
     */
    public static String mysqlConvertStr(String str) {
        if (!StringUtils.isBlank(str)) {
            str = StringUtils.deleteWhitespace(str);
            if (StringUtils.contains(str, '\\')) {
                str = StringUtils.replace(str, "\\", "\\\\");
            }
            if (StringUtils.contains(str, '_')) {
                str = StringUtils.replace(str, "_", "\\_");
            }
            if (StringUtils.contains(str, "'")) {
                str = StringUtils.replace(str, "'", "\\'");
            }
            return str;
        }
        return null;
    }

    /*
     * 获得当天的凌晨时间
     */
    public static Date getTodayMorning() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.add(Calendar.DATE, -1);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int nDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month - 1, nDay, 23, 59, 59);
        Date dateInfo = calendar.getTime();
        return dateInfo;
    }

    /*
     * 除法运算
     */
    public static double divide(double firstNum, double secondNum) {
        double result = 0;
        if (firstNum != 0 && secondNum != 0) {
            BigDecimal first = BigDecimal.valueOf(firstNum);
            BigDecimal second = BigDecimal.valueOf(secondNum);
            result = first.divide(second, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return result;
    }

    /*
     * 进行单位的换算
     */
    public static String selectUnit(double num, String type) {
        final double radix = 1024;
        int index = -1;
        String[] unit = { "B", "K", "M", "G", "T", "P", "E" };
        for (int i = 0; i < unit.length; i++) {
            if (type.trim().equalsIgnoreCase(unit[i])) {
                index = i;
            }
        }

        if (index == -1) {
            return num + type;
        }

        String result = num + unit[index];
        while (num >= radix && index < 6) {
            num = divide(num, radix);
            index++;
            result = num + unit[index];
        }
        return result;
    }

    public static String selectDate(String str, String type) {
        double num = Double.valueOf(str);
        final double radix = 60;
        int index = -1;
        String[] unit = { "s", "m", "h" };
        for (int i = 0; i < unit.length; i++) {
            if (type.trim().equalsIgnoreCase(unit[i])) {
                index = i;
            }
        }
        if (index == -1) {
            return num + type;
        }
        String time = num + unit[index];
        while (num >= radix && index < 2) {
            num = divide(num, radix);
            index++;
            time = num + unit[index];
        }
        return time;
    }

    // 把字符串转换为数字
    public static int parse2Int(String value) {
        value = value.replaceAll("[^0-9]", "").trim();
        if ("".equals(value)) {
            return 0;
        }
        return Integer.valueOf(value).intValue();
    }

    public static float parset2Float(String value) {
        value = value.replaceAll("[^0-9.]", "");
        if ("".equals(value)) {
            return 0;
        }
        return Float.parseFloat(value);
    }

    /**
     * 验证邮箱地址是否正确
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        flag = matcher.matches();
        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return [0-9]{5,9}
     */
    public static boolean isMobileNO(String mobiles) {
        boolean flag = false;
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        flag = m.matches();
        return flag;
    }

    public static String getStringNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    public static boolean isIndexOfTtl(String line) {
        if (line.toLowerCase().indexOf("ttl") != -1) {
            return true;
        }
        return false;
    }

    /**
     * @title: checkResult
     * @description:
     * @param result
     * @return
     * @throws:
     */
    public static String checkResult(String[] result) {
        if (result.length >= 3) {
            return "false";
        } else {
            return "true";
        }
    }

    // 判断是数字
    public static boolean isNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int getCountOrTen(int count, int start) {
        if ((count - start) < 10) {
            return (count - start);
        }
        return 10;
    }

    /**
     * 生成不重复的字符串
     *
     * @param preStr
     *            前缀
     * @return
     */
    public synchronized static String createUniqueStr(String preStr) {
        return preStr + System.currentTimeMillis();
    }

    /**
     * 生成随即字符串
     *
     * @param length
     *            表示生成字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 去掉str前方的某个字符,例如：去掉字符串前边所有的0
     *
     * @param str
     * @param charStr
     * @return
     */
    public static String replaceChar(String str, char charStr) {
        if(!isBlank(str)) {
            char[] strs = str.toCharArray();
            int index = 0;
            for (int i = 0, len = strs.length; i < len; i++) {
                if(charStr != strs[i]) {
                    index = i ;
                    break;
                }
            }
            return str.substring(index, str.length());
        }
        return "";
    }
    /**
     * 去除字符串中的换行符空格等等
     * @param str
     * @return
     */
    public static String replaceBlank(String str){
        String result = "";
        if(str != null){
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher matcher = p.matcher(str);
            result = matcher.replaceAll("");
        }
        return result;
    }

    public static String[] getStartTower(String towerInfo){
        String[] towers=new String[2];
        String[] tosers = towerInfo.split("-");
        return towers;
    }

    /**
     * 获取#0001,#0002,#0003,#0004,#0005,#0006的第一个杆塔#0001
     * @param towerInfo
     * @return
     */
    public static String getStartTowerByString(String towerInfo){
        if (StringUtils.isEmpty(towerInfo)) {
            return "";
        } else {
            String[] split = towerInfo.split(",");
            return split[0];
        }
    }

    /**
     * 获取#0001,#0002,#0003,#0004,#0005,#0006的最后一个杆塔#0006
     * @param towerInfo
     * @return
     */
    public static String getEndTowerByString(String towerInfo){
        if (StringUtils.isEmpty(towerInfo)) {
            return "";
        } else {
            String[] split = towerInfo.split(",");
            return split[split.length - 1];
        }
    }


    public static String DDtoDMS(Double d){

        String[] array=d.toString().split("[.]");
        String degrees=array[0];//得到度
        if(degrees.length()==1){
            degrees="0"+degrees;
        }

        Double m=Double.parseDouble("0."+array[1])*60;
        String[] array1=m.toString().split("[.]");
        String minutes=array1[0];//得到分
        if(minutes.length()==1){
            minutes="0"+minutes;
        }

        Double s=Double.parseDouble("0."+array1[1])*60;
        String[] array2=s.toString().split("[.]");
        String seconds=array2[0];//得到秒
        if(seconds.length()==1){
            seconds="0"+seconds;
        }
        return degrees+"°"+minutes+"′"+seconds+"\" ";
    }

    /**
     * 将驼峰命名改为下划线分割
     * @param para
     * @return
     */
    public static String humpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;
        if (!para.contains("_")) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

}
