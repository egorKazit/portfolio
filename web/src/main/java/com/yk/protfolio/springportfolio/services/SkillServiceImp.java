package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.SkillDAO;
import com.yk.schema.Skill;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to work on skill
 */
@Service
@AllArgsConstructor
public class SkillServiceImp implements SkillService {

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
