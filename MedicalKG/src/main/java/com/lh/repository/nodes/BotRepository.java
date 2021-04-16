package com.lh.repository.nodes;

import com.lh.entity.nodes.BotNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository extends GraphRepository<BotNode> {
    BotNode findAllByName(String name);
}
