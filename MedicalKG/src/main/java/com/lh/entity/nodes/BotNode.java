package com.lh.entity.nodes;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import java.io.Serializable;
import java.util.List;

@Data
@NodeEntity(label = "Bot")
public class BotNode implements Serializable {
    @GraphId
    private Long id;
    @Property(name = "name")
    @Index(unique = true)
    private String name;//名称
    @Property(name = "kind")
    private String kind;//（疾病、症状）
    @Property(name = "family")
    private List<String> family;//科室
    @Property(name = "part")
    private List<String> part;//部位

    public BotNode() {
    }

    public BotNode(String name, String kind, List<String> family, List<String> part) {
        this.name = name;
        this.kind = kind;
        this.family = family;
        this.part = part;
    }
}
