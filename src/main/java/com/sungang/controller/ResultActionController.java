package com.sungang.controller;

import com.sungang.model.ResultActionBean;
import com.sungang.model.result.ResultActionResponse;
import com.sungang.service.ResultActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by SGang on 2019/1/22.
 */
@RestController
public class ResultActionController extends BaseController {
    @Autowired
    private ResultActionService resultActionService;
    @RequestMapping(value = "/saveResultAction",method = RequestMethod.GET)
    public ResultActionResponse saveResultAction(ResultActionBean bean){
        bean.setCreateTime(new Timestamp(new Date().getTime()));
        resultActionService.saveResultAction(bean);
        return ResultActionResponse.success();
    }
    @RequestMapping(value = "/getResultActionByOpenId",method = RequestMethod.GET)
    public List<ResultActionBean> getResultActionByOpenId(String openid){
        return resultActionService.getResultActionByOpenId(openid);
    }

}
