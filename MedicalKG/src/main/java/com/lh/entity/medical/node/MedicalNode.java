package com.lh.entity.medical.node;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

//neo4j节点
@Data
@NodeEntity(label = "medical")//疾病节点(主节点)
public class MedicalNode {
    @GraphId
    private Long id;
    @Property(name = "name")
    private String name;//节点名
    @Property(name = "intro")
    private String intro;//节点介绍

    public MedicalNode() { }

    public MedicalNode(String name, String intro) {
        this.id = -1l;
        this.name = name;
        this.intro = intro;
    }

}
