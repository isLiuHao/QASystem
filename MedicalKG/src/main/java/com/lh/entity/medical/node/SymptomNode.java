package com.lh.entity.medical.node;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

//neo4j节点
@Data
@NodeEntity(label = "symptom")//症状节点
public class SymptomNode {
    @GraphId
    private Long id;
    @Property(name = "name")
    private String name;//节点名
    @Property(name = "intro")
    private String intro;//节点介绍

    public SymptomNode() {}

    public SymptomNode(String name) {
        this.id = -1l;
        this.name = name;
        this.intro = "症状";
    }

}