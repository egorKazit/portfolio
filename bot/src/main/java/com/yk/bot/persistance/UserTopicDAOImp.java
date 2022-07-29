package com.yk.bot.persistance;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.UserTopicAssignment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserTopicDAOImp implements UserTopicDAO {

    @Autowired
    private DAOEntityManager daoEntityManager;

    @Override
    public List<UserTopicAssignment> getUserTopicAssignments() {
        return daoEntityManager.getListOfEntities(UserTopicAssignment.class);
    }
}
