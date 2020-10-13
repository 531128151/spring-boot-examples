package com.neo.codeInfo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neo.codeInfo.entity.TbSysCodeinfoTestimportexcel;
import com.neo.codeInfo.service.ITbSysCodeinfoTestimportexcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 字典 前端控制器
 * </p>
 *
 * @author wupeng
 * @since 2020-10-13
 */
@Slf4j
@RestController
@RequestMapping("/codeInfo")
public class TbSysCodeinfoTestimportexcelController {
    @Autowired
    private ITbSysCodeinfoTestimportexcelService iTbSysCodeinfoTestimportexcelService;

    @GetMapping("/testPlus")
    public Object get(){
        QueryWrapper<TbSysCodeinfoTestimportexcel> wrapper = new QueryWrapper<>();
        wrapper.eq("sysCodeValue","1005101106101");
        wrapper.like("sysCodeName","一般:过电压保护器上引线缺失");
        List<TbSysCodeinfoTestimportexcel> list = iTbSysCodeinfoTestimportexcelService.list(wrapper);
        log.info(list.get(0).getSysCodeName());
        return list;
    }


}
