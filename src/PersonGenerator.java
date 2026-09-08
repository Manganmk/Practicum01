import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {

    static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ArrayList<String> people = new ArrayList<>();
        boolean moreData = true;
        while (moreData)
        {
            String id = SafeInput.getNonZeroLenString(in, "Please enter ID");
            String firstName = SafeInput.getNonZeroLenString(in, "Please enter your first name");
            String lastName = SafeInput.getNonZeroLenString(in, "Please enter your last name");
            String title = SafeInput.getNonZeroLenString(in, "Please enter your title");
            int YOB = SafeInput.getRangedInt(in, "Please enter your year of birth",1,2026);
            String record = id + "," + firstName + "," + lastName + "," + title + "," + YOB;
            people.add(record);

            moreData = SafeInput.getYNConfirm(in, "Do you want to enter another person?");
        }
        String fileName = SafeInput.getNonZeroLenString(in, "Please enter your file name");
        Path file = Path.of(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(file))
        {
            for ( String person : people )
            {
                writer.write(person);
                writer.newLine();
            }
            System.out.println("Saved " + people.size() + " records to"+file.toAbsolutePath());
        }
        catch (IOException e)
        {
            System.out.println("Something went wrong:" + e.getMessage());
        }

    }

}
