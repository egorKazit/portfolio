package com.yk.protfolio.springportfolio.persistence;

import com.yk.protfolio.springportfolio.utilities.UpdateEntityStatus;
import java.util.List;
import java.util.Map;

public interface DAOEntityManager {
    <Entity> List<Entity> getListOfEntities(Class<Entity> entityClass);

    <Entity> Entity getEntityByKeys(Class<Entity> entityClass, Map<String, Object> keys);

    <Entity> UpdateEntityStatus<Entity> updateEntity(Entity entity);

}
