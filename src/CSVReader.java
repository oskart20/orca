import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVReader {

    String folderPath;

    public CSVReader(){
        // this.folderPath = folderPath;

    }

    public String[][] readCSV(String path) {

        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        String[] row;
        String[][] rows = new String[0][0];
        try {
            Path pathObject = Paths.get(path);
            long lineCount = Files.lines(pathObject).count();
            br = new BufferedReader(new FileReader(path));
            System.out.println(lineCount);
            rows = new String[(int) lineCount][2];
            int i = 0;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                row = line.split(cvsSplitBy);
                rows[i] = row;
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rows;

    }

}