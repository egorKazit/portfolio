package com.yk.protfolio.springportfolio.persistence;

import com.yk.protfolio.springportfolio.schema.GeneralValue;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralValueDAOImp implements GeneralValueDAO {

    @Autowired
    private DAOEntityManager daoEntityManager;

    @Override
    public List<GeneralValue> getGeneralValues() {
        return daoEntityManager.getListOfEntities(GeneralValue.class);
    }
}
