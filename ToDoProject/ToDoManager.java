package ToDoProject;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import java.util.ArrayList;

public class ToDoManager{
    private ArrayList<ToDo> tasks = new ArrayList<>();
    private database db;

    public ToDoManager() {
        db = new database();
        tasks = db.loadTasks(); 
    }

    public void saveTasks(){
        db.saveTasks(tasks);
    }
    public void add(Scanner scanner){
        System.out.println("\nEnter task title: ");
        String title = scanner.nextLine();
        
        System.out.println("\nEnter task description: ");
        String description = scanner.nextLine();

        ToDo newtask = new ToDo(title, description, false);
        tasks.add(newtask);
        System.out.println("\nTask added!");
        list();
    }

    public void list(){
        for(int i = 0; i < tasks.size(); i++){
            ToDo task = tasks.get(i);
            if(task != null)
                System.out.println("\n[" + i + "] " + task.get_title() +" - "+task.get_description() + " [" + (task.get_status() ? "Done" : "Pending") + "]");
        }
    }

    public void markDone(Scanner scanner){
        System.out.println("\nWhich task do you want to mark done(Position task): ");
        String input = scanner.nextLine();
        int Position;
        try{
            Position = Integer.parseInt(input);
            if(Position < 0 || Position >= tasks.size())
                System.out.println("\nInvalid Position!");
            else{
                tasks.get(Position).set_status(true);
                System.out.println("\nSuccessfully marked!");
                list();
            }
        }catch(NumberFormatException e){
            System.out.println("\nInvalid input!");
            }   
    }

    public void removeTask(Scanner scanner){
        System.out.println("\nWhich task do you want to remove(Position): ");
        String input = scanner.nextLine();
        int Position;

        try{
            Position = Integer.parseInt(input);
            if(Position < 0 || Position >= tasks.size())
                System.out.println("\nInvalid Position!");
            else{
                tasks.remove(Position);
                System.out.println("\nSuccessfully removed!");
                list();
            }
        }catch(NumberFormatException e){
            System.out.println("\nInvalid input!");
        } 
    }

    public ArrayList<ToDo> getTasks() {
    return tasks;
    }
    public static void main(String[] args) {
        ToDoManager manager = new ToDoManager();
        Scanner scanner = new Scanner(System.in);
        SwingUtilities.invokeLater(() -> new SwingToDo(manager).createInterface());

        Runtime.getRuntime().addShutdownHook(new Thread(()-> manager.saveTasks()));
    }
}