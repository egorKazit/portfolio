package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.configuration.BotRegistration;
import com.yk.protfolio.springportfolio.persistence.SkillDAOImp;
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
class SkillServiceImpTest {

    @Mock
    SkillDAOImp skillDAOImp;

    @InjectMocks
    SkillServiceImp skillServiceImp;

    @MockitoBean
    BotRegistration botRegistration;

    @Test
    void getSkills() {
        List<Skill> skills = List.of(new Skill(), new Skill());
        when(skillDAOImp.getSkills()).thenReturn(skills);
        assertEquals(skills, skillServiceImp.getSkills());
    }
}