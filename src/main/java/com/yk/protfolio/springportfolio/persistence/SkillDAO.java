package com.yk.protfolio.springportfolio.persistence;

import com.yk.protfolio.springportfolio.schema.Skill;
import java.util.List;

/**
 * DAO interface to work on Skill Entity
 */
public interface SkillDAO {
    /**
     * gets list of skill entities
     *
     * @return list of skill entities
     */
    List<Skill> getSkills();
}
