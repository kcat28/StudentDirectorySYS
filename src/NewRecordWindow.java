import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewRecordWindow extends JFrame {
    private JTextField studentNo_TF;
    private JTextField program_TF;
    private JComboBox<String> yearOpt;
    private JTextField firstName_TF;
    private JTextField middleName_TF;
    private JTextField lastName_TF;
    private JTextField nationality_TF;
    private JComboBox<String> sexOpt;
    


    NewRecordWindow (){

        setTitle("New Student Record");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 280);
        setResizable(false);

        sexOpt = new JComboBox<>(new String[]{"Male", "Female", "Prefer not to say"});
        yearOpt = new JComboBox<>(new String[]{"1st Year", "2nd Year", "3rd Year", "4th Year"});

        JPanel signUP_panel = new JPanel();
        signUP_panel.setLayout(new GridLayout(8,2,5,0));
        signUP_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        signUP_panel.setBackground(new Color(162, 207, 113));

        // sigunup content
        
        JLabel studentNo = new JLabel("Student No: ");
        studentNo_TF = new JTextField();
        signUP_panel.add(createLabelAndFieldPanel(studentNo, studentNo_TF));        

        JLabel program = new JLabel("Program: ");
        program_TF = new JTextField();
        signUP_panel.add(createLabelAndFieldPanel(program, program_TF));
        
        JLabel yearLevel = new JLabel("Year Level: ");
        signUP_panel.add(createLabelAndFieldPanel(yearLevel, yearOpt));

        JLabel firstName = new JLabel("First name: ");
        firstName_TF = new JTextField();
        signUP_panel.add(createLabelAndFieldPanel(firstName, firstName_TF));

        JLabel middleName = new JLabel("Middle name: ");
        middleName_TF = new JTextField();
        signUP_panel.add(createLabelAndFieldPanel(middleName, middleName_TF));

        JLabel lastName = new JLabel("Last name: ");
        lastName_TF = new JTextField();
        signUP_panel.add(createLabelAndFieldPanel(lastName, lastName_TF));
        
        JLabel nationality = new JLabel("Nationality: ");
        nationality_TF = new JTextField();
        signUP_panel.add(createLabelAndFieldPanel(nationality, nationality_TF));

        JLabel sex = new JLabel("Sex: ");
        signUP_panel.add(createLabelAndFieldPanel(sex, sexOpt));

        // button panel and periph

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        buttonPanel.setBackground(new Color(162, 207, 113));

        JButton submButton = new JButton("Submit") {{
            setFocusable(false);
            addActionListener(e -> new RecordingFunc(getStudentNo_TF(), getProgram_TF(), getYearOpt(),
            getFirstName_TF(), getMiddleName_TF(), getLastName_TF(), getNationality_TF(),
            getSexOpt()));
            addActionListener(e -> dispose());
        }};

        JButton cancButton = new JButton("Cancel") {{
            setFocusable(false);
            addActionListener(e -> new MainWindow());
            addActionListener(e -> dispose());
        }};

        buttonPanel.add(submButton);
        buttonPanel.add(cancButton);

        add(signUP_panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


        setLocationRelativeTo(null);
        setVisible(true);


    }

    private JPanel createLabelAndFieldPanel(JLabel label, Component field) {
        JPanel panel = new JPanel(new GridLayout(1,2));
        panel.add(label);
        panel.add(field);
        panel.setBackground(new Color(162,207,113));
        return panel;}


         public String getStudentNo_TF() {
        return studentNo_TF.getText();
    }

    public String getProgram_TF() {
        return program_TF.getText();
    }

    public String getYearOpt() {
        return (String) yearOpt.getSelectedItem();
    }

    public String getFirstName_TF() {
        return firstName_TF.getText();
    }

    public String getMiddleName_TF() {
        return middleName_TF.getText();
    }

    public String getLastName_TF() {
        return lastName_TF.getText();
    }

    public String getNationality_TF() {
        return nationality_TF.getText();
    }

    public String getSexOpt() {
        return (String) sexOpt.getSelectedItem();
    }
}
          
    



