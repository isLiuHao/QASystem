package com.lh.controller;

import com.lh.commonUtils.R;
import com.lh.entity.medical.node.SymptomNode;
import com.lh.service.SymptomNodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//对neo4j数据库操作
@RestController
@RequestMapping("/MedicalNode")
public class SymptomNodeController {
    @Autowired
    private SymptomNodeService symptomNodeService;

    @ApiOperation(value="通过名字查找symptomNode")
    @GetMapping("/findSymptomNodeByName/{name}")
    public R findSymptomNodeByName(@PathVariable("name") String name){
        SymptomNode symptomNode = symptomNodeService.findSymptomNodeByName(name);
        if(symptomNode!=null){
            return R.ok().data("symptomNode",symptomNode);
        }
        else return R.error();
    }

}
