package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Skill;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO class to work on Skill Entity
 */
@Repository
@AllArgsConstructor
public class SkillDAOImp implements SkillDAO {

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
