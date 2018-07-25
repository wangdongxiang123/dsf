package com.onefun.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.onefun.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.onefun.service.MsgService;
import com.onefun.util.JSONResult;
import com.onefun.entity.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *code is far away from bug with the animal protecting
 *　　
 *   @description : Msg 控制器
 *   ---------------------------------
 * 	 @author Lin_huanwen123
 *   @since 2018-07-24
 */
@RestController
@Api(value="/msg", description="Msg 控制器")
@RequestMapping("/msg")
public class MsgController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(MsgController.class);

    @Autowired
    public MsgService msgService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : Lin_huanwen123
     * @since : Create in 2018-07-24
     */
    @GetMapping("/getMsgList")
    @ApiOperation(value="获取分页列表", notes="根据条件、页大小、页数获取分页列表")
    @ApiImplicitParams(
    {  
        @ApiImplicitParam(name = "length", paramType="query", value = "页大小", dataType="integer", required = true),
    	@ApiImplicitParam(name = "pageNo", paramType="query", value = "页数", dataType="integer", required = true)
    })
    public JSONResult<Map<String, Object>> getMsgList(Msg param,Integer length,Integer pageNo) throws Exception{
            JSONResult<Map<String, Object>> resJson=new JSONResult<Map<String, Object>>();
            Page<Msg> page=new Page<Msg>(pageNo,length);
            msgService.selectPageWithParam(page, param);
            Map<String,Object> map=new HashMap<>();
            map.put("total", page.getTotal());
            map.put("list", page.getRecords());
            resJson.setData(map);
            resJson.setStatus(0);
            return resJson;
    }

    /**
     * @description : 通过id获取Msg
     * ---------------------------------
     * @author : Lin_huanwen123
     * @since : Create in 2018-07-24
     */
    @GetMapping("/getMsgById")
    @ApiOperation(value="通过id获取Msg", notes="通过id获取Msg")
    @ApiImplicitParam(name = "id", paramType="query", value = "MsgID", dataType="integer", required = true)
    public JSONResult<Msg> getMsgById(Integer id) throws Exception{
            JSONResult<Msg> resJson = new JSONResult<>();
            Msg param= msgService.selectOneByObj(id);
            resJson.setData(param);
            resJson.setStatus(0);
            return resJson;
    }

    /**
     * @description : 通过id删除Msg
     * ---------------------------------
     * @author : Lin_huanwen123
     * @since : Create in 2018-07-24
     */
    @GetMapping("/deleteMsgById")
    @ApiOperation(value="通过id删除Msg", notes="通过id删除Msg")
    @ApiImplicitParam(name = "id", paramType="query", value = "MsgID", dataType="integer", required = true)
    public JSONResult<Msg> deleteMsgById(Integer id) throws Exception{
            JSONResult<Msg> resJson = new JSONResult<>();
            boolean boo=msgService.deleteById(id);
            resJson.setStatus(boo?0:1);
            return resJson;
    }

    /**
     * @description : 通过id更新Msg
     * ---------------------------------
     * @author : Lin_huanwen123
     * @since : Create in 2018-07-24
     */
    @PostMapping("/updateMsgById")
    @ApiOperation(value="通过id更新Msg", notes="通过id更新Msg")
    public JSONResult<Msg> updateMsgById(@ApiParam(name="Msg",value="Msg 实体类") @RequestBody Msg param) throws Exception{
            JSONResult<Msg> resJson = new JSONResult<>();
            boolean boo=msgService.updateById(param);
            resJson.setStatus(boo?0:1);
            return resJson;
    }

    /**
     * @description : 添加Msg
     * ---------------------------------
     * @author : Lin_huanwen123
     * @since : Create in 2018-07-24
     */
	@PostMapping("/addMsg")
    @ApiOperation(value="添加Msg", notes="添加Msg")
    public JSONResult<Msg> addMsg(@ApiParam(name="Msg",value="Msg 实体类") @RequestBody Msg param) throws Exception{
            JSONResult<Msg> resJson = new JSONResult<>();
            boolean boo=msgService.insert(param);
            resJson.setStatus(boo?0:1);
            return resJson;
    }
}
