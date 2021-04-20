package com.lh.controller;

import com.lh.entity.medical.Medical;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.service.MedicalNodeService;
import com.lh.service.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//对mysql数据库操作
@RestController
@RequestMapping("/Medical")
public class MedicalController {
    @Autowired
    private MedicalService medicalService;

    //通过疾病名查找
    @GetMapping("/findMedicalByName/{name}")
    public Medical findMedicalByName(@PathVariable("name") String name){
        return medicalService.findMedicalByName(name);
    }


}
