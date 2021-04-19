package com.lh.service;

import com.lh.entity.medical.node.CureNode;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.SymptomNode;

import java.util.List;

public interface MedicalNodeService {
    //通过名字查找
    MedicalNode findMedicalNodeByName(String name);

    //通过MedicalNode名字查找子节点SymptomNode
    List<SymptomNode> findSymptomNodeByMedicalNodeName(String name);

    //通过MedicalNode名字查找子节点CureNode
    public List<CureNode> findCureNodeByMedicalNodeName(String name);

    //通过SymptomNode名字查找父节点
    List<MedicalNode> findBySymptomNodeName(String name);

}
