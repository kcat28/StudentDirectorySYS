
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class OpenRecordWindow extends JFrame {

    private JTextField ID_textField;
    private JComboBox<String> searchresultBox;

    OpenRecordWindow() {
        
        setTitle("Student Records");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,155);
        setResizable(false);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(2,2,5,0));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        searchPanel.setBackground(new Color(162,207,113));

        JLabel ID_searchLabel = new JLabel("Student no: ");
        ID_textField = new JTextField();
        ID_textField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateComboBox();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateComboBox();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });

        
        searchPanel.add(ID_searchLabel);
        searchPanel.add(ID_textField);

        JLabel searchResuLabel = new JLabel("Search result: ");
        searchPanel.add(searchResuLabel);

        searchresultBox = new JComboBox<>();
        searchPanel.add(searchresultBox);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        buttonPanel.setBackground(new Color(162,207,113));

        JButton openButton = new JButton("Open") {{
        setFocusable(false);
            addActionListener(e -> openSelectedFile());

        }};

        JButton cancelButton = new JButton("Cancel") {{
        setFocusable(false);
            addActionListener(e -> new MainWindow());
            addActionListener(e -> dispose());
        }};


        buttonPanel.add(openButton);
        buttonPanel.add(cancelButton);
        add(searchPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);



        setLocationRelativeTo(null);
        setVisible(true);
         

        
    }


    private void updateComboBox() {
        String idNumb = ID_textField.getText();
        boolean fileExists = new File(idNumb + ".txt").exists();
        searchresultBox.removeAllItems();

        if (fileExists) {

            searchresultBox.addItem(idNumb + ".txt");
        } 
    }

     private void openSelectedFile() {
        String selectedFile = (String) searchresultBox.getSelectedItem();

        if (selectedFile != null) {
            try {
                Path filePath = Paths.get(selectedFile);
                List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

                displayFileContent(lines);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading file");
            }
        }
    }

    private void displayFileContent(List<String> lines) {
        // Create a new window to display the content (similar to NewRecordWindow)
        JFrame contentFrame = new JFrame("Public Student Info");
        contentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentFrame.setSize(300, 280);  // Set the size accordingly
        contentFrame.setResizable(false);
    
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(8, 1, 5, 0));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(new Color(162, 207, 113));
    
        // Populate contentPanel with labels and text fields based on the lines
        String[] labels = {"Student No:", "Program:", "Year Level:", "First name:",
                "Middle name:", "Last name:", "Nationality:", "Sex:"};
    
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            JTextField textField = new JTextField(lines.get(i));
            textField.setEditable(false); 
            contentPanel.add(createLabelAndFieldPanel(label, textField));
        }

        JPanel buttoPanel = new JPanel(); 
        buttoPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        buttoPanel.setBackground(new Color(162, 207, 113));
          
        JButton closeButton = new JButton("Close"){{
            setFocusable(false);
            addActionListener(e -> contentFrame.dispose());
            addActionListener(e -> new OpenRecordWindow());
            dispose();
        }};
        buttoPanel.add(closeButton);

        contentFrame.add(contentPanel, BorderLayout.CENTER);
        contentFrame.add(buttoPanel, BorderLayout.SOUTH);

        contentFrame.setLocationRelativeTo(null);
        contentFrame.setVisible(true);
    }

 private JPanel createLabelAndFieldPanel(JLabel label, JComponent component) {
        JPanel panel = new JPanel(new GridLayout(1,2));
        panel.add(label);
        panel.add(component);
        panel.setBackground(new Color(162,207,113));
        return panel;
    }
    

}
