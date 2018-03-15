import java.util.*;

/**
 * Created by muhammadtayyabsaeed on 15/02/18.
 */
public class MoviesList {

    FileUploader fileUploader = new FileUploader();

    private String movieName;
    private ArrayList<String> moviesList = new ArrayList<>();
    private String randomMovie;
    private String randomMovieHint;
    ArrayList<String> arrayListNameHint = null;

    protected void createMoviesList() {
        fileUploader.fileUpload();
        while (fileUploader.getMovieScanner().hasNextLine()) {
            movieName = fileUploader.getMovieScanner().nextLine();
            moviesList.add(movieName);
        }
    }

    public void printMoviesList() {
        createMoviesList();
        System.out.println("The list has these movies: " + moviesList);
    }

    protected String getRandomMovieHint(){
        randomMovieHint = arrayListNameHint.get(1);
        return randomMovieHint;
    }
    protected ArrayList<String> createRandomMovieAndHint(){
        createMoviesList();
        Random random = new Random();
        randomMovieHint = moviesList.get(random.nextInt(moviesList.size()));
        arrayListNameHint = new ArrayList<>(Arrays.asList(randomMovieHint.split(",")));
        return arrayListNameHint;
    }

    protected String getRandomMovieName(){
        createRandomMovieAndHint();
        randomMovie = arrayListNameHint.get(0);
        return randomMovie;
    }

    public void checkNull(){
        if(randomMovie == null){
            System.out.println("\n\n\nThis is the output from a null object. This is the output from MoviesList.java " + randomMovie);
        } else System.out.println("\n\n\nrandomMovie is not null" + randomMovie + " " + " This is the output from MoviesList.java");
    }
}
