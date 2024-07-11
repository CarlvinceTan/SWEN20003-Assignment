package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 * Class that contains methods to read a CSV file and a properties file.
 * You may edit this as you wish.
 */
public class IOUtils {

    /***
     * Method that reads a CSV file and return a 2D String array
     * @param csvFile: the path to the CSV file
     * @return 2D String array
     */
    public static String[][] readCsv(String csvFile) {
        String[][] data = null;
        try {
            File file = new File(csvFile);
            Scanner scanner = new Scanner(file);

            // Initialize array
            int rows = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                rows++;
            }
            data = new String[rows][3];

            // Reset Scanner
            scanner.close();
            scanner = new Scanner(file);
            scanner.useDelimiter(",");

            // Read CSV
            int row = 0;
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                System.arraycopy(line, 0, data[row], 0, 3);
                row++;
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    /***
     * Method that reads a properties file and return a Properties object
     * @param configFile: the path to the properties file
     * @return Properties object
     */
    public static Properties readPropertiesFile(String configFile) {
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(configFile));
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        return appProps;
    }
}