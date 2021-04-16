package com.lh.repository.medical.node;

import com.lh.entity.medical.Medical;
import com.lh.entity.nodes.BotNode;
import com.lh.entity.nodes.BotRelation;
import com.lh.repository.medical.MedicalRepository;
import com.lh.repository.medical.MsymptomRepository;
import com.lh.repository.nodes.BotRelationRepository;
import com.lh.repository.nodes.BotRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BotNodeUpdata{
        private Logger logger = LoggerFactory.getLogger(getClass());
        //neo4j
        @Autowired
        BotRepository botRepository;
        @Autowired
        BotRelationRepository botRelationRepository;
        //mysql
        @Autowired
        MedicalRepository medicalRepository;
        @Autowired
        MsymptomRepository msymptomRepository;
        //关系
        final String BRING = "bring";

    @Test
    public void test() {
        int size = 1000;//每页1000条
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(0, size, sort);
        Page<Medical> medicalPage = getMedicals(pageable);
        //如果还有下一页
        while (medicalPage.hasNext()) {
            medicalPage = getMedicals(pageable = pageable.next());
        }
        logger.info("操作完成");
    }

    private Page<Medical> getMedicals(Pageable pageable) {
        //先从数据库获取数据
        Page<Medical> medicalPage = medicalRepository.findAll(pageable);
        List<Medical> content = medicalPage.getContent();
        //遍历数据
        for (Medical medical : content) {
            List<String> symptomList = medical.getSymptom_list();
            //从neo4j数据库获取数据
            List<BotRelation> botRelationList = botRelationRepository.findAllByMedical(medical.getName());
            List<String> botString = new ArrayList<>();//备份症状集合
            for (BotRelation botRelation: botRelationList){
                botString.add(botRelation.getEndNode().getName());
            }
            for (String symptom : symptomList){
                //如果不存在该关系的话
                if (!botString.contains(symptom)){
                    //看下数据库有没有节点
                    BotNode bot = botRepository.findAllByName(symptom);
                    if (bot!=null){
                        BotNode node ;
                        if (botRelationList.isEmpty()){
                            node = botRepository.findAllByName(medical.getName());
                        }else {
                            node = botRelationList.get(0).getStartNode();
                        }
                        //有的话连一下
                        BotRelation botRelation = new BotRelation(node,bot,BRING);
                        botRelationRepository.save(botRelation);
                        logger.info("成功更新："+ node.getName()+" "+bot.getName());
                    }
                }
            }
        }
        return medicalPage;
    }

}
