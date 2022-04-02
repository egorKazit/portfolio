package com.yk.protfolio.springportfolio.persistence;

import com.yk.protfolio.springportfolio.schema.Social;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DAO class to work on Slide Entity
 */
@Service
public class SocialDAOImp implements SocialDAO {

    @Autowired
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
