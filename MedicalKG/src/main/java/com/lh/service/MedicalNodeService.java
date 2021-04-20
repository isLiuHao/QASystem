package com.lh.service;

import com.lh.entity.medical.Medical;
import com.lh.entity.medical.node.CureNode;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.SymptomNode;
import java.util.List;

public interface MedicalNodeService {
    //通过名字查找MedicalNode
    MedicalNode findMedicalNodeByName(String name);

    //通过MedicalNode名字查找子节点SymptomNode
    List<SymptomNode> findSymptomNodesByMedicalNodeName(String name);

    //通过MedicalNode名字查找子节点CureNode
    List<CureNode> findCureNodesByMedicalNodeName(String name);

    //通过SymptomNode名字查找父节点
    List<MedicalNode> findMedicalNodesBySymptomNodeName(String name);

    //通过CureNode名字查找父节点
    List<MedicalNode> findMedicalNodesByCureNodeName(String name);

    //保存单个数据
    void saveOneKG(Medical medical);
    //保存所有数据
    void saveAllKG();
    //数据库保存到neo4j
    void saveNeo4j(Medical medical);
    //删除所有节点
    boolean deleteAllKG();
}
