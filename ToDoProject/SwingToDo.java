package ToDoProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SwingToDo {
    private ToDoManager manager;

    private JTextField titleField;
    private JTextField descriptionField;
    private JComboBox<String> statusCombo;
    private DefaultListModel<String> taskListModel;

    public SwingToDo(ToDoManager manager) {
        this.manager = manager;
    }

    public void createInterface() {
        // === Frame setup ===
        JFrame frame = new JFrame("To Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        // === Main panel ===
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // === Labels ===
        JPanel labelPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        labelPanel.add(new JLabel("Title"));
        labelPanel.add(new JLabel("Description"));
        labelPanel.add(new JLabel("Status"));

        // === Fields ===
        JPanel fieldPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        titleField = new JTextField();
        descriptionField = new JTextField();
        statusCombo = new JComboBox<>(new String[]{"Pending", "Done"});
        fieldPanel.add(titleField);
        fieldPanel.add(descriptionField);
        fieldPanel.add(statusCombo);

        // === Combine Labels and Fields ===
        JPanel formPanel = new JPanel(new BorderLayout(10, 10));
        formPanel.add(labelPanel, BorderLayout.WEST);
        formPanel.add(fieldPanel, BorderLayout.CENTER);

        // === Task List Display ===
        taskListModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // === Buttons ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Task");
        JButton setDoneButton = new JButton("Set Done");
        JButton removeButton = new JButton("Remove Selected");
        JButton exitButton = new JButton("Exit");

        // === Add Task Button Logic ===
        addButton.addActionListener((ActionEvent e) -> {
            String title = titleField.getText().trim();
            String desc = descriptionField.getText().trim();
            String statusStr = (String) statusCombo.getSelectedItem();

            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Title cannot be empty!");
                return;
            }

            boolean status = statusStr.equals("Done");
            ToDo task = new ToDo(title, desc, status);
            manager.getTasks().add(task); // Add to backend
            updateTaskList();             // Refresh GUI
            titleField.setText("");
            descriptionField.setText("");
            statusCombo.setSelectedIndex(0);
        });

        setDoneButton.addActionListener((ActionEvent e) -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0 && index < manager.getTasks().size()) {
                manager.getTasks().get(index).set_status(true);
                updateTaskList();
            } else {
                JOptionPane.showMessageDialog(frame, "Select a valid task to remove.");
            }
        });

        // === Remove Task Button Logic ===
        removeButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0 && index < manager.getTasks().size()) {
                manager.getTasks().remove(index);
                updateTaskList();
            } else {
                JOptionPane.showMessageDialog(frame, "Select a valid task to remove.");
            }
        });
        

        // === Exit Button Logic ===
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(addButton);
        buttonPanel.add(setDoneButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(exitButton);

        // === Assemble Everything ===
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);

        updateTaskList(); // initial refresh
    }

    // === Helper Method to Refresh the Task List Display ===
    private void updateTaskList() {
        taskListModel.clear();
        for (ToDo task : manager.getTasks()) {
            if (task != null) {
                String display = "📌 " + task.get_title() + " - " + task.get_description() + " [" + (task.get_status() ? "Done" : "Pending") + "]";
                taskListModel.addElement(display);
            }
        }
    }
}
