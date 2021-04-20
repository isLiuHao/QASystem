package com.lh.repository.medical.node;

import com.lh.entity.medical.Medical;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.SymptomNode;
import com.lh.service.MedicalNodeService;
import com.lh.service.MedicalService;
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
public class MedicalControllerTest {
    @Autowired
    private MedicalNodeService medicalNodeService;
    @Autowired
    private MedicalService medicalService;

    //保存单个数据
    @Test
    public void saveOneKGTest(){
        Medical medical = medicalService.findMedicalByName("流行性感冒");
        medicalNodeService.saveOneKG(medical);
        Medical medical1 = medicalService.findMedicalByName("流行性腮腺炎");
        medicalNodeService.saveOneKG(medical1);
    }
    //保存所有数据
    @Test
    public void saveAllKGTest(){
        medicalNodeService.saveAllKG();
    }
    //删除所有neo4j
    @Test
    public void deleteAll(){
        boolean flag = medicalNodeService.deleteAllKG();
        if(flag){
            log.info("删除全部节点成功");
        }else
            log.info("删除全部节点失败");
    }

    //通过父节点名查询子节点
    @Test
    public void searchByMedicalNodeName(){
        MedicalNode medicalNode = medicalNodeService.findMedicalNodeByName("流行性感冒");
        for(SymptomNode symptomNode:medicalNode.getSymptomNodes()){
            System.out.println("======"+symptomNode);
        }
    }
    //通过子节点名查询父节点
    @Test
    public void searchBySymptomNodeName(){
        List<MedicalNode> medicalNodes = medicalNodeService.findMedicalNodesBySymptomNodeName("呕吐");
        for(MedicalNode medicalNode:medicalNodes){
            System.out.println("====="+medicalNode);
        }
    }


}