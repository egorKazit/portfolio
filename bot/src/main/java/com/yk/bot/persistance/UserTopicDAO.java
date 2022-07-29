package com.yk.bot.persistance;

import com.yk.schema.UserTopicAssignment;
import java.util.List;

public interface UserTopicDAO {
    List<UserTopicAssignment> getUserTopicAssignments();
}
