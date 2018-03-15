import java.io.*;
import java.util.*;

/**
 * Created by muhammadtayyabsaeed on 15/02/18.
 */
public class FileUploader {

    private File movieFile = new File("movies.txt");
    private Scanner movieScanner = null;

    protected Scanner getMovieScanner(){
        return movieScanner;
    }

    protected void fileUpload() {
        try{
            movieScanner = new Scanner(movieFile);
            if(movieFile.exists())
                System.out.println("File exists and scanned");
        } catch (FileNotFoundException exception){
            System.out.println("File not found");
        }
    }
}
