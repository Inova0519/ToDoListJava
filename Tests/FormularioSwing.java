package Tests;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class FormularioSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormularioSwing().createUI();
        });
    }

    public void createUI() {
        JFrame frame = new JFrame("Formulario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // === LEFT LABEL PANEL ===
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(6, 1, 10, 10));
        labelPanel.setBackground(new Color(202, 230, 197)); // Light green

        labelPanel.add(new JLabel("Nombre"));
        labelPanel.add(new JLabel("Clave"));
        labelPanel.add(new JLabel("Género"));
        labelPanel.add(new JLabel("Idiomas"));
        labelPanel.add(new JLabel("Grado"));
        labelPanel.add(new JLabel("Comentarios"));

        // === RIGHT FORM PANEL ===
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(6, 1, 10, 10));

        // 1. Name
        JTextField nameField = new JTextField();
        fieldPanel.add(nameField);

        // 2. Password
        JPasswordField passwordField = new JPasswordField();
        fieldPanel.add(passwordField);

        // 3. Gender
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton maleButton = new JRadioButton("Masculino");
        JRadioButton femaleButton = new JRadioButton("Femenino");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        fieldPanel.add(genderPanel);

        // 4. Languages
        JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox spanishCheck = new JCheckBox("Español");
        JCheckBox englishCheck = new JCheckBox("Ingles");
        JCheckBox italianCheck = new JCheckBox("Italiano");
        languagePanel.add(spanishCheck);
        languagePanel.add(englishCheck);
        languagePanel.add(italianCheck);
        fieldPanel.add(languagePanel);

        // 5. Grade
        JComboBox<String> gradeCombo = new JComboBox<>(new String[]{"Primaria", "Secundaria", "Preparatoria"});
        fieldPanel.add(gradeCombo);

        // 6. Comments
        JTextArea commentArea = new JTextArea(4, 20);
        JScrollPane commentScroll = new JScrollPane(commentArea);
        fieldPanel.add(commentScroll);

        // === CENTER FORM ===
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(labelPanel, BorderLayout.WEST);
        centerPanel.add(fieldPanel, BorderLayout.CENTER);

        // === BOTTOM BUTTONS ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton sendButton = new JButton("Enviar");
        JButton exitButton = new JButton("Salir");
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(sendButton);
        buttonPanel.add(exitButton);

        // === FINAL SETUP ===
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}
