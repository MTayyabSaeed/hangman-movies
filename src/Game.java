import java.util.*;

/**
 * Created by muhammadtayyabsaeed on 19/02/18.
 */
public class Game {
    MoviesList moviesList = new MoviesList();
    UserInput userInput = new UserInput();

    private String underscoreName = "";
    public String randomMovie = moviesList.getRandomMovieName();
    private ArrayList<Character> randomMovieChars = new ArrayList<>(randomMovie.length());
    private ArrayList<Character> underscoreNameChars = new ArrayList<>();
    private ArrayList<Character> incorrectEntries = new ArrayList<>();
    private ArrayList<Character> correctEntries = new ArrayList<>();

    private int correct = 0;
    private int incorrect = 0;
    private int whiteSpaceCounter = 0;
    private int counterLimit = 0;
    private int similarCharCounter = 0;

    //converting the movie name to charaters and storing them in an ArrayList
    private ArrayList randomMovieToChar() {
        for (char character :
                randomMovie.toCharArray()){
            if(randomMovieChars.size() >= randomMovie.length())
                break; //restricting the character ArrayList not to exceed than the size of the movie name string
            randomMovieChars.add(character); //adding each character from the foreach loop to randomMovieChars (ArrayList)
        }
        return randomMovieChars;
    }

    //converting the movie name to an ArrayList in a format where whitespaces will be replaced as they are and characters with an underscore
    private String convertMovieNameToUnderscore() {
        Character value;
        for (int i = 0; i < randomMovie.length(); i++) {
            char currentCharater = randomMovie.charAt(i);
            value = Character.valueOf(currentCharater);
            if (Character.isWhitespace(value)) {
                underscoreName += " ";
            } else {
                underscoreName += "_";
            }
        }
        return underscoreName;
    }

    private ArrayList underscoreNameToChars() {
        convertMovieNameToUnderscore();
        for (char charaters: underscoreName.toCharArray()) {
            if (underscoreNameChars.size() >= randomMovie.length())
                break;
            underscoreNameChars.add(charaters);
        }
        return underscoreNameChars;
    }

    private ArrayList<Character> compareMovieName() {
        for (int i = 0; i < randomMovie.length(); i++) {
            convertMovieNameToUnderscore();
            if (randomMovieChars.contains(userInput.getUserInputCharater())) {
                for (int j = 0; j < randomMovieChars.size(); j++) {
                    if (randomMovieChars.get(j) == userInput.getUserInputCharater()) {
                        underscoreNameChars.set(j, userInput.getUserInputCharater());
                    }
                }
            }
        }
        if (!randomMovieChars.contains(userInput.getUserInputCharater())) {
            incorrectEntries.add(userInput.getUserInputCharater());
            entryPrompt();
            return incorrectEntries;
        }
        entryPrompt();
        correctEntries.add(userInput.getUserInputCharater());
        return underscoreNameChars;
    }

    private void entryPrompt() {
        if (underscoreNameChars.contains(userInput.getUserInputCharater())) {
            System.out.println(userInput.getUserInputCharater() + " is CORRECT");
            correct++;
        } else if (!underscoreNameChars.contains(userInput.getUserInputCharater())) { //todo: here the condition is extra
            System.out.println(userInput.getUserInputCharater() + " is INCORRECT");
            incorrect++;
        }
    }

    //get the hint from
    private void hint(){
        System.out.println("HINT: " + moviesList.getRandomMovieHint());
    }

    // the main method that I would
    public void run() {
        randomMovieToChar(); //getting a random movie name
        hint(); //displaying the hint of a particular movie
        underscoreNameToChars();
        gameCounterLimit();
        System.out.println(underscoreNameChars);
        for (int i = 0; i <= counterLimit; i++) {
            if (counterLimit - i > 0 && !hasWon()) {
                System.out.println("Input your guess. You have " + (counterLimit - i) + " turns left");
                userInput.setUserInputCharater(new Scanner(System.in)); //invoking the user for input
                System.out.println("Your input: " + userInput.getUserInputCharater());
                if (underscoreNameChars.contains(userInput.getUserInputCharater())) {
                    i--;
                    System.out.println("You already put that one and it was correct. You still have your current guess.");
                } else if (incorrectEntries.contains(userInput.getUserInputCharater())) {
                    System.out.println("You already put that one and it was incorrect. You still have your current guess.");
                    i--;
                } else {
                    compareMovieName();
                    System.out.println(underscoreNameChars);
                    scorePrompt();
                }
            }
        }
        if(hasWon())
            System.out.println("Congratulations you have won!");
        else{
            System.out.println("Sorry you have lost. Please try one more time");
            System.out.println("The correct word is " + randomMovieChars.toString());
        }
    }

    //prompts the score of the user and the entries he made (correct and incorrect both)
    private void scorePrompt() {
        System.out.println("Your correct score: " + correct);
        System.out.println("Correct Entries: " + correctEntries.toString());
        System.out.println("Your incorrect score: " + incorrect);
        System.out.println("Incorrect Entries: " + incorrectEntries.toString());
    }

    private int whiteSpaceCount() {
        for(int i = 0; i < underscoreNameChars.size(); i++) {
            char whiteSpace = underscoreNameChars.get(i);
            if(Character.isWhitespace(whiteSpace)) {
                whiteSpaceCounter++;
            }
        }
        return whiteSpaceCounter;
    }

    private Boolean hasWon() {
        if (underscoreNameChars.equals(randomMovieChars))
            return true;
        return false;
    }

    private int gameCounterLimit() {
        similarCharCount();
        whiteSpaceCount();
        System.out.println("Movie Name Size: " + randomMovieChars.size()  + "     Number of spaces: " + whiteSpaceCounter + "     Similar letters included: " +  similarCharCounter );
        counterLimit =  randomMovieChars.size() - whiteSpaceCounter - similarCharCounter  + 2;
        return counterLimit;
    }

    //counting letters and checks of they have repetitions, count those repetitions and returns one less then them
    private int similarCharCount() {
        int numberOfCharOccurance;
        char character;
        ArrayList<Character> characterSet = new ArrayList<>(randomMovieChars); //copying rarandomMovieChars to characterSet
        for(int i = 0; i < characterSet.size(); i++) {
            character = characterSet.get(i);
            numberOfCharOccurance = 0;
            for (int j = 0; j < characterSet.size(); j++) {
                if(character == characterSet.get(j)) {
                    if(Character.isWhitespace(character)) //I don't count whitespaces as thats being subtracted
                        continue;
                    numberOfCharOccurance++;
                    if(numberOfCharOccurance > 1)
                        characterSet.remove(Character.valueOf(character));
                }
            }
            System.out.println(numberOfCharOccurance);
            if(numberOfCharOccurance > 1) {
                similarCharCounter = similarCharCounter + numberOfCharOccurance - 1;
            }
        }

        // these two lines do
        // Set characterSet = new HashSet(randomMovieChars);
        // similarCharCounter = characterSet.size();
        return similarCharCounter;
    }

    //this method doesn't do anything inside the code except I checked if randomMovie reference to a method of the MoviesList object returns a null or desired the value
    public void checkNull() {
        if(randomMovie == null){
            System.out.println("This is the output from a null object");
        } else System.out.println("randomMovie is not null          " + randomMovie);
    }
}
