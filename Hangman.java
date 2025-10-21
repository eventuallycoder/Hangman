
import java.util.Scanner;

public class Hangman {

    static String[][] wordBank = {
            /*Length 4*/{ "Bane", "Cape", "Cowl", "Croc", "Fear" },
            { "Alley", "Bruce", "Clown", "Crime", "Joker", "Manor", "Quinn", "Robin", "Wayne", "Zsasz" },
            { "Alfred", "Arkham", "Asylum", "Enigma", "Freeze", "Gordon", "Gotham", "Harley", "Knight", "League",
                    "Lucius", "Oracle", "Signal","Batman" },
            { "Batcave", "Batgirl", "Falcone", "Grapple", "Justice", "Penguin", "Riddler", "Shadows",
                    "Twoface", "Utility" },
            { "Batarang", "Catwoman", "Clayface", "Nightwing" },
            /*Length 9*/{ "Batmobile", "Detective", "Scarecrow", "Vengeance", "Vigilante" }
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
        System.out.println("(C) Exit");
        System.out.println("--------");
    }

    //prints out the current word in underline form
    //This means if the word is 4 letters long and a user guessed 'a' correctly somewhere in the word,
    //this will print out "_ a _ _"
    public static void printOutWord(String [] underlineWordArray)
    {
       // print out your word
        System.out.print("\nYour Word: ");

        for(int i = 0; i < underlineWordArray.length;i++)
        {
            if(i==0 && (!underlineWordArray[i].equals("_")))
            {
                System.out.print(underlineWordArray[i].toUpperCase() + " ");            
            }
            else 
                System.out.print(underlineWordArray[i] + " ");  
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
         
        String returnString = "";
        switch (letterLength) {
            case 4:
                returnString = wordBank[0][(int) (Math.random() * wordBank[0].length - 1)];
                break;
            case 5:
                returnString = wordBank[1][(int) (Math.random() * wordBank[1].length - 1)];
                break;
            case 6:
                returnString = wordBank[2][(int) (Math.random() * wordBank[2].length - 1)];
                break;
            case 7:
                returnString = wordBank[3][(int) (Math.random() * wordBank[3].length - 1)];
                break;
            case 8:
                returnString = wordBank[4][(int) (Math.random() * wordBank[4].length - 1)];
                break;
            case 9:
                returnString = wordBank[5][(int) (Math.random() * wordBank[5].length - 1)];
                break;

        }

        return returnString;
    }

    public static boolean playHangman(String word) {
        Scanner scan2 = new Scanner(System.in);

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

        //Print out the initial underlined word (The word we don't know yet but are guessing)
        printOutWord(underlineWordArray);

        //String that holds the guess for each round
         String guessString = "";

        // // guess a letter until win lose condition
         boolean finishedWord = false;
         while(hangmanIterationsIndex < hangmanIterations.length-1 && finishedWord == false) {
            System.out.print("\nWhat letter would you like to guess: ");
            guessString = scan2.nextLine();
            
           //this string is used so that we can keep the original case format of guess string
             String lowerCaseGuessString = guessString.toLowerCase();

             if (word.toLowerCase().contains(lowerCaseGuessString)) {
                 System.out.println("\nThe letter " + guessString + " is in the word. Good job.");
                 System.out.println(hangmanIterations[hangmanIterationsIndex]);

                 //Adds the guessed letter anywhere it appeared in the word
                 for (int i = 0; i < wordArray.length; i++) {
                     if (wordArray[i].toLowerCase().equals(lowerCaseGuessString)) {
                         underlineWordArray[i] = guessString;
                     }
                 }
                  printOutWord(underlineWordArray);

             } else {
                 hangmanIterationsIndex++;
                 System.out.println("\nThe letter " + guessString + " is not in the word. So sorry.");

                 //only print out the next ascii image if the index is less than the array length
                 if(hangmanIterationsIndex < hangmanIterations.length){
                     System.out.println(hangmanIterations[hangmanIterationsIndex]);
                     printOutWord(underlineWordArray);
                 }   
             }

             //check if the user finished word, and if they did set boolean flag to false
             int wordArrayLength = wordArray.length;
             int wordArrayLengthCheck = 0;
             for(int i = 0;i< wordArray.length;i++)
             {

                 if(wordArray[i].toLowerCase().equals(underlineWordArray[i].toLowerCase()))
                  wordArrayLengthCheck++;
             }

             if(wordArrayLength == wordArrayLengthCheck)
             {
                 finishedWord = true;
                 break;
             }
            
         }

          if(finishedWord)
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
     

        // while the input string wasn't exit, we run the menu (the game) again
        while (!whatToDo.equals("c")) {

            String word;
             

            switch (whatToDo.toLowerCase()) {
                 case "a":
                     word = chooseRandomWordForGame();
                      

                     boolean winCondition = playHangman(word);

                     if(winCondition)
                     {
                         System.out.println("\nCongrats, you won!!!" + " The word was '" + word + "'");
                     }
                     else
                     {
                         System.out.println("\n" + hangmanIterations[hangmanIterations.length-1]);
                         System.out.println("Tough luck, try again?");
                     }

                     break;

                 case "b":
                     System.out.print("\nWhat letter length would you like to play: ");
                     int letterLength = scan.nextInt();
                     // call next line again to move out of error caused by nextInt
                     scan.nextLine();

                     while (letterLength > 9 || letterLength < 4) {
                         System.out.print("Please choose a length greater than 3 and less than 10: ");
                         letterLength = scan.nextInt();
                         scan.nextLine();

                     }
                     word = chooseSpecificLengthWordForGame(letterLength);

                    winCondition = playHangman(word);

                    if(winCondition)
                     {
                         System.out.println("\nCongrats, you won!!!" + " The word was '" + word + "'");
                     }
                     else
                     {
                         System.out.println("\n" + hangmanIterations[hangmanIterations.length-1]);
                         System.out.println("Tough luck, try again?");
                     }

                     break;
                 default:

                     System.out.println("\n\nInput: '" + whatToDo + "' is not an option. Please try entering 'A', 'B', or 'C'.");
            }

            menu();
            System.out.print("What would you like to do: ");
             
            whatToDo = scan.nextLine();
        }

        System.out.println("Goodbye!");

        // close the scanner
        scan.close();

    }
}
