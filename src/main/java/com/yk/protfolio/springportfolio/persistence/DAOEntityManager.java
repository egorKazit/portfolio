package com.yk.protfolio.springportfolio.persistence;

import com.yk.protfolio.springportfolio.utilities.UpdateEntityStatus;
import java.util.List;
import java.util.Map;

/**
 * DAO entity manager interface
 */
public interface DAOEntityManager {
    /**
     * gets list of generic entity
     *
     * @param entityClass entity class
     * @param <Entity>    generic entity
     * @return list of entities
     */
    <Entity> List<Entity> getListOfEntities(Class<Entity> entityClass);

    /**
     * gets generic entry based on provided keys
     *
     * @param entityClass entity class
     * @param keys        keys
     * @param <Entity>    generic entity
     * @return entity
     */
    <Entity> Entity getEntityByKeys(Class<Entity> entityClass, Map<String, Object> keys);

    /**
     * updates entity and returns the status of updated entity
     *
     * @param entity   entity
     * @param <Entity> generic entity
     * @return update entity status
     */
    <Entity> UpdateEntityStatus<Entity> updateEntity(Entity entity);

}
