package com.lh.service;

import com.lh.entity.medical.node.CureNode;

public interface CureNodeService {
    //通过名字查找CureNode
    CureNode findCureNodeByName(String name);
}
