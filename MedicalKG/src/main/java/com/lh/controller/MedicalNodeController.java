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
public class MedicalNodeController {
    @Autowired
    private MedicalNodeService medicalNodeService;
    //通过名字查找MedicalNode
    @GetMapping("/findMedicalNodeByName/{name}")
    public MedicalNode findMedicalNodeByName(@PathVariable String name){
        return medicalNodeService.findMedicalNodeByName(name);
    }

    //通过父节点名查询子节点SymptomNode名字
    @GetMapping("/findSymptomNodesByMedicalNodeName/{name}")
    public List<SymptomNode> findSymptomNodesByMedicalNodeName(@PathVariable String name){
        return medicalNodeService.findSymptomNodesByMedicalNodeName(name);
    }
    //通过父节点名查询子节点CureNode名字
    @GetMapping("/findCureNodesByMedicalNodeName/{name}")
    public List<CureNode> findCureNodesByMedicalNodeName(@PathVariable String name){
        return medicalNodeService.findCureNodesByMedicalNodeName(name);
    }

    //通过SymptomNode名字查找父节点
    @GetMapping("/findBySymptomNodeName/{name}")
    public List<MedicalNode> findBySymptomNodeName(@PathVariable String name){
        return medicalNodeService.findMedicalNodesBySymptomNodeName(name);
    }
    //通过CureNode名字查找父节点
    @GetMapping("/findByCureNodeName/{name}")
    public List<MedicalNode> findByCureNodeName(@PathVariable String name){
        return medicalNodeService.findMedicalNodesByCureNodeName(name);
    }

}
