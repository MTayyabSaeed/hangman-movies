import java.util.*;

/**
 * Created by muhammadtayyabsaeed on 19/02/18.
 */
public class UserInput {

    MoviesList moviesList = new MoviesList();

    private char userInputCharater;
    protected ArrayList<Character> inputList = new ArrayList<>();

    //set the charater input by the user
    protected void setUserInputCharater(Scanner scanner ) {
        userInputCharater = scanner.next().charAt(0);
        if(Character.isUpperCase(userInputCharater)){
            userInputCharater =  Character.toLowerCase(userInputCharater);
        }
    }

    //convert the user userInputCharater to lowercase if they insert UPPERCASE LETTER
    protected char getUserInputCharater(){
            return userInputCharater;
    }


    //todo: unused code (must be removed from here but I am waiting till the end of the project)
    //not used for the moment
    protected void setInputArrayList (ArrayList inputList) {
            setUserInputCharater(new Scanner(System.in));
            this.inputList = inputList;
            inputList.add(this.getUserInputCharater());
    }
    //not used for the moment
    protected ArrayList getInputArayList(){
        return inputList;
    }

}
