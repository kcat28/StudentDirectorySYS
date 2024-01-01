import javax.swing.*;
import java.awt.*;


public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            } catch (Exception e){
                System.out.println("Error setting look and feel.");
            }
           new MainWindow();
          
        });

    }
}

class MainWindow extends JFrame{

    public MainWindow (){
        setTitle("SSM");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setResizable(false);
        
        JPanel containerJPanel = new JPanel();
        containerJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        containerJPanel.setLayout(new GridLayout(2, 1, 5, 5));
        containerJPanel.setBackground(new Color(162, 207, 113));
        add(containerJPanel, BorderLayout.CENTER);

       JLabel titleLabel = new JLabel("Student System Management", SwingConstants.CENTER);
       titleLabel.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 20));
       containerJPanel.add(titleLabel);

       JPanel buttonPanel = new JPanel();
       buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
       buttonPanel.setBackground(new Color(162, 207, 113));

       JButton newRecordButton = new JButton(" New Student Record "){{
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setFocusable(false);
            addActionListener(e -> new NewRecordWindow());
             addActionListener(e -> dispose());
       }};
      
       JButton openRecordButton = new JButton("Open Student Record"){{
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setFocusable(false);
        addActionListener(e -> new OpenRecordWindow());
        addActionListener(e -> dispose());
       }};
      

       buttonPanel.add(openRecordButton);
       buttonPanel.add(newRecordButton);
       containerJPanel.add(buttonPanel);
       

        
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
}