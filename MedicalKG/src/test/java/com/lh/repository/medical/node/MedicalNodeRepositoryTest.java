package com.lh.repository.medical.node;

import com.lh.entity.medical.Medical;
import com.lh.entity.medical.node.CureNode;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.SymptomNode;
import com.lh.repository.medical.MedicalRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalNodeRepositoryTest {
    @Autowired
    MedicalRepository medicalRepository;//mysql表
    @Autowired
    MedicalNodeRepository medicalNodeR;//neo4j主节点
    @Autowired
    SymptomNodeRepository symptomNodeR;//neo4j子节点
    @Autowired
    CureNodeRepository cureNodeR;//neo4j子节点

    //节点直接的关系
    final String disease = "疾病";

    //保存单个数据
    @Test
    public void saveKG(){
        Medical medical = medicalRepository.findMedicalByName("流行性感冒");
        Medical medical1 = medicalRepository.findMedicalByName("流行性腮腺炎");
        saveNeo4j(medical);
        saveNeo4j(medical1);
    }
    //通过父节点名查询子节点
    @Test
    public void searchByMedicalNodeName(){
        MedicalNode medicalNode = medicalNodeR.findByName("流行性感冒");
        for(SymptomNode symptomNode:medicalNode.getSymptomNodes()){
            System.out.println("======"+symptomNode);
        }
    }
    //通过子节点名查询父节点
    @Test
    public void searchBySymptomNodeName(){
        List<MedicalNode> medicalNodes = medicalNodeR.findMedicalNodesBySymptomNode("呕吐");
        for(MedicalNode medicalNode:medicalNodes){
            System.out.println("====="+medicalNode);
        }
    }
    //数据库保存到neo4j
    private void saveNeo4j(Medical medical) {
        //查看主节点是否存在
        MedicalNode medicalNode = medicalNodeR.findByName(medical.getName());
        if(medicalNode==null){
            medicalNode = new MedicalNode(medical.getName(),disease);
        }
        //查看子节点是否存在
        for (String s : medical.getSymptom_list()){
            SymptomNode symptomNode = symptomNodeR.findByName(s);
            if(symptomNode==null){
                symptomNode = new SymptomNode(s);
                symptomNodeR.save(symptomNode);
            }
            medicalNode.addSymptom(symptomNode);
        }
        for (String s : medical.getCure_list()){
            CureNode cureNode = cureNodeR.findByName(s);
            if(cureNode==null){
                cureNode = new CureNode(s);
                cureNodeR.save(cureNode);
            }
            medicalNode.addCure(cureNode);
        }
        medicalNodeR.save(medicalNode);//保存主节点
    }
    //保存所有数据
    @Test
    public void saveAllKG(){
        int num = 0;//当前第几页
        int size = 1000;//每页1000条
        boolean s = false;
        Sort sort = new Sort(Sort.Direction.DESC,"id");//降序
        Pageable pageable = new PageRequest(num++,size,sort);
        Page<Medical> medicalPage = medicalRepository.findAll(pageable);
        List<Medical> content = medicalPage.getContent();
        //先保存第一页
        log.warn("保存第"+num+"页到neo4j数据库（每页1000条）");
        int i=1;
        for (Medical medical : content){
            log.warn("保存第"+num+"页,第"+i+"条，到neo4j数据库（每页1000条）");
            saveNeo4j(medical);
            i++;
        }
        //如果还有下一页
        while (medicalPage.hasNext()){
            pageable = new PageRequest(num++,size,sort);
            medicalPage = medicalRepository.findAll(pageable);
            int j=1;
            for (Medical medical : medicalPage.getContent()){
                log.warn("保存第"+num+"页,第"+j+"条，到neo4j数据库（每页1000条）");
                saveNeo4j(medical);
                j++;
            }
        }
        log.warn("保存到neo4j完成，共"+num+"页数据");
    }
}