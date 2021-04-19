package com.lh.repository.medical.node;

import com.lh.entity.medical.node.CureNode;
import com.lh.entity.medical.node.SymptomNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CureNodeRepository extends GraphRepository<CureNode> {
    //通过节点名查找
    CureNode findByName(String name);
}
