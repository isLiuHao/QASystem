package com.lh.controller;

import com.lh.entity.medical.node.CureNode;
import com.lh.entity.medical.node.SymptomNode;
import com.lh.service.CureNodeService;
import com.lh.service.SymptomNodeService;
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
    //通过名字查找cureNode
    @GetMapping("/findCureNodeByName/{name}")
    public CureNode findCureNodeByName(@PathVariable("name") String name){
        return cureNodeService.findCureNodeByName(name);
    }

}
