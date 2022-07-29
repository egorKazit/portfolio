package com.yk.protfolio.springportfolio.persistence;

import com.yk.schema.Social;
import java.util.List;

/**
 * DAO interface to work on Slide Entity
 */
public interface SocialDAO {
    /**
     * gets list of social entities
     *
     * @return list of social entities
     */
    List<Social> getSocials();
}
