package com.lh.repository.medical.node;

import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.SymptomMedicalRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomMedicalRelationRepository extends GraphRepository<SymptomMedicalRelation> {
    //通过Symptom查找关系
    List<SymptomMedicalRelation> findSymptomMedicalRelationsByEndNode(String name);

    //通过Medical查找关系
    List<SymptomMedicalRelation> findSymptomMedicalRelationsByStartNode(MedicalNode medicalNode);


}
