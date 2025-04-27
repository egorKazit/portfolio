package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.UserTopicAssignment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserTopicDAOImp implements UserTopicDAO {

    private final DAOEntityManager daoEntityManager;

    @Override
    public List<UserTopicAssignment> getUserTopicAssignments() {
        return daoEntityManager.getListOfEntities(UserTopicAssignment.class);
    }
}
