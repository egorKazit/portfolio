package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Skill;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO class to work on Skill Entity
 */
@Repository
public class SkillDAOImp implements SkillDAO {

    @Autowired
    private DAOEntityManager daoEntityManager;

    /**
     * gets list of skill entities
     *
     * @return list of skill entities
     */
    @Override
    public List<Skill> getSkills() {
        return daoEntityManager.getListOfEntities(Skill.class);
    }
}
