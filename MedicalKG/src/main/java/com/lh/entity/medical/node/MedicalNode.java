package com.lh.entity.medical.node;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
//neo4j节点
@Data
@NodeEntity(label = "medical")//节点类型
public class MedicalNode {
    @GraphId
    private Long id;
    @Property(name = "name")
    private String name;//节点名
    @Property(name = "intro")
    private String intro;//节点介绍
    @Property(name = "major")
    private boolean major;//是否是主节点

    public MedicalNode() {
        this.major = false;
    }

    public MedicalNode(String name, String intro) {
        this.id = -1l;
        this.name = name;
        this.intro = intro;
        this.major = false;
    }

}
