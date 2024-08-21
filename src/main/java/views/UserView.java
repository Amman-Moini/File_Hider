
package views;

import dao.DataDAO;
import model.Data;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private String email;

    UserView(String email) {
        this.email = email;
    }

    public void home() {
        do {
            System.out.println("Welcome " + this.email);
            System.out.println("Press 1 to show hidden files");
            System.out.println("Press 2 to hide a new file");
            System.out.println("Press 3 to unhide a file");
            System.out.println("press 0 to exit");
            Scanner sc = new Scanner(System.in);
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1 -> {
                    try {
                        List<Data> files = DataDAO.getAllFiles(this.email);
                        System.out.println("ID - File Name");
                        for (Data file : files) {
                            System.out.println(file.getId() + " - " + file.getFileName());
                        }
                    } catch (SQLException e) {
                        System.out.println("Error occurred while fetching files: " + e.getMessage());
                    }
                }
                case 2 -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Select a file to hide");
                    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        Data file = new Data(0, selectedFile.getName(), selectedFile.getAbsolutePath(), this.email);
                        try {
                            DataDAO.hideFile(file);
                        } catch (SQLException | IOException e) {
                            System.out.println("Error occurred while hiding the file: " + e.getMessage());
                        }
                    }
                }
                case 3 -> {
                    // Your existing code for unhiding files
                }
                case 0 -> {
                    System.exit(0);
                }
            }
        } while (true);
    }
}