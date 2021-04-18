package com.lh.entity.medical.node;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

//neo4j节点关系
@Data
@RelationshipEntity(type = "medicalRelation")//节点类型
public class MedicalNodeRelation {
    @GraphId
    private Long id;
    @StartNode
    private MedicalNode startNode;
    @EndNode
    private MedicalNode2 endNode;
    @Property
    private String relation;

    public MedicalNodeRelation() {}

    public MedicalNodeRelation(MedicalNode startNode, MedicalNode2 endNode, String relation) {
        this.id = -1l;
        this.startNode = startNode;
        this.endNode = endNode;
        this.relation = relation;
    }
}
