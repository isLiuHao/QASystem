package com.lh.service.imp;

import com.lh.entity.medical.Medical;
import com.lh.entity.medical.node.CureNode;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.SymptomNode;
import com.lh.repository.medical.MedicalRepository;
import com.lh.repository.medical.node.CureNodeRepository;
import com.lh.repository.medical.node.MedicalNodeRepository;
import com.lh.repository.medical.node.SymptomNodeRepository;
import com.lh.service.MedicalNodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class MedicalNodeServiceImpl implements MedicalNodeService {
    @Autowired
    private MedicalNodeRepository medicalNodeR;
    @Autowired
    private MedicalRepository medicalRepository;
    @Autowired
    private SymptomNodeRepository symptomNodeR;
    @Autowired
    private CureNodeRepository cureNodeR;

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
    //保存单个数据到neo4j
    @Override
    public void saveOneKG(Medical medical){
        saveNeo4j(medical);
    }
    //保存所有数据
    @Override
    public void saveAllKG(){
        int num = 0;//当前第几页
        int size = 1000;//每页1000条
        boolean s = false;
        Sort sort = new Sort(Sort.Direction.DESC,"id");//降序
        Pageable pageable = new PageRequest(num++,size,sort);
        Page<Medical> medicalPage = medicalRepository.findAll(pageable);
        List<Medical> content = medicalPage.getContent();
        //先保存第一页
        log.info("保存第"+num+"页到neo4j数据库（每页1000条）");
        int i=1;
        for (Medical medical : content){
            log.info("保存第"+num+"页,第"+i+"条，到neo4j数据库（每页1000条）");
            saveNeo4j(medical);
            i++;
        }
        //如果还有下一页
        while (medicalPage.hasNext()){
            pageable = new PageRequest(num++,size,sort);
            medicalPage = medicalRepository.findAll(pageable);
            int j=1;
            for (Medical medical : medicalPage.getContent()){
                log.info("保存第"+num+"页,第"+j+"条，到neo4j数据库（每页1000条）");
                saveNeo4j(medical);
                j++;
            }
        }
        log.info("保存到neo4j完成，共"+num+"页数据");
    }
    //数据库保存到neo4j
    @Override
    public void saveNeo4j(Medical medical){
        //查看主节点是否存在
        MedicalNode medicalNode = medicalNodeR.findByName(medical.getName());
        if(medicalNode==null){
            medicalNode = new MedicalNode(medical.getName(),"疾病");
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
    //删除所有节点
    @Override
    public boolean deleteAllKG(){
        medicalNodeR.deleteAll();
        if(medicalNodeR.findAll()==null){
            return true;
        }else
            return false;
    }
}
