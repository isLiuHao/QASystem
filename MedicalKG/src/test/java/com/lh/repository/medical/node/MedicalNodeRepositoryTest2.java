package com.lh.repository.medical.node;

import com.lh.entity.medical.Medical;
import com.lh.entity.medical.node.MedicalNode;
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
    MedicalNodeRepository medicalNodeRepository;
    @Autowired
    MedicalRelationRepository medicalRelationRepository;
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
        Medical medical = medicalRepository.findByName("风疹");
        saveNeo4j(medical);
    }
    @Test
    public void searchKG(){
        System.out.println();
    }

    private void saveNeo4j(Medical medical) {
        //保存主节点
        MedicalNode node = new MedicalNode(medical.getName(),medical.getPart().toString()+medical.getFamily());
        node.setMajor(true);//设为主节点
        medicalNodeRepository.save(node);
        //保存子节点们
        MedicalNode node1 = new MedicalNode(intro,medical.getIntro());
        medicalNodeRepository.save(node1);
        MedicalNode node2 = new MedicalNode(cause,medical.getCause());
        medicalNodeRepository.save(node2);
        MedicalNode node3 = new MedicalNode(diagnose,medical.getDiagnose());
        medicalNodeRepository.save(node3);
        MedicalNode node4 = new MedicalNode(cure,medical.getCure());
        medicalNodeRepository.save(node4);
        MedicalNode node5 = new MedicalNode(prevent,medical.getPrevent());
        medicalNodeRepository.save(node5);
        MedicalNode node6 = new MedicalNode(complication,medical.getComplication());
        medicalNodeRepository.save(node6);
        MedicalNode node7 = new MedicalNode(symptom,medical.getSymptom());
        medicalNodeRepository.save(node7);
        //保存主节点与子节点的关系
        medicalRelationRepository.save(new MedicalRelation(node,node1,intro));
        medicalRelationRepository.save(new MedicalRelation(node,node2,cause));
        medicalRelationRepository.save(new MedicalRelation(node,node3,diagnose));
        medicalRelationRepository.save(new MedicalRelation(node,node4,cure));
        medicalRelationRepository.save(new MedicalRelation(node,node5,prevent));
        medicalRelationRepository.save(new MedicalRelation(node,node6,complication));
        medicalRelationRepository.save(new MedicalRelation(node,node7,symptom));
        //保存子节点的子节点
        for (String s : medical.getIntro_list()){
            MedicalNode node11 = new MedicalNode(s,s);
            medicalNodeRepository.save(node11);//保存子子节点
            medicalRelationRepository.save(new MedicalRelation(node1,node11, baohan));//保存子子关系
        }
        for (String s : medical.getCause_list()){
            MedicalNode node22 = new MedicalNode(s,s);
            medicalNodeRepository.save(node22);
            medicalRelationRepository.save(new MedicalRelation(node2,node22, baohan));
        }
        for (String s : medical.getDiagnose_list()){
            MedicalNode node33 = new MedicalNode(s,s);
            medicalNodeRepository.save(node33);
            medicalRelationRepository.save(new MedicalRelation(node3,node33, baohan));
        }
        for (String s : medical.getCure_list()){
            MedicalNode node44 = new MedicalNode(s,s);
            medicalNodeRepository.save(node44);
            medicalRelationRepository.save(new MedicalRelation(node4,node44, baohan));
        }
        for (String s : medical.getPrevent_list()){
            MedicalNode node55 = new MedicalNode(s,s);
            medicalNodeRepository.save(node55);
            medicalRelationRepository.save(new MedicalRelation(node5,node55, baohan));
        }
        for (String s : medical.getComplication_list()){
            MedicalNode node66 = new MedicalNode(s,s);
            medicalNodeRepository.save(node66);
            medicalRelationRepository.save(new MedicalRelation(node6,node66, baohan));
        }
        for (String s : medical.getSymptom_list()){
            MedicalNode node77 = new MedicalNode(s,s);
            medicalNodeRepository.save(node77);
            medicalRelationRepository.save(new MedicalRelation(node7,node77, baohan));
        }
    }

}