package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.GeneralValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DAO class to work on General Entity
 */
@Service
@AllArgsConstructor
public class GeneralValueDAOImp implements GeneralValueDAO {

    private DAOEntityManager daoEntityManager;

    /**
     * gets list of generic values
     *
     * @return list of generic values
     */
    @Override
    public List<GeneralValue> getGeneralValues() {
        return daoEntityManager.getListOfEntities(GeneralValue.class);
    }
}
