import java.io.File;

public class main {
    public static void main(String[] args) {
        String directory = "mydir";
        String filename = "myfile.txt";

        // Construct a file path using File.separator
        String filePath = directory + File.separator + filename;

        System.out.println("File path: " + filePath);
    }
}