import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;
public class RecordingFunc{
    
    String studentNo;
    String program_TF;
    String yearOpt;
    String firstName_TF;
    String  middleName_TF;
    String lastName_TF;
    String nationality_TF; 
    String sexOpt; 
    boolean fileResult;

    RecordingFunc(String studentNoField, String programField, String yearOptField, String firstNameField, String middleNameField, String lastNameField, String nationalityField, String sexOptField) {
        this.studentNo = studentNoField;
        this.program_TF = programField;
        this.yearOpt = yearOptField;
        this.firstName_TF = firstNameField;
        this.middleName_TF = middleNameField;
        this.lastName_TF = lastNameField;
        this.nationality_TF = nationalityField;
        this.sexOpt = sexOptField;

        writeToTextFile();
        if(!fileResult) {
        new NewRecordWindow();
        } 
      

    }
    
      private void writeToTextFile() {
        fileResult = false;

        if(studentNo == null || studentNo.isEmpty() || program_TF == null || program_TF.isEmpty() || yearOpt == null || yearOpt.isEmpty()|| firstName_TF == null || firstName_TF.isEmpty() || middleName_TF == null || middleName_TF.isEmpty() || lastName_TF == null || lastName_TF.isEmpty() || nationality_TF == null || nationality_TF.isEmpty() || sexOpt == null || sexOpt.isEmpty()){
            JOptionPane.showMessageDialog(null, "Fill out all blank fields");
        } else {

        try {
            int studentNoInt = Integer.parseInt(studentNo);
            boolean fileExists = new File(studentNoInt + ".txt").exists();
            
            if(fileExists) {
                JOptionPane.showMessageDialog(null, " ID number registered, try again");
                return;
            }
            
            if(!isAlphabetic(program_TF) || !isAlphabetic(firstName_TF) || !isAlphabetic(middleName_TF)
            || !isAlphabetic(lastName_TF) || !isAlphabetic(nationality_TF) || !isAlphabetic(sexOpt)) {

                JOptionPane.showMessageDialog(null, "Invalid input. Fields must contain only letters.");
                return;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(studentNoInt+".txt"));
            // Write student information to the file

            writer.write(String.valueOf(studentNoInt));
            writer.newLine();
            writer.write(program_TF);
            writer.newLine();
            writer.write(yearOpt);
            writer.newLine();
            writer.write(firstName_TF);
            writer.newLine();
            writer.write(middleName_TF);
            writer.newLine();
            writer.write(lastName_TF);
            writer.newLine();
            writer.write(nationality_TF);
            writer.newLine();
            writer.write(sexOpt);
            writer.close();
            JOptionPane.showMessageDialog(null, "Student information written to file successfully.");
            fileResult = true;
            new MainWindow();
        } catch(NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Please input a valid ID no");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error writing student information to file.");
        }}
}

    private static boolean isAlphabetic(String str){
        return str.matches("^[a-zA-Z]+(?:\s[a-zA-Z]+)*$");
    }


}
