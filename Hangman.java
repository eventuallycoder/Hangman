
import java.util.Scanner;

public class Hangman {

    static String[][] wordBank = {
            { "Bane", "Cape", "Cowl", "Croc", "Fear" },
            { "Alley", "Bruce", "Clown", "Crime", "Joker", "Manor", "Quinn", "Robin", "Wayne", "Zsasz" },
            { "Alfred", "Arkham", "Asylum", "Enigma", "Freeze", "Gordon", "Gotham", "Harley", "Knight", "League",
                    "Lucius", "Oracle", "Signal" },
            { "Batcave", "Batgirl", "Batman", "Falcone", "Grapple", "Justice", "Penguin", "Riddler", "Shadows",
                    "Twoface", "Utility" },
            { "Batarang", "Catwoman", "Clayface", "Nightwing" },
            { "Batmobile", "Detective", "Scarecrow", "Vengeance", "Vigilante" }
    };

    static String[] hangmanIterations = { """
            +---+
            |   |
                |
                |
                |
                |
            ---------""",
            """
                    +---+
                    |   |
                    O   |
                        |
                        |
                        |
                    ---------
                        """,
            """
                    +---+
                    |   |
                    O   |
                    |   |
                        |
                        |
                    ---------
                      """,
            """
                     +---+
                     |   |
                     O   |
                    /|   |
                         |
                         |
                     ---------
                       """,
            """

                     +---+
                     |   |
                     O   |
                    /|\\  |
                         |
                         |
                     ---------
                          """,
            """
                     +---+
                     |   |
                     O   |
                    /|\\  |
                    /    |
                         |
                     ---------
                               """,
            """
                     +---+
                     |   |
                     O   |
                    /|\\  |
                    / \\  |
                         |
                      ---------
                       """ };

    //the menu method, thats all
    public static void menu() {
        System.out.println("--------\n(A) Start Game With Random Word Length");
        System.out.println("(B) Choose Word Length For Game");
        System.out.println("(C) Exit\n--------");
    }

    //prints out the current word in underline form
    //This means if the word is 4 letters long and a user guessed 'a' correctly somewhere in the word,
    //this will print out "_ a _ _"
    public static void printOutWord(String [] underlineWordArray)
    {
       // print out your word
        System.out.print("\nYour Word: ");
        for (String s : underlineWordArray) {
            System.out.print(s + " ");
        } 
    }

    //chooses a random word to start the game,
    //This function is specifically if the user selects option (A)
    //because its gets a random word from anywhere in the word bank
    public static String chooseRandomWordForGame() {
        int randomWordLength = (int) (Math.random() * 6);
        return wordBank[randomWordLength][(int) (Math.random() * wordBank[randomWordLength].length - 1)];
    }

    //takes in the word the user will be playing with an makes it an underlined word
    //e.g: "_____". This will later be printed out as "_ _ _ _" by calling printOutWord()
    public static String makeUnderline(String word) {
        String underLineString = "";
        for (int i = 0; i < word.length(); i++) {
            underLineString += "_";
        }

        return underLineString;
    }

    public static String chooseSpecificLengthWordForGame(int letterLength) {

        int index;
        switch (letterLength) {
            case 4:
                index = 0;
                break;
            case 5:
                index = 0;
                break;
            case 6:
                index = 0;
                break;
            case 7:
                index = 0;
                break;
            case 8:
                index = 0;
                break;
            case 9:
                index = 0;
                break;

        }

        return wordBank[letterLength][(int) (Math.random() * wordBank[letterLength].length - 1)];
    }

    public static boolean playHangman(String word) {
        Scanner scan = new Scanner(System.in);

        // keeps track of the ascii image index we want to show the user
        int hangmanIterationsIndex = 0;

        // the original underlined word e.g: " _ _ _ _ "
        String underlineWord = makeUnderline(word);

        //creates a string array from the underlineWord
        String[] underlineWordArray = underlineWord.split("");

        //creates a string array from the actual word
        String[] wordArray = word.split("");

        // print out the initial hangman ascii art and word.
        System.out.println("\nLets play! Good Luck!\n");
        System.out.println(hangmanIterations[hangmanIterationsIndex]);

        printOutWord(underlineWordArray);

        //String that holds the guess for each round
        String guessString = "";

        // guess a letter until win condition or lose condition
        while(hangmanIterationsIndex < hangmanIterations.length) {
            System.out.print("\nWhat letter would you like to guess: ");
            guessString = scan.nextLine();

            if (word.toLowerCase().contains(guessString.toLowerCase())) {
                System.out.println("\nThe letter " + guessString + " is in the word. Good job.");
                System.out.println(hangmanIterations[hangmanIterationsIndex]);

                //Adds the guessed letter anywhere it appeared in the word
                for (int i = 0; i < wordArray.length; i++) {
                    if (wordArray[i].equals(guessString)) {
                        underlineWordArray[i] = guessString;
                    }
                }

                 printOutWord(underlineWordArray);

            } else {
                hangmanIterationsIndex++;
                System.out.println("\nThe letter " + guessString + " is not in the word. So sorry.");
                System.out.println(hangmanIterations[hangmanIterationsIndex]);

                printOutWord(underlineWordArray);
            }
        }
          
        scan.close();

         if(hangmanIterationsIndex < hangmanIterations.length)
            return true;
        else 
            return false;
    }

     

    public static void main(String[] args) {

        System.out.println("Welcome to the Hangman Game");
        System.out.println("Choose from the options below to start a game (Type the letter of option)");

        menu();

        System.out.print("Enter your choice here: ");

        // create a scanner object.
        Scanner scan = new Scanner(System.in);

        // grab the input from the user and make it lower case
        String whatToDo = scan.nextLine();
        whatToDo = whatToDo.toLowerCase();

        // while the input string wasn't exit, we run the menu (the game) again
        while (!whatToDo.equals("c")) {

            String word;

            switch (whatToDo) {
                case "a":
                    word = chooseRandomWordForGame();

                    boolean winCondition = playHangman(word);

                    if(winCondition)
                        System.out.println("Congrats, you won!!!");
                    else
                        System.out.println("Tough luck, try again?");

                    break;

                case "b":
                    System.out.print("\nWhat letter length would you like to play: ");
                    int letterLength = scan.nextInt();
                    // call next line again to move out of error caused by nextInt
                    scan.nextLine();

                    while (letterLength > 12 || letterLength < 4) {
                        System.out.println("Please choose a length greater than 3 or less than 13: ");
                        letterLength = scan.nextInt();

                    }
                    word = chooseSpecificLengthWordForGame(letterLength);

                    playHangman(word);

                    break;
            }

            menu();
            System.out.print("\nWhat would you like to do next: ");

            whatToDo = scan.nextLine();
        }

        System.out.println("Goodbye!");

        // close the scanner
        scan.close();

    }
}
