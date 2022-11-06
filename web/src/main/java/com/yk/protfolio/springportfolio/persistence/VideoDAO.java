package com.yk.protfolio.springportfolio.persistence;

import com.yk.schema.Video;

public interface VideoDAO {
    Video getVideoByName(String name);
}
