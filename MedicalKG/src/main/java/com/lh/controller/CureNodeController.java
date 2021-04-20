package com.lh.controller;

import com.lh.commonUtils.R;
import com.lh.entity.medical.node.CureNode;
import com.lh.service.CureNodeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//对neo4j数据库操作
@RestController
@RequestMapping("/MedicalNode")
public class CureNodeController {
    @Autowired
    private CureNodeService cureNodeService;

    @ApiOperation(value="通过名字查找cureNode")
    @GetMapping("/findCureNodeByName/{name}")
    public R findCureNodeByName(@PathVariable("name") String name){
        CureNode cureNode=cureNodeService.findCureNodeByName(name);
        if(cureNode!=null){
            return R.ok().data("cureNode",cureNode);
        }
        else return R.error();
    }

}
