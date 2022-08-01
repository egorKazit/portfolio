package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.SkillDAO;
import com.yk.schema.Skill;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to work on skill
 */
@Service
public class SkillServiceImp implements SkillService {

    @Autowired
    private SkillDAO skillDAO;

    /**
     * gets list of skills
     *
     * @return list of skills
     */
    @Override
    public List<Skill> getSkills() {
        return skillDAO.getSkills();
    }
}
