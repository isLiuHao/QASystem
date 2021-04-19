package com.lh.repository.medical.node;

import com.lh.entity.medical.node.MedicalNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalNodeRepository extends GraphRepository<MedicalNode> {
    //通过节点名查找
    MedicalNode findByName(String name);
    //通过SymptomNodes查找
    @Query("MATCH (n:medical)-[r:`medical-symptom`]->(m:symptom) WHERE m.name={name} return n")
    List<MedicalNode> findMedicalNodesBySymptomNodes(@Param("name") String name);

}
