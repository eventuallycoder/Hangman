import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {

    static String[][] wordBank = {
  {"Bane", "Cape", "Cowl", "Croc", "Fear"},
  {"Alley", "Bruce", "Clown", "Crime", "Joker", "Manor", "Quinn", "Robin", "Wayne", "Zsasz"},
  {"Alfred", "Arkham", "Asylum", "Enigma", "Freeze", "Gordon", "Gotham", "Harley", "Knight", "League", "Lucius", "Oracle", "Signal"},
  {"Batcave", "Batgirl", "Batman", "Falcone", "Grapple", "Justice", "Penguin", "Riddler", "Shadows", "Twoface", "Utility"},
  {"Batarang", "Catwoman", "Clayface", "Nightwing"},
  {"Batmobile", "Detective", "Scarecrow", "Vengeance", "Vigilante"}
};

    static String[] hangmanIterations = { """
            +---+
            |   |
                |
                |
                |
                |""",
            """
                    +---+
                    |   |
                    O   |
                        |
                        |
                        |
                          """,
            """
                    +---+
                    |   |
                    O   |
                    |   |
                        |
                        |
                      """,
            """
                     +---+
                     |   |
                     O   |
                    /|   |
                         |
                         |
                       """,
            """

                     +---+
                     |   |
                     O   |
                    /|\\  |
                         |
                         |

                          """,
            """
                     +---+
                     |   |
                     O   |
                    /|\\  |
                    /    |
                         |
                               """,
            """
                     +---+
                     |   |
                     O   |
                    /|\\  |
                    / \\  |
                         |
                       """ };

    public static void menu() {
        System.out.println("--------\n(A) Start Game With Random Word Length");
        System.out.println("(B) Choose Word Length For Game");
        System.out.println("(C) Exit\n--------");
    }

    public static String chooseRandomWordForGame() {
        int randomWordLength = (int)(Math.random() * 6);
        return wordBank[randomWordLength][(int) (Math.random() * wordBank[randomWordLength].length - 1)];
    }

    public static String chooseSpecificLengthWordForGame(int letterLength) {

        int index;
        switch (letterLength)
        {
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

    public static void playHangman(String word)
    {

    }

    public static void main(String[] args) {

        System.out.println("Welcome to the Hangman Game");
        System.out.println("Choose from the options below to start a game (Type letter of option)");

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
                    
                    playHangman(word);

                    break;

                case "b":
                    System.out.println("What letter length would you like to play: ");
                    int letterLength = scan.nextInt();

                    while (letterLength > 12 || letterLength < 4) {
                        System.out.println("Please choose a length greater than 3 or less than 13: ");
                        letterLength = scan.nextInt();
                    }

                    word = chooseSpecificLengthWordForGame(letterLength);
                    System.out.println("Word: " +word);

                    break;
            }

            menu();
            System.out.print("What would you like to do next: ");
            whatToDo = scan.nextLine();
        }

        System.out.println("Goodbye!");

        // close the scanner
        scan.close();

    }
}
