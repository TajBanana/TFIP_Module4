package tajbanana.day2.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import tajbanana.day2.models.Task;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static tajbanana.day2.repositories.SQL.*;

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Task> getTasks() {
        List<Task> tasksList = new LinkedList<>();
        final SqlRowSet rowSet = jdbcTemplate.queryForRowSet(SQL_GET_ALL_TASKS);
        while (rowSet.next()) {
            tasksList.add(Task.create(rowSet));
        }
        return tasksList;
    }

    public boolean insertTask(Task task) {
        return (jdbcTemplate
                .update(SQL_ADD_NEW_TASK,
                        task.getUsername(),
                        task.getTaskName(),
                        task.getPriority().toString(),
                        task.getDueDate())) > 0;
    }
}
