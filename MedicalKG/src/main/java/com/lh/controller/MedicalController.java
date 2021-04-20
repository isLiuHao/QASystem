package com.lh.controller;

import com.lh.commonUtils.R;
import com.lh.entity.medical.Medical;
import com.lh.service.MedicalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//对mysql数据库操作
@RestController
@RequestMapping("/Medical")
public class MedicalController {
    @Autowired
    private MedicalService medicalService;

    @ApiOperation(value="通过疾病名查找medical")
    @GetMapping("/findMedicalByName/{name}")
    public R findMedicalByName(@PathVariable("name") String name){
        Medical medical = medicalService.findMedicalByName(name);
        if(medical!=null){
            return R.ok().data("medical",medical);
        }
        else return R.error();
    }

    @ApiOperation(value="保存单个数据到neo4j")
    @PostMapping("/saveOneKG")
    public R saveOneKG(Medical medical){
        //medicalService.saveOneKG(medical);
        return R.error().message("未开放接口");
    }
}
