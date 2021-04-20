package com.lh.repository.medical.node;

import com.lh.entity.medical.node.SymptomNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomNodeRepository extends GraphRepository<SymptomNode> {
    //通过节点名查找
    SymptomNode findByName(String name);
    //查找全部
    List<SymptomNode> findAll();
}
