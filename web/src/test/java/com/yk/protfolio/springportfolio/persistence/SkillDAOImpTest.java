package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.protfolio.springportfolio.configuration.BotRegistration;
import com.yk.schema.Skill;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SkillDAOImpTest {

    @Mock
    DAOEntityManager daoEntityManager;

    @InjectMocks
    private SkillDAOImp skillDAOImp;

    @MockitoBean
    BotRegistration botRegistration;

    @Test
    void getSkills() {
        List<Skill> skills = List.of(new Skill(), new Skill());
        when(daoEntityManager.getListOfEntities(Skill.class)).thenReturn(skills);
        assertEquals(skills, skillDAOImp.getSkills());
    }
}