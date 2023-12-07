package com.haakimi.dormInspection.controller.protect;


import com.haakimi.dormInspection.entity.pojo.SbUser;
import com.haakimi.dormInspection.entity.vo.rq.testRq;
import com.haakimi.dormInspection.enums.ResultStatus;
import com.haakimi.dormInspection.service.SbUserService;
import com.haakimi.dormInspection.utils.ResultMapHelper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.haakimi.dormInspection.controller.BaseController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lcc
 * @since 2020-04-15
 */
@RestController
@RequestMapping("/user")
public class SbUserController extends BaseController {

    @Autowired
    SbUserService sbUserService;

    @PostMapping("test")
    @ApiOperation(value = "测试接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "id"))
    public Map<String,Object> test(@RequestBody @Validated testRq testRq){
//        SbUser sbUser = sbUserService.getById(testRq.getId());
        System.out.println(testRq);
        return ResultMapHelper.success(testRq, ResultStatus.Success.getMsg());
    }

}
