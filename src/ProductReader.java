import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProductReader
{

        static void main()
        {
            JFileChooser chooser = new JFileChooser(".");
            chooser.setDialogTitle("Select a Product data file");

            if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            {
                System.out.println("No file selected. Exiting.");
                return;
            }
            Path file = chooser.getSelectedFile().toPath();
            System.out.println();
            System.out.println(String.format("%-10s %-14s %-28s %-10s", "ID#", "Name", "Description", "Cost"));

            System.out.println("=".repeat(55));
            try (BufferedReader reader = Files.newBufferedReader(file))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    String[] parts = line.split(",");

                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String description = parts[2].trim();
                    double cost = Double.parseDouble(parts[3].trim());

                    System.out.println(String.format("%-10s %-14s %-28s %-10s", id, name, description, cost));
                }
            }
            catch (IOException e)
            {
                System.out.println("Error reading the file: " + e.getMessage());
            }
            System.out.println("You picked:" + file.toAbsolutePath());
        }
    }
