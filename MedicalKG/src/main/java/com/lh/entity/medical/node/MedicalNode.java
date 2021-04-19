package com.lh.entity.medical.node;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

//neo4j节点
@Data
@NoArgsConstructor
@NodeEntity(label = "medical")//疾病节点(主节点)
public class MedicalNode {
    @GraphId
    private Long id;
    @Property(name = "name")
    private String name;//节点名
    @Property(name = "intro")
    private String intro;//节点介绍
    //关系
    @Relationship(type = "medical-symptom", direction = Relationship.OUTGOING)
    private List<SymptomNode> symptomNodes;
    @Relationship(type = "medical-cure", direction = Relationship.OUTGOING)
    private List<CureNode> cureNodes;

    public MedicalNode(String name, String intro) {
        this.id = -1l;
        this.name = name;
        this.intro = intro;
    }
    public void addSymptom(SymptomNode symptomNode) {
        if (this.symptomNodes == null) {
            this.symptomNodes = new ArrayList<>();
        }
        this.symptomNodes.add(symptomNode);
    }
    public void addCure(CureNode cureNode) {
        if (this.cureNodes == null) {
            this.cureNodes = new ArrayList<>();
        }
        this.cureNodes.add(cureNode);
    }
}
