package ToDoProject;

public class ToDo{
    //Values
    private String title;
    private String description;
    private boolean status;

    public ToDo(String title, String description, boolean status){ //Constructor
        this.title = title;
        this.description = description;
        this.status = status;
    }

    //Getter
    public String get_title(){
        return title;
    }

    public String get_description(){
        return description;
    }
    public boolean get_status(){
        return status;
    }

    //Setter
    public void set_title(String newtitle){
        this.title = newtitle;
    }
    public void set_description(String newdescription){
        this.description = newdescription;
    }
    public void set_status(boolean newstatus){
        this.status = newstatus;
    }
}
