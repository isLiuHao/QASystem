package com.lh.repository.medical.node;

import com.lh.entity.medical.Medical;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.MedicalNode2;
import com.lh.entity.medical.node.MedicalNodeRelation;
import com.lh.entity.medical.node.MedicalRelation;
import com.lh.repository.medical.MedicalRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalNodeRepositoryTest2 {
    @Autowired
    MedicalNodeRepository medicalNodeR;//主节点
    @Autowired
    MedicalNodeRepository2 medicalNodeR2;//副节点
    @Autowired
    MedicalNodeRelationRepository medicalRelationR;
    @Autowired
    MedicalRepository medicalRepository;

    //节点直接的关系
    final String intro = "简介";
    final String cause = "病因";
    final String diagnose = "诊断";
    final String cure = "治疗";
    final String prevent = "预防";
    final String complication = "并发症";
    final String symptom = "症状";
    final String baohan = "包含";
    final String examine = "检查";

    @Test
    public void saveKG(){
        Medical medical = medicalRepository.findByName("流行性感冒");
        Medical medical1 = medicalRepository.findByName("流行性腮腺炎");
        saveNeo4j(medical);
        saveNeo4j(medical1);
    }
    @Test
    public void searchKG(){
        System.out.println();
    }

    private void saveNeo4j(Medical medical) {
        //保存主节点
        MedicalNode node = new MedicalNode(medical.getName(),medical.getPart().toString()+medical.getFamily());
        medicalNodeR.save(node);

        //保存子节点的子节点
        for (String s : medical.getIntro_list()){
            MedicalNode2 node11 = new MedicalNode2(s,s);
            medicalNodeR2.save(node11);//保存子子节点
            medicalRelationR.save(new MedicalNodeRelation(node,node11, intro));//保存子子关系
        }
        for (String s : medical.getCause_list()){
            MedicalNode2 node22 = new MedicalNode2(s,s);
            medicalNodeR2.save(node22);
            medicalRelationR.save(new MedicalNodeRelation(node,node22, cause));
        }
        for (String s : medical.getDiagnose_list()){
            MedicalNode2 node33 = new MedicalNode2(s,s);
            medicalNodeR2.save(node33);
            medicalRelationR.save(new MedicalNodeRelation(node,node33, diagnose));
        }
        for (String s : medical.getCure_list()){
            MedicalNode2 node44 = new MedicalNode2(s,s);
            medicalNodeR2.save(node44);
            medicalRelationR.save(new MedicalNodeRelation(node,node44, cure));
        }
        for (String s : medical.getPrevent_list()){
            MedicalNode2 node55 = new MedicalNode2(s,s);
            medicalNodeR2.save(node55);
            medicalRelationR.save(new MedicalNodeRelation(node,node55, prevent));
        }
        for (String s : medical.getComplication_list()){
            MedicalNode2 node66 = new MedicalNode2(s,s);
            medicalNodeR2.save(node66);
            medicalRelationR.save(new MedicalNodeRelation(node,node66, complication));
        }
        for (String s : medical.getSymptom_list()){
            MedicalNode2 node77 = new MedicalNode2(s,s);
            medicalNodeR2.save(node77);
            medicalRelationR.save(new MedicalNodeRelation(node,node77, symptom));
        }
    }

}