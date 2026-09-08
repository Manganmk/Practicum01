import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter
{

        static void main(String[] args)
        {
            Scanner in = new Scanner(System.in);
            ArrayList<String> people = new ArrayList<>();
            boolean moreData = true;
            while (moreData)
            {
                String ID = SafeInput.getNonZeroLenString(in, "Please enter ID");
                String Name = SafeInput.getNonZeroLenString(in, "Please enter Product name");
                String Description = SafeInput.getNonZeroLenString(in, "Please enter your description");
                double Cost = SafeInput.getRangedDouble(in, "Enter the cost", 0.0, 10000.0);
                String record = ID + "," + Name + "," + Description + "," + Cost;
                people.add(record);

                moreData = SafeInput.getYNConfirm(in, "Do you want to enter another product?");
            }
            String fileName = SafeInput.getNonZeroLenString(in, "Please enter your file name");
            Path file = Path.of(fileName.trim());

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


