package com.debug.imooc.dubbo.one.server.controller;

import com.debug.imooc.dubbo.one.api.response.BaseResponse;
import com.debug.imooc.dubbo.one.api.service.IDubboItemInfoService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    private static final String prefix = "item";

    @Autowired
    private IDubboItemInfoService iDubboItemInfoService;
    /**
     * 用户商城列表查询
     *     调用服务方dubboOne提供的列表查询功能
     * @return
     */
    @RequestMapping(value = prefix + "/list" ,method = RequestMethod.GET)
    public Map<String,Object> list(){
        Map<String,Object> resMap = Maps.newHashMap();
//        resMap.put("code" , "0");
//        resMap.put("msg" , "成功");
        try {
            BaseResponse baseResponse = iDubboItemInfoService.listItems();
            if (baseResponse != null && baseResponse.getCode().equals(0)){
                resMap.put("data" , baseResponse.getData());
            }
        }catch (Exception e){
            resMap.put("code" , "-1");
            resMap.put("msg" , "失败");
        }
        return resMap;
    }

    /**
     * 用户商城列表 -- 分页查询
     *     调用服务方dubboOne提供的列表查询功能
     * @return
     */
    @RequestMapping(value = prefix + "/page/list" ,method = RequestMethod.GET)
    public Map<String,Object> pageList(Integer pageNo , Integer pageSize){
        if ( pageNo == null || pageSize == null || pageNo <= 0 || pageSize <= 0){
            pageNo = 1 ;
            pageSize = 2 ;
        }
        Map<String,Object> resMap = Maps.newHashMap();
        try {
            BaseResponse baseResponse = iDubboItemInfoService.listPagesItems(pageNo,pageSize);
            if (baseResponse != null && baseResponse.getCode().equals(0)){
                resMap.put("data" , baseResponse.getData());
            }
        }catch (Exception e){
            resMap.put("code" , "-1");
            resMap.put("msg" , "失败");
        }
        return resMap;
    }


    /**
     * 用户商城列表查询-分页查询-带参数模糊查询
     * @return
     */
    @RequestMapping(value = prefix+"/page/list/params",method = RequestMethod.GET)
    public Map<String,Object> pageListParams(Integer pageNo,Integer pageSize,String search){
//        if (pageNo==null || pageSize==null || pageNo<=0 || pageSize<=0){
//            pageNo=1;
//            pageSize=2;
//        }
        Map<String,Object> resMap= Maps.newHashMap();
        resMap.put("code","0");
        resMap.put("msg","成功");

        //TODO:调用服务提供方dubboOne提供的列表查询-分页查询功能
        try {
            BaseResponse response=iDubboItemInfoService.listPageItemsParams(pageNo,pageSize,search);
            if (response!=null && response.getCode().equals(0)){
                resMap.put("data",response.getData());

            }
        }catch (Exception e){
            e.printStackTrace();
            resMap.put("code","-1");
            resMap.put("msg","失败");
        }
        return resMap;
    }
}
