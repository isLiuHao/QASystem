package com.lh.controller;

import com.lh.entity.medical.Medical;
import com.lh.service.MedicalNodeService;
import com.lh.service.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Medical")
public class MedicalController {
    @Autowired
    private MedicalService medicalService;

    @GetMapping("/findMedicalByName")
    public Medical findMedicalByName(String name){
        return medicalService.findMedicalByName(name);
    }
}
