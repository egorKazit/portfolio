package com.yk.protfolio.springportfolio.persistence;

import com.yk.schema.UserTopicAssignment;

import java.util.List;

public interface UserTopicDAO {
    List<UserTopicAssignment> getUserTopicAssignments();
}
