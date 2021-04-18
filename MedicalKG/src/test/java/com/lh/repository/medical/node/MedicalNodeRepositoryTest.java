package com.lh.repository.medical.node;

import com.lh.entity.medical.Medical;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.SymptomNode;
import com.lh.entity.medical.node.SymptomMedicalRelation;
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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalNodeRepositoryTest {
    @Autowired
    MedicalNodeRepository medicalNodeR;//主节点
    @Autowired
    SymptomNodeRepository symptomNodeR;//副节点
    @Autowired
    SymptomMedicalRelationRepository symptomMRelationR;
    @Autowired
    MedicalRepository medicalRepository;

    //节点直接的关系
    final String disease = "疾病";
    final String intro = "简介";
    final String cause = "病因";
    final String diagnose = "诊断";
    final String cure = "治疗";
    final String prevent = "预防";
    final String complication = "并发症";
    final String hasSymptom = "拥有症状";
    final String family = "科室";
    final String part = "部位";

    @Test
    public void saveKG(){
        Medical medical = medicalRepository.findByName("流行性感冒");
        Medical medical1 = medicalRepository.findByName("流行性腮腺炎");
        saveNeo4j(medical);
        saveNeo4j(medical1);
    }
    @Test
    public void searchKG(){
        MedicalNode medicalNode = medicalNodeR.findByName("流行性感冒");
        System.out.println(medicalNode);
    }

    private void saveNeo4j(Medical medical) {
        //保存主节点
        MedicalNode medicalNode = new MedicalNode(medical.getName(),disease);
        medicalNodeR.save(medicalNode);
        //保存子节点和关系
        List<SymptomMedicalRelation> symptomlists=new ArrayList<>();
        for (String s : medical.getSymptom_list()){
            SymptomNode introNode = new SymptomNode(s);
            symptomNodeR.save(introNode);//保存子节点
            symptomMRelationR.save(new SymptomMedicalRelation(medicalNode, introNode, hasSymptom));//保存关系
        }
    }

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