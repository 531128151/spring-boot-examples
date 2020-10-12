package com.neo.util;


import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class MD5Util {
    protected static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f'};
    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("MD5FileUtil messagedigest初始化失败");
        }
    }


    /**
     * 跟据文件获取md5码
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileMD5String(File file) {
        FileInputStream in=null;
        try {
            in = new FileInputStream(file);
            FileChannel ch = in.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            messagedigest.update(byteBuffer);
            return bufferToHex(messagedigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 跟据文件流获取md5码
     *
     * @param fis
     * @return
     */
    public static String getFileMD5String(InputStream fis) {
        BigInteger bi = null;
        String biStr=null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            if(!CommonUtil.isEmpty(fis)){
                while ((len = fis.read(buffer)) != -1) {
                    messagedigest.update(buffer, 0, len);
                }
                byte[] b = messagedigest.digest();
                bi = new BigInteger(1, b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("【关闭资源异常】-{}", e);
                }
            }
        }
        if(CommonUtil.isEmpty(bi)){
            biStr= bi.toString(16);
        }
        return biStr;
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    public static void main(String[] args) throws IOException {
        long begin = System.currentTimeMillis();
        System.out.println("md5:" + getMD5String("111111"));
        File big = new File("D:\\dapian.mp4");
        // File big1 = new File("D:\\dapian");
        String md5 = getFileMD5String(big);
        // String md51 = getFileMD5String(big1);
        long end = System.currentTimeMillis();
        System.out.println("md5:" + md5);
        System.out.println("time:" + ((end - begin) / 1000) + "s");
        // if (md51.equals(md5)) {
        // System.out.println("They are one file!");
        // }

    }

}

