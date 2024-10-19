package Week3;

import Week3.DbConnect;
import Week3.Status;
import Week3.TaskItem;

public class Main {
    public static void main(String[] args) {
        DbConnect dbConnect = new DbConnect();

        System.out.println("All tasks from the database:");
        dbConnect.getAllTasksFromDb().forEach(task ->
                System.out.println("ID: " + task.getTaskId() + ", Description: " + task.getTaskDescription() + ", Status: " + task.getTaskStatus())
        );

        System.out.println("\nTask with ID 2:");
        TaskItem task = dbConnect.getTaskById(2);
        if (task != null) {
            System.out.println("ID: " + task.getTaskId() + ", Description: " + task.getTaskDescription() + ", Status: " + task.getTaskStatus());
        }

        System.out.println("\nCreating a new task...");
        TaskItem newTask = new TaskItem(5, "Write unit tests", Status.TO_DO);
        dbConnect.createTask(newTask);
        System.out.println("New task created: " + newTask.getTaskDescription());

        System.out.println("\nUpdating task description with ID 1...");
        dbConnect.updateTaskDescription(1, "Push updated lab code to GitHub");
        System.out.println("Task description updated.");
    }
}
