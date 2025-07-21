package ToDoProject;

import java.io.*;
import java.util.ArrayList;

public class database {
    private static final String FILENAME = "tasks.txt";

    public void saveTasks(ArrayList<ToDo> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (ToDo task : tasks) {
                writer.println(task.get_title() + "|" + 
                             task.get_description() + "|" + 
                             task.get_status());
            }
            //System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public ArrayList<ToDo> loadTasks() {
        ArrayList<ToDo> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String title = parts[0];
                    String description = parts[1];
                    boolean status = Boolean.parseBoolean(parts[2]);
                    tasks.add(new ToDo(title, description, status));
                }
            }
            //System.out.println("Tasks loaded successfully!");
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
