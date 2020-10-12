package com.neo.util;

/**
 *
 */

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public final class CommonUtil {
    /**
     * 正则表达式，只能包含字母、数字及下划线，并且不以下划线开头或者结尾
     */
    public static String PATTERN_DIGCHARPATTERN = "^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$";

    // email地址正则表达式
    public static String PATTERN_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+ ([-.]\\w+)*";

    // 电话号码正则表达式
    public static String PATTERN_TELPHONE = "\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d(3)-\\d(8)";

    // 手机号码 正则表达式
    public static String PATTERN_MOBILPHONE = "^[1][3,5]+\\d{9}";

    // IP地址正则表达式
    public static String PATTERN_IP = "\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}";

    /**
     * 字符串是否满足，字符、字母及下划线的组成，且开头和结尾不能为下划线
     *
     * @param str
     * @return
     */
    public static boolean isDigcharpattern(String str) {
        if (isEmpty(str)) {
            return false;
        }

        return isRightPattern(str, PATTERN_DIGCHARPATTERN);
    }

    /**
     * 比较两个字符串是否同为null或者值是否相同
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isEqual(String src, String dst) {
        if (src == null && dst == null) {
            return true;
        }

        if (src != null && dst != null && src.equals(dst)) {
            return true;
        }
        return false;
    }

    /**
     * 字符串是否符合email地址格式
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {

        if (isEmpty(str)) {
            return false;
        }

        return isRightPattern(str, PATTERN_EMAIL);
    }

    /**
     * 字符串是否符合电话号码格式
     *
     * @param str
     * @return
     */
    public static boolean isTelphoneNumber(String str) {

        if (isEmpty(str)) {
            return false;
        }

        return isRightPattern(str, PATTERN_TELPHONE);
    }

    /**
     * 字符串是否符合手机号码格式
     *
     * @param str
     * @return
     */
    public static boolean isMobilNumber(String str) {

        if (isEmpty(str)) {
            return false;
        }

        return isRightPattern(str, PATTERN_MOBILPHONE);
    }

    /**
     * 字符串是否符合ip地址格式
     *
     * @param str
     * @return
     */
    public static boolean isIP(String str) {

        if (isEmpty(str)) {
            return false;
        }

        return isRightPattern(str, PATTERN_IP);
    }

    /**
     * 判断一个字符串是否为端口类型，端口范围{0-65536}，当传入值大于0小于65536时返回true
     *
     * @param str
     * @return
     */
    public static boolean isPort(String str) {
        if (isEmpty(str)) {
            return false;
        }

        try {
            int value = Integer.parseInt(str.trim());
            if (value < 0 || value > 65535) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断列表是否空，当null或者个数为零时返回true
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List<?> list) {
        return (list == null || list.size() == 0);
    }

    /**
     * 判断Map是否空，当null或者个数为零时返回true
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.size() == 0);
    }

    /**
     * 判断Set是否空，当null或者个数为零时返回true
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(Set<?> set) {
        return set == null || set.size() == 0;
    }

    /**
     * 判断字符串是否空，当null，值为""后者值为"null"或者"NULL"返回true
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return (value == null || "".equals(value.trim()) || "null".equals(value.trim().toLowerCase()));
    }

    /**
     * 判断对象是否为空，当null时返回true
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(Object value) {
        return (value == null);
    }

    /**
     * 判断Long型对象是否空，当为null或者值小于等于0时返回true
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(Long value) {
        return (value == null || value.longValue() <= 0);
    }

    /**
     * 判断Integer型对象是否空，当为null或者值小于等于0时返回true
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(Integer value) {
        return (value == null || value.intValue() <= 0);
    }

    /**
     * 判断字符数组是否空，当为null或者元素个数等于0时返回true
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String[] arrValue) {
        return (arrValue == null || arrValue.length == 0);
    }

    /**
     * 判断对象数组是否空，当为null或者元素个数等于0时返回true
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(Object[] arrObject) {
        return (arrObject == null || arrObject.length == 0);
    }

    /**
     * 日期比较，比较传入的两个时间，日期是否相同，日期格式"yyyy-MM-dd"
     *
     * @param sysDate
     * @param dbDate
     * @return
     */
    public static boolean isDate(Calendar sysDate, Date dbDate) {
        // 判断传入的参数是否为空
        if (sysDate == null || dbDate == null) {
            return false;
        }

        SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd");

        // 转化为yyyy-MM-dd格式的日期字符串
        String dbdateStr = frm.format(dbDate);
        // 转化为yyyy-MM-dd格式的日期字符串
        String sysdateStr = frm.format(sysDate.getTime());
        // 比较两个字符串是否相等
        if (sysdateStr.equals(dbdateStr)) {
            return true;
        }
        return false;
    }

    /**
     * 时间比较，比较传入的两个时间，sysDate是否大于dbDate，大于返回true
     *
     * @param sysDate
     * @param dbDate
     * @return
     */
    public static boolean lessSysDateTime(Calendar sysDate, Date dbDate) {
        // 判断传入的参数是否为空
        if (sysDate == null || dbDate == null) {
            return false;
        }

        if (sysDate.getTime().getTime() > dbDate.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 判断指定的字符串是否为指定的正则表达式格式.
     *
     * @param str
     *            指定的字符串str
     * @param pattern
     *            指定的正则表达式格式
     * @return 当符合条件的时候返回true, 否则返回false
     */
    public static boolean isRightPattern(String str, String pattern) {
        Pattern patternRegex = Pattern.compile(pattern);
        Matcher matcher = patternRegex.matcher(str);
        return matcher.matches();
    }

    /**
     * 将当前时间按照制定类型偏移一段值再转化为制定格式
     *
     * @param pattern
     *            指定格式的时间格式示例 :"yyyy-MM-dd HH:mm:ss"
     * @param field
     *            指定的日历属性字段，示例 :Calendar.SECOND 按秒编移
     * @param timeLapse
     *            指定的偏移移数值
     * @return String 制定pattern格式的字符串
     */
    public static String formatDateString(String pattern, int field, int timeLapse) {
        // 当前日历声明
        Calendar calendar = Calendar.getInstance();
        // 时间偏移，
        calendar.add(field, timeLapse);
        SimpleDateFormat frm = new SimpleDateFormat(pattern);
        String dateStr = frm.format(calendar.getTime());
        return dateStr;
    }

    /**
     * 将时间对象转化为指定格式的字符串
     *
     * @param pattern
     *            指定格式的时间格式示例 :"yyyy-MM-dd HH:mm:ss"
     * @param date
     *            日期
     * @return String [指定格式的时间格式字符串]
     */
    public static String dateString(String pattern, Date date) {
        SimpleDateFormat frm = new SimpleDateFormat(pattern);
        String dateStr = frm.format(date.getTime());
        return dateStr;
    }

    /**
     * 将字符串进行MD5加密，返回MD5串
     *
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }

    /**
     * 判断传输的分页参数和页码是否同时为空
     *
     * @param allData
     * @param page
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws
     *                [违例类型] [违例说明]
     */
    public static boolean pageParmsIsEmpty(String allData, String page) {
        if (isEmpty(allData) && isEmpty(page)) {
            return true;
        }
        return false;
    }

    /**
     * 判断传入的参数是否为整数,可以为负数
     *
     * @param str
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws
     *                [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isInt(String str) {
        try {
            new Integer(str);

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 将Long值转化为yyyy-MM-dd HH:mm:ss格式的时间字符串
     *
     * @param date
     * @return
     */
    public static String datetostring24(Long date) {

        return dateString("yyyy-MM-dd HH:mm:ss", new Date(date));
    }

    /**
     * 将英文双引号转化为中文双引号
     *
     * @param paramString
     * @return String []
     * @see [类、类#方法、类#成员]
     */
    public static String enStringToZhString(String paramString) {

        return paramString.replaceAll("\"", "”");
    }

    /**
     * 判断是否是带小数点的浮点型数值
     *
     * @see [相关类/方法]
     */
    public static boolean isDouble(String str) {
        if (isEmpty(str)) {
            return false;
        }
        if (str.matches("^[0-9]+[.]?[0-9]*$")) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否为正整数
     *
     * @param str
     * @return
     */
    public static boolean isInt2(String str) {
        if (isEmpty(str)) {
            return false;
        }
        if (str.trim().matches("\\d+")) {
            return true;
        }
        return false;
    }

    /**
     * 返回当前时间intervalDays天之前的时间字符串，格式为yyyy-MM-dd HH:mm:ss
     *
     * @param intervalDays
     * @return
     */
    public static String getTodayBefore(int intervalDays) {
        return formatDateString("yyyy-MM-dd HH:mm:ss", Calendar.DATE, -intervalDays);
    }

    /**
     * 返回当前时间intervalDays天之后的日期字符串，格式为yyyy-MM-dd
     *
     * @param intervalDays
     * @return
     */
    public static String getTodayAfter(int intervalDays) {
        return formatDateString("yyyy-MM-dd", Calendar.DATE, intervalDays);
    }

    /**
     * 获取一个容器的元素个数
     *
     * @param list
     *            Collection类型
     * @return
     */
    public static int getColLen(@SuppressWarnings("rawtypes") Collection list) {
        return (list == null) ? 0 : list.size();
    }

    /**
     * 判断是否包含另一个是否串,res是否包含des
     *
     * @param res
     * @param des
     * @return boolean [返回类型说明]
     */
    public static boolean isStringContainAntherString(String res, String des) {
        if (isEmpty(res) || isEmpty(des)) {
            return false;
        }

        if (res.indexOf(des.trim()) > -1) {
            return true;
        }

        return false;

    }

    /**
     * 将字符串数组中的所有字符串通过,进行连接为一个字符串
     *
     * @param strArray
     * @return String [返回类型说明]
     */
    public static String getStringFromStringArray(String[] strArray) {
        if (isEmpty(strArray)) {
            return null;
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < strArray.length; i++) {
            if (i == strArray.length - 1) {
                result.append(strArray[i]);
                break;
            }
            result.append(strArray[i]).append(",");
        }

        return result.toString();
    }

    /**
     * 比较两个格式为yyyy-MM-dd HH:mm:ss的字符串标识的时间大小 firstDate大于secondDate，返回1,
     * firstDate小于secondDate，返回-1 相等返回0
     *
     * @param firstDate
     * @param serondDate
     * @return
     */
    public static int compareDate(String firstDate, String secondDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(firstDate);
            Date dt2 = df.parse(secondDate);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;

    }

    /**
     * 比较两个格式为时间大小 firstDate大于secondDate，返回1, firstDate小于secondDate，返回-1 相等返回0
     *
     * @param firstDate
     * @param serondDate
     * @return
     */
    public static int compareDate(Date firstDate, Date serondDate) {
        try {
            if (firstDate.getTime() > serondDate.getTime()) {
                return 1;
            } else if (firstDate.getTime() < serondDate.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 将格式为yyyy-MM-dd HH:mm:ss字符串转化为时间对象
     *
     * @param date
     * @return
     */
    public static Date stringParseDate(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将整形数组中的所有元素通过,进行连接为一个字符串
     *
     * @param intArray
     * @return String [返回类型说明]
     */
    public static String intArrayToString(int[] intArray) {
        if (CommonUtil.isEmpty(intArray)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < intArray.length; i++) {
            if (i == intArray.length - 1) {
                sb.append(intArray[i]);
                break;
            }
            sb.append(intArray[i]).append(",");
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否为有效的long类型
     *
     * @param value
     * @return [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws
     *                [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isLong(String value) {
        if (isEmpty(value)) {
            return false;
        }
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 保留两位小数
     * @param value
     * @return
     */
    public static Float ParseTowPlacesFloat(Float value) {
        return ParseNPlacesFloat(value, 2);
    }

    /**
     * 保留三位小数
     * @param value
     * @return
     */
    public static Float ParseThreePlacesFloat(Float value) {
        return ParseNPlacesFloat(value, 3);
    }

    /**
     * 保留几位小数
     * @param value
     * @param num
     * @return
     */
    public static Float ParseNPlacesFloat(Float value, int num) {
        BigDecimal b = new BigDecimal(value);
        value = b.setScale(num, BigDecimal.ROUND_HALF_UP).floatValue();
        return value;
    }
    /**
     * 获取文件md5值
     *
     * @param file inputFile
     * @return md5Value
     */
    public static String getFileMD5(File file) {
        MessageDigest digest;
        FileInputStream in;
        int length = 1024;
        byte[] buffer = new byte[length];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, length)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 获取缩略图
     *
     * @param srcImg       inputImg
     * @param imgMaxWidth  缩略图的最大宽度值
     * @param imgMaxHeight 缩略图的最大高度值
     * @return 图片的base64格式
     */
    public static String getImgThumbnail(BufferedImage srcImg, int imgMaxWidth, int imgMaxHeight) {
        try {
            int realWidth = srcImg.getWidth();
            int realHeight = srcImg.getHeight();
            int[] resize = getResize(realWidth, realHeight, imgMaxWidth, imgMaxHeight);

            int width = resize[0];
            int height = resize[1];
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            img.getGraphics().drawImage(srcImg, 0, 0, width, height, null);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(img, "JPG", outputStream);
            byte[] bytes = outputStream.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(bytes);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 获取缩略图,拼接base64前缀
     *
     * @param srcImg       inputImg
     * @param imgMaxWidth  缩略图的最大宽度值
     * @param imgMaxHeight 缩略图的最大高度值
     * @return 图片的base64格式
     */
    public static String getImgThumbnail1(BufferedImage srcImg, int imgMaxWidth, int imgMaxHeight,String endName) {
        try {
            int realWidth = srcImg.getWidth();
            int realHeight = srcImg.getHeight();
            int[] resize = getResize(realWidth, realHeight, imgMaxWidth, imgMaxHeight);

            int width = resize[0];
            int height = resize[1];
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            img.getGraphics().drawImage(srcImg, 0, 0, width, height, null);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(img, "JPG", outputStream);
            byte[] bytes = outputStream.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();

            StringBuffer sb = new StringBuffer();
            if("png".equals(endName.toLowerCase())){
                sb.append("data:image/png;base64,");
            }else if("jpg".equals(endName.toLowerCase())||"jpeg".equals(endName.toLowerCase())){
                sb.append("data:image/jpeg;base64,");
            }else if("gif".equals(endName.toLowerCase())){
                sb.append("data:image/gif;base64,");
            }else if("icon".equals(endName.toLowerCase())){
                sb.append("data:image/x-icon;base64,");
            }
            sb.append(encoder.encode(bytes));
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 获取缩略图的自适应尺寸
     *
     * @param realWidth    原图宽度
     * @param realHeight   原图高度
     * @param imgMaxWidth  缩略图可取最大宽度
     * @param imgMaxHeight 缩略图可取最大高度
     * @return [缩略图宽，缩略图高]
     */
    private static int[] getResize(int realWidth, int realHeight, int imgMaxWidth, int imgMaxHeight) {
        float imgWidthHeightRate = imgMaxWidth / (float) imgMaxHeight;
        float realRate = realWidth / (float) realHeight;
        int[] ret = new int[2];
        if (realRate > imgWidthHeightRate) {
            ret[0] = imgMaxWidth;
            ret[1] = Math.round(imgMaxWidth / realRate);
        } else {
            ret[1] = imgMaxHeight;
            ret[0] = Math.round(imgMaxHeight * realRate);
        }
        return ret;
    }

}
