package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Social;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DAO class to work on Slide Entity
 */
@Service
@AllArgsConstructor
public class SocialDAOImp implements SocialDAO {

    private DAOEntityManager daoEntityManager;

    /**
     * gets list of social entities
     *
     * @return list of social entities
     */
    @Override
    public List<Social> getSocials() {
        return daoEntityManager.getListOfEntities(Social.class);
    }
}
