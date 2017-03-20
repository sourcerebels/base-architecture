package com.sourcerebels.basearchitecture;

import java.util.List;

public interface Repository<Entity, Id> {

    List<Entity> findAll();
    Entity findById(Id id);
    Entity save(Entity entity);
}
