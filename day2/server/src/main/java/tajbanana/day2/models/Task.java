package tajbanana.day2.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Task {
    private String username;
    private Integer taskId;
    private String taskName;
    private Priority priority;
    private Date dueDate;

    public static Task create(SqlRowSet rowSet) {
        final Task task = new Task();
        task.taskId = rowSet.getInt("tid");
        task.username = rowSet.getString("username");
        task.taskName = rowSet.getString("task_name");
        task.priority = Enum.valueOf(
                Priority.class, rowSet.getString("priority"));
        task.dueDate = rowSet.getDate("due_date");
        return task;
    }

    public static Task create(String jsonString) throws ParseException {
        JsonReader jsonReader = Json.createReader(
                new ByteArrayInputStream(
                        jsonString.getBytes(StandardCharsets.UTF_8)));
        JsonObject jsonObject = jsonReader.readObject();

        final Task task = new Task();

        try {
            task.taskId = jsonObject.getInt("id",-1);
        } catch (Exception ignored) {}

        task.username = jsonObject.getString("username");
        task.taskName = jsonObject.getString("taskName");
        task.priority = Enum.valueOf(
                Priority.class, jsonObject.getString("priority"));
        task.dueDate = (new SimpleDateFormat("yyyy-MM-dd"))
                .parse(jsonObject.getString("dueDate"));

        return task;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id",taskId)
                .add("username", username)
                .add("taskName", taskName)
                .add("priority", priority.toString())
                .add("dueDate", dueDate.toString())
                .build();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}




