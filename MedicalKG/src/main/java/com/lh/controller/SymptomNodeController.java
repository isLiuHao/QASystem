package com.lh.controller;

import com.lh.entity.medical.node.CureNode;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.SymptomNode;
import com.lh.service.CureNodeService;
import com.lh.service.MedicalNodeService;
import com.lh.service.SymptomNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//对neo4j数据库操作
@RestController
@RequestMapping("/MedicalNode")
public class SymptomNodeController {
    @Autowired
    private SymptomNodeService symptomNodeService;
    //通过名字查找symptomNode
    @GetMapping("/findSymptomNodeByName/{name}")
    public SymptomNode findSymptomNodeByName(@PathVariable String name){
        return symptomNodeService.findSymptomNodeByName(name);
    }

}
