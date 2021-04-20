package com.lh.controller;

import com.lh.commonUtils.R;
import com.lh.entity.medical.node.CureNode;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.SymptomNode;
import com.lh.service.MedicalNodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//对neo4j数据库操作
@RestController
@RequestMapping("/MedicalNode")
public class MedicalNodeController {
    @Autowired
    private MedicalNodeService medicalNodeService;

    @ApiOperation(value="通过名字查找MedicalNode")
    @GetMapping("/findMedicalNodeByName/{name}")
    public R findMedicalNodeByName(@PathVariable("name") String name){
        MedicalNode medicalNode = medicalNodeService.findMedicalNodeByName(name);
        if(medicalNode!=null){
            return R.ok().data("medicalNode",medicalNode);
        }
        else return R.error();
    }

    @ApiOperation(value="通过父节点名查询子节点SymptomNode名字")
    @GetMapping("/findSymptomNodesByMedicalNodeName/{name}")
    public R findSymptomNodesByMedicalNodeName(@PathVariable("name") String name){
        List<SymptomNode> symptomNodes = new ArrayList<>();
        symptomNodes = medicalNodeService.findSymptomNodesByMedicalNodeName(name);
        if(symptomNodes!=null){
            return R.ok().data("SymptomNodes",symptomNodes);
        }
        else return R.error();
    }
    @ApiOperation(value="通过父节点名查询子节点CureNode名字")
    @GetMapping("/findCureNodesByMedicalNodeName/{name}")
    public R findCureNodesByMedicalNodeName(@PathVariable("name") String name){
        List<CureNode> cureNodes = new ArrayList<>();
        cureNodes = medicalNodeService.findCureNodesByMedicalNodeName(name);
        if(cureNodes!=null){
            return R.ok().data("cureNodes",cureNodes);
        }
        else return R.error();
    }


    @ApiOperation(value="通过SymptomNode名字查找父节点")
    @GetMapping("/findBySymptomNodeName/{name}")
    public R findBySymptomNodeName(@PathVariable("name") String name){
        List<MedicalNode> medicalNodes = new ArrayList<>();
        medicalNodes = medicalNodeService.findMedicalNodesBySymptomNodeName(name);
        if(medicalNodes!=null){
            return R.ok().data("medicalNodes",medicalNodes);
        }
        else return R.error();
    }

    @ApiOperation(value="通过CureNode名字查找父节点")
    @GetMapping("/findByCureNodeName/{name}")
    public R findByCureNodeName(@PathVariable("name") String name){
        List<MedicalNode> medicalNodes = new ArrayList<>();
        medicalNodes = medicalNodeService.findMedicalNodesByCureNodeName(name);
        if(medicalNodes!=null){
            return R.ok().data("medicalNodes",medicalNodes);
        }
        else return R.error();
    }

}
