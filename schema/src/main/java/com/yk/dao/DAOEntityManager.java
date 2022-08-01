package com.yk.dao;

import com.yk.processor.UpdateEntityStatus;
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
     * @param <E>    generic entity
     * @return list of entities
     */
    <E> List<E> getListOfEntities(Class<E> entityClass);

    /**
     * gets generic entry based on provided keys
     *
     * @param entityClass entity class
     * @param keys        keys
     * @param <E>    generic entity
     * @return entity
     */
    <E> E getEntityByKeys(Class<E> entityClass, Map<String, Object> keys);

    /**
     * updates entity and returns the status of updated entity
     *
     * @param entity   entity
     * @param <E> generic entity
     * @return update entity status
     */
    <E> UpdateEntityStatus<E> updateEntity(E entity);

}
