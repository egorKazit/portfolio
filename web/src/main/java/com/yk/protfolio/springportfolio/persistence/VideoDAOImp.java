package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Video;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VideoDAOImp implements VideoDAO {

    private final ConcurrentMap<String, Video> videoBuffer = new ConcurrentHashMap<>();

    @Autowired
    private DAOEntityManager daoEntityManager;

    @Override
    public Video getVideoByName(String name) {
        return videoBuffer.computeIfAbsent(name, nameOfNewVideo -> daoEntityManager.getEntityByKeys(Video.class, Map.of("name", name)));
    }
}
