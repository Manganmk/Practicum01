import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersonReader
{
    static void main()
    {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setDialogTitle("Select a Person data file");

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
        {
            System.out.println("No file selected. Exiting.");
            return;
        }
        Path file = chooser.getSelectedFile().toPath();
        System.out.println();
        System.out.println(String.format("%-10s %-12s %-14s %-8s %-6s", "ID", "First Name", "Last Name", "Title", "YOB"));
        System.out.println("=".repeat(55));
        try (BufferedReader reader = Files.newBufferedReader(file))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");

                String id = parts[0].trim();
                String firstName = parts[1].trim();
                String lastName = parts[2].trim();
                String title = parts[3].trim();
                int YOB = Integer.parseInt(parts[4].trim());

                System.out.println(String.format("%-10s %-12s %-14s %-8s %-6d", id, firstName, lastName, title, YOB));
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        System.out.println("You picked:" + file.toAbsolutePath());
    }
}
