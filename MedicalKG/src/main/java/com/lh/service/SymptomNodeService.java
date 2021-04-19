package com.lh.service;

import com.lh.entity.medical.node.SymptomNode;

public interface SymptomNodeService {
    //通过名字查找SymptomNode
    SymptomNode findSymptomNodeByName(String name);
}
