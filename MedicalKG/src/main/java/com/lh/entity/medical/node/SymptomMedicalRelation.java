package com.lh.entity.medical.node;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

//neo4j节点关系
@Data
@RelationshipEntity(type = "medical-symptom")//疾病-症状关系
public class SymptomMedicalRelation {
    @GraphId
    private Long id;
    @StartNode
    private MedicalNode startNode;
    @EndNode
    private SymptomNode endNode;
    @Property
    private String relation;

    public SymptomMedicalRelation() {}

    public SymptomMedicalRelation(MedicalNode startNode, SymptomNode endNode, String relation) {
        this.id = -1l;
        this.startNode = startNode;
        this.endNode = endNode;
        this.relation = relation;
    }
}
