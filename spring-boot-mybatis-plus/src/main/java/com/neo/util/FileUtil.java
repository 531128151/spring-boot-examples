package com.neo.util;

/**
 *
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.UUID;

/**
 * FileUtil.java
 *
 * @version 1.0
 */
@Slf4j
public class FileUtil {

    /**
     * 将内容写入到该路径文件中
     * @param content
     * @param filePath
     * @param override 是否覆盖,false往后面追加
     */
    public static void saveAsFileWriter(String content,String filePath,boolean override) {
        FileWriter fwriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fwriter = new FileWriter(filePath, override);
            fwriter.write(content);
        } catch (IOException ex) {
            log.error("【文件写入失败】-{}", ex);
        } finally {
            try {
                if (fwriter != null) {
                    fwriter.flush();
                }
                if (fwriter != null) {
                    fwriter.close();
                }
            } catch (IOException ex) {
                log.error("【关闭文件流异常】-{}", ex);
            }
        }
    }

    /**
     * 上传文件
     * @param file
     * @param imgPath 保存路径
     * @return 文件路径(包含文件名)
     * @throws IOException
     */
    public static String upload(CommonsMultipartFile file, String imgPath) throws IOException {
        String fileName = file.getOriginalFilename();
        String[] split = fileName.split("[.]");
        String endName = split[split.length - 1].trim().toLowerCase();
        String uuidFileName = UUID.randomUUID().toString().replace("-", "") + "." + endName;
        String currentDate = DateUtil.getStringDateShort();
        String currentYear = DateUtil.getYear(currentDate);
        String currentMonth = DateUtil.getMonth(currentDate);
        String currentDay = DateUtil.getDay(currentDate);
        String path = "/" + currentYear + currentMonth + "/" + currentDay;
        upFile(file.getInputStream(), uuidFileName, imgPath + path);
        return path + "/" + uuidFileName;
    }

    /**
     * InputStream转 File类型
     * @param ins
     * @param file
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件类型
     * @return
     * @param file
     */
    public static String getFileType(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String[] split = fileName.split("[.]");
        return split[split.length - 1].trim().toLowerCase();
    }

    /**
     *
     * 单个文件上传
     * @param is
     * @param fileName
     * @param filePath
     *
     */
    public static void upFile(InputStream is, String fileName, String filePath) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File f = new File(filePath + "/" + fileName);
        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            byte[] bt = new byte[4096];
            int len;
            while ((len = bis.read(bt)) > 0) {
                bos.write(bt, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                    bos = null;
                }
                if (null != fos) {
                    fos.close();
                    fos = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
                if (null != bis) {
                    bis.close();
                    bis = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把文件写到本地的文件夹下，先生成一个UUID命名的名字返回给前台
     * @param localFileBaseUrl
     * @param file
     * @return
     */
    public static String saveFile(String localFileBaseUrl, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String[] split = fileName.split("[.]");
        String endName = split[split.length - 1].trim().toLowerCase();
        String uuidFileName = UUID.randomUUID().toString().replace("-", "") + "." + endName;
        String currentDate = DateUtil.getStringDateShort();
        String currentYear = DateUtil.getYear(currentDate);
        String currentMonth = DateUtil.getMonth(currentDate);
        String currentDay = DateUtil.getDay(currentDate);
        String path = "/" + currentYear + "/" + currentMonth + "/" + currentDay;
        upFile(file.getInputStream(), uuidFileName, localFileBaseUrl + path);
        return path + "/" + uuidFileName;
    }

    /**
     * 获得以日期隔开的文件路径
     * /2019/09/27
     * @return
     */
    public static String getDayFormatPath() {
        String currentDate = DateUtil.getStringDateShort();
        String currentYear = DateUtil.getYear(currentDate);
        String currentMonth = DateUtil.getMonth(currentDate);
        String currentDay = DateUtil.getDay(currentDate);
        String path = "/" + currentYear + "/" + currentMonth + "/" + currentDay;
        return path;
    }

    /**
     * 从本地路径读取文件到流中
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getInputStream(String path) throws FileNotFoundException {
        if (StringUtil.isEmpty(path)) {
            return null;
        }
        log.info("【下载的文件路径为】- {}", path);
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        return fileInputStream;
    }

    /**
     * 从本地路径读取文件到文件对象中
     */
    public static File getFile(String path) throws Exception {
        log.info("【下载的文件路径为】- {}", path);
        File file = new File(path);
        return file;
    }

    /**
     * 获取图片的base64流
     * @param imgFile
     * @return
     */
    public static String getImageBase64Str(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("【文件读取异常】-{}", e);
                }
            }
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 读取文件字节数组
     * @param path
     * @return
     */
    public static byte[] getFileBytes(String path) {
        InputStream inputStream = null;
        byte[] bytes = null;
        try {
            inputStream = getInputStream(path);
        } catch (FileNotFoundException e) {
            log.error("【获取文件异常】-{}", e);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            if (inputStream != null) {
                bytes = IOUtils.toByteArray(inputStream);
            }
            if(inputStream!=null) {
                inputStream.close();
            }
        } catch (IOException e) {
            log.error("【流转化为字节数组异常】-{}", e);
        }
        return bytes;
    }
}

