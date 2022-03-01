package tajbanana.day2.repositories;

public interface SQL {
    public static final String SQL_GET_ALL_TASKS = "SELECT * FROM task ORDER BY username";
    public static final String SQL_GET_USER_BY_USERNAME = "SELECT COUNT(*) user_count FROM user where username = ?";
    public static final String SQL_ADD_NEW_TASK = "INSERT INTO task(username, task_name, priority, due_date) values (?, ?, ?, ?)";
}
