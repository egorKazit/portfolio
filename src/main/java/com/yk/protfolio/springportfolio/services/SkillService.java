package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.schema.Skill;
import java.util.List;

/**
 * Service interface to work on skill
 */
public interface SkillService {
    /**
     * gets list of skills
     *
     * @return list of skills
     */
    List<Skill> getSkills();
}
