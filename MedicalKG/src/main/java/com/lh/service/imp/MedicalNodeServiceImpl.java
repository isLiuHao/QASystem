package com.lh.service.imp;

import com.lh.entity.medical.node.CureNode;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.SymptomNode;
import com.lh.repository.medical.node.MedicalNodeRepository;
import com.lh.service.MedicalNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalNodeServiceImpl implements MedicalNodeService {
    @Autowired
    private MedicalNodeRepository medicalNodeR;

    //通过名字查找MedicalNode
    @Override
    public MedicalNode findMedicalNodeByName(String name) {
        return medicalNodeR.findByName(name);
    }

    //通过父节点名字查找子节点SymptomNode
    @Override
    public List<SymptomNode> findSymptomNodesByMedicalNodeName(String name) {
        MedicalNode medicalNode = medicalNodeR.findByName(name);
        return medicalNode.getSymptomNodes();
    }
    //通过父节点名字查找子节点CureNode
    @Override
    public List<CureNode> findCureNodesByMedicalNodeName(String name) {
        MedicalNode medicalNode = medicalNodeR.findByName(name);
        return medicalNode.getCureNodes();
    }

    //通过SymptomNode名字查找父节点
    @Override
    public List<MedicalNode> findMedicalNodesBySymptomNodeName(String name) {
        return medicalNodeR.findMedicalNodesBySymptomNode(name);
    }
    //通过CureNode名字查找父节点
    @Override
    public List<MedicalNode> findMedicalNodesByCureNodeName(String name) {
        return medicalNodeR.findMedicalNodesByCureNode(name);
    }



}
