package com.yk.protfolio.springportfolio.persistence;

import com.yk.protfolio.springportfolio.utilities.UpdateEntityStatus;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DAOEntityManagerImp implements DAOEntityManager {

    @Autowired
    private EntityManager entityManager;

    @Override
    public <Entity> List<Entity> getListOfEntities(Class<Entity> entityClass) {
        CriteriaQuery<Entity> criteriaQuery = entityManager.getCriteriaBuilder()
                .createQuery(entityClass);
        criteriaQuery.from(entityClass);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public <Entity> Entity getEntityByKeys(Class<Entity> entityClass, Map<String, Object> keys) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entity> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<Entity> tRoot = criteriaQuery.from(entityClass);
        List<Predicate> predicates = keys.entrySet().stream()
                .map(keyValue -> criteriaBuilder.and(criteriaBuilder.equal(tRoot.get(keyValue.getKey()), keyValue.getValue())))
                .collect(Collectors.toList());
        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        criteriaQuery.where(finalPredicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    @Transactional
    public <Entity> UpdateEntityStatus<Entity> updateEntity(Entity entity) {
        try {
            entityManager.merge(entity);
            return UpdateEntityStatus.<Entity>builder().status(UpdateEntityStatus.UPDATED).entity(entity).build();
        } catch (PersistenceException e) {
            return UpdateEntityStatus.<Entity>builder().status(UpdateEntityStatus.FAILED).messages(List.of(e.getMessage())).build();
        }
    }


}
