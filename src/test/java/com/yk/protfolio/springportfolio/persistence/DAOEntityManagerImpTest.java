package com.yk.protfolio.springportfolio.persistence;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DAOEntityManagerImpTest {

    @Mock
    EntityManager entityManager;

    @InjectMocks
    DAOEntityManagerImp daoEntityManagerImp;

    @Test
    void getListOfEntity() {

        List<MockInterface> mockInterfaces = List.of(new MockInterface() {
        });
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<MockInterface> criteriaQuery = (CriteriaQuery<MockInterface>) mock(CriteriaQuery.class);
        when(criteriaBuilder.createQuery(MockInterface.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(MockInterface.class)).thenAnswer(invocationOnMock -> null);
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        TypedQuery<MockInterface> typedQuery = (TypedQuery<MockInterface>) mock(TypedQuery.class);
        when(typedQuery.getResultList()).thenReturn(mockInterfaces);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        Assertions.assertEquals(mockInterfaces, daoEntityManagerImp.getListOfEntities(MockInterface.class));
    }

    @Test
    void getListOfEntityByKeys() {
        MockInterface mockInterface = new MockInterface() {
        };
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<MockInterface> criteriaQuery = (CriteriaQuery<MockInterface>) mock(CriteriaQuery.class);
        when(criteriaBuilder.createQuery(MockInterface.class)).thenReturn(criteriaQuery);
        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.and(any())).thenReturn(predicate);
        Root<MockInterface> mockInterfaceRoot = (Root<MockInterface>) mock(Root.class);
        Path path = mock(Path.class);
        when(mockInterfaceRoot.get(anyString())).thenReturn(path);
        when(criteriaQuery.from(MockInterface.class)).thenReturn(mockInterfaceRoot);
        when(criteriaBuilder.equal(any(), any())).thenReturn(predicate);
        when(criteriaQuery.where(predicate)).thenAnswer(invocationOnMock -> null);
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        TypedQuery<MockInterface> typedQuery = (TypedQuery<MockInterface>) mock(TypedQuery.class);
        when(typedQuery.getSingleResult()).thenReturn(mockInterface);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        Assertions.assertEquals(mockInterface, daoEntityManagerImp.getEntityByKeys(MockInterface.class, Map.of("id", 1)));
    }

    private interface MockInterface {
    }

}