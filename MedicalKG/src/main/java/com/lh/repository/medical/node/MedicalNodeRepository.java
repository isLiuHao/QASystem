package com.lh.repository.medical.node;

import com.lh.entity.medical.node.MedicalNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalNodeRepository extends GraphRepository<MedicalNode> {
    //通过节点名查找
    MedicalNode findByName(String name);

}
