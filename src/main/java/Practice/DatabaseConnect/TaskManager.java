package Practice.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

enum TaskStatus {
    TO_DO,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}

class TaskItem {
    private int id;
    private String taskDescription;
    private TaskStatus taskStatus;

    public TaskItem(int id, String taskDescription, TaskStatus taskStatus) {
        this.id = id;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "id=" + id +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStatus=" + taskStatus +
                '}';
    }
}

class TaskManager {
    private List<TaskItem> tasks = new ArrayList<>();

    public TaskManager() {
        tasks = Arrays.asList(
                new TaskItem(1,"Push lab code to the github", TaskStatus.TO_DO),
                new TaskItem(2,"Prepare for the quiz", TaskStatus.IN_PROGRESS),
                new TaskItem(3,"Go over tasks from lab2", TaskStatus.COMPLETED));
    }

    public List<TaskItem> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskItem> tasks) {
        this.tasks = tasks;
    }

    public List<TaskItem> getTasksByStatus(TaskStatus taskStatus){
        return tasks.stream()
                .filter(task -> task.getTaskStatus() == taskStatus)
                .collect(Collectors.toList());
    }

    public List<TaskItem> getTasksWithGreaterId(int id){
        return tasks.stream()
                .filter(task -> task.getId() >= id)
                .collect(Collectors.toList());
    }

    public void printTaskDescriptions(){
        tasks.forEach(task -> System.out.println(task.getTaskDescription()));
    }
}

class DbConnect {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/tasksdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hFW5yLylRpx!=2)";

    private Connection connection = null;

    public DbConnect(){
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public List<TaskItem> getTasksFromDb(){
        List<TaskItem> tasks = new ArrayList<>();

        String query = "SELECT * FROM tasks1";

        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt("id");
                String taskDescription = rs.getString("task_description");
                TaskStatus taskStatus = TaskStatus.valueOf(rs.getString("task_status"));
                tasks.add(new TaskItem(id, taskDescription, taskStatus));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return tasks;
    }

    public TaskItem getTaskById(int id){
        TaskItem task = null;
        String query = "SELECT * FROM tasks1 WHERE id = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                int taskId = rs.getInt("id");
                String taskDescription = rs.getString("task_description");
                TaskStatus taskStatus = TaskStatus.valueOf(rs.getString("task_status"));
                task = new TaskItem(taskId, taskDescription, taskStatus);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return task;
    }

    public void createTask(TaskItem task){
        String query = "INSERT INTO tasks1 (id, task_description, task_status)" +
                "VALUES (?, ?, ?)";

        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, task.getId());
            pstmt.setString(2, task.getTaskDescription());
            pstmt.setString(3, (task.getTaskStatus().name()));

            pstmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateTaskDescription(int id, String description){
        String query = "UPDATE tasks1 SET task_description = ? WHERE id = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1, description);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        TaskItem task1 = new TaskItem(1, "Prepare for exam", TaskStatus.IN_PROGRESS);
        TaskItem task2 = new TaskItem(2, "Write unit tests", TaskStatus.TO_DO);
        TaskItem task3 = new TaskItem(3, "Night out", TaskStatus.CANCELLED);

        DbConnect dbConnect = new DbConnect();
        dbConnect.createTask(task1);
        dbConnect.createTask(task2);
        dbConnect.createTask(task3);

        System.out.println(dbConnect.getTasksFromDb());
    }
}
