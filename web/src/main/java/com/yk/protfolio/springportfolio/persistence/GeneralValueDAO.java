package com.yk.protfolio.springportfolio.persistence;

import com.yk.schema.GeneralValue;
import java.util.List;

/**
 * DAO interface to work on General Entity
 */
public interface GeneralValueDAO {
    /**
     * gets list of generic values
     *
     * @return list of generic values
     */
    List<GeneralValue> getGeneralValues();
}
