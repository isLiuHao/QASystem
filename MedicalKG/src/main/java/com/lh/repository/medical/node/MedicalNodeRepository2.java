package com.lh.repository.medical.node;

import com.lh.entity.medical.node.MedicalNode2;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalNodeRepository2 extends GraphRepository<MedicalNode2> {
}
