package com.lh.service.imp;

import com.lh.entity.medical.node.SymptomNode;
import com.lh.repository.medical.node.SymptomNodeRepository;
import com.lh.service.SymptomNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SymptomNodeServiceImpl implements SymptomNodeService {
    @Autowired
    private SymptomNodeRepository symptomNodeR;
    //通过名字查找SymptomNode
    @Override
    public SymptomNode findSymptomNodeByName(String name) {
        return symptomNodeR.findByName(name);
    }
}
