package com.yk.protfolio.springportfolio.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.yk.protfolio.springportfolio.schema.Skill;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SkillDAOImpTest {

    @Mock
    DAOEntityManager daoEntityManager;

    @InjectMocks
    private SkillDAOImp skillDAOImp;

    @Test
    void getSkills() {
        List<Skill> skills = List.of(new Skill(), new Skill());
        when(daoEntityManager.getListOfEntities(Skill.class)).thenReturn(skills);
        assertEquals(skills, skillDAOImp.getSkills());
    }
}