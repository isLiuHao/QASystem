package com.lh.repository.medical.node;

import com.lh.entity.medical.node.CureNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CureNodeRepository extends GraphRepository<CureNode> {
    //通过节点名查找
    CureNode findByName(String name);
    //查找全部
    List<CureNode> findAll();
}
