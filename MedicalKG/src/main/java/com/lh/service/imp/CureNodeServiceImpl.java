package com.lh.service.imp;

import com.lh.entity.medical.node.CureNode;
import com.lh.entity.medical.node.SymptomNode;
import com.lh.repository.medical.node.CureNodeRepository;
import com.lh.repository.medical.node.SymptomNodeRepository;
import com.lh.service.CureNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CureNodeServiceImpl implements CureNodeService {
    @Autowired
    private CureNodeRepository cureNodeR;
    //通过名字查找SymptomNode
    @Override
    public CureNode findCureNodeByName(String name) {
        return cureNodeR.findByName(name);
    }
}
