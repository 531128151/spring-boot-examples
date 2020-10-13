package com.neo.controller;


import com.neo.util.VerifyCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @Author: ZiLing.Zhao
 * @Description: 验证码
 * @Date: Created by Arthur on 2018/12/16.
 */
@Controller
@RequestMapping("/verifyCode")
@Slf4j
public class VerifyCodeController {
    /**
     * 获取校验码
     * @param response
     * @param session
     */
    @RequestMapping(value="/getVerifyCode",produces = "application/json;charset=UTF-8")
    public void getVerifyCode(HttpServletResponse response, HttpSession session) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        VerifyCodeUtil code=new VerifyCodeUtil();
        String verifyCodeValue = code.drawImg(output);
        log.info("【登录验证码】- {}", verifyCodeValue);
        // 将校验码保存到session中
        session.setAttribute("verifyCodeValue", verifyCodeValue);
        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            log.error("【异常】- {}", e);
            log.error("<--验证码前端写出.出现异常-->"+e.getMessage());
        }
    }

    @RequestMapping(value = "/test")
    public Object test(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        VerifyCodeUtil code=new VerifyCodeUtil();
        String verifyCodeValue = code.drawImg(output);
        log.info("【登录验证码】- {}", verifyCodeValue);
        return null;
    }

    public static void main(String[] args) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        VerifyCodeUtil code=new VerifyCodeUtil();
        String verifyCodeValue = code.drawImg(output);
        log.info("【登录验证码】- {}", verifyCodeValue);

        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        System.out.println( s.charAt(r.nextInt(s.length())));
    }

}
