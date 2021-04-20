package com.lh.service;

import com.lh.entity.medical.Medical;

public interface MedicalService {
    //通过名字查找
    Medical findMedicalByName(String name);
    //保存单个数据
    void saveOneKG(Medical medical);
    //保存所有数据
    void saveAllKG();
    //数据库保存到neo4j
    void saveNeo4j(Medical medical);
}
