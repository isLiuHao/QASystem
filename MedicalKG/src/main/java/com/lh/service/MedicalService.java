package com.lh.service;

import com.lh.entity.medical.Medical;

public interface MedicalService {
    //通过名字查找
    Medical findMedicalByName(String name);
}
