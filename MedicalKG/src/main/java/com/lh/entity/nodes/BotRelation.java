package com.lh.entity.nodes;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type = "BotRelation")
public class BotRelation {
    @GraphId
    private Long id;
    @StartNode
    private BotNode startNode;
    @EndNode
    private BotNode endNode;
    @Property
    private String relation;

    public BotRelation() {
    }

    public BotRelation(BotNode startNode, BotNode endNode, String relation) {
        this.id = id;
        this.startNode = startNode;
        this.endNode = endNode;
    }
}
