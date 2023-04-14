package com.yk.dao;

import com.yk.processor.UpdateEntityStatus;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DAO entity manager class
 */
@Component
@Log4j2
class DAOEntityManagerImp implements DAOEntityManager {

    @Autowired
    private EntityManager entityManager;

    /**
     * gets list of generic entity
     *
     * @param entityClass entity class
     * @param <E>    generic entity
     * @return list of entities
     */
    @Override
    public <E> List<E> getListOfEntities(Class<E> entityClass) {
        CriteriaQuery<E> criteriaQuery = entityManager.getCriteriaBuilder()
                .createQuery(entityClass);
        criteriaQuery.from(entityClass);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * gets generic entry based on provided keys
     *
     * @param entityClass entity class
     * @param keys        keys
     * @param <E>    generic entity
     * @return entity
     */
    @Override
    public <E> E getEntityByKeys(Class<E> entityClass, Map<String, Object> keys) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<E> tRoot = criteriaQuery.from(entityClass);
        List<Predicate> predicates = keys.entrySet().stream()
                .map(keyValue -> criteriaBuilder.and(criteriaBuilder.equal(tRoot.get(keyValue.getKey()), keyValue.getValue())))
                .collect(Collectors.toList());
        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        criteriaQuery.where(finalPredicate);
        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * updates entity and returns the status of updated entity
     *
     * @param entity   entity
     * @param <E> generic entity
     * @return update entity status
     */
    @Override
    @Transactional
    public <E> UpdateEntityStatus<E> updateEntity(E entity) {
        try {
            entityManager.merge(entity);
            return UpdateEntityStatus.<E>builder().status(UpdateEntityStatus.UPDATED).entity(entity).build();
        } catch (PersistenceException e) {
            return UpdateEntityStatus.<E>builder().status(UpdateEntityStatus.FAILED).messages(List.of(e.getMessage())).build();
        }
    }


}
