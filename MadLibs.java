import java.io.*;
import java.util.*;

public class MadLibs {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        intro();
        String choice = "";
        while(!choice.equalsIgnoreCase("Q")){
            choice = menu(console);
            if (choice.equalsIgnoreCase("C") || choice.equalsIgnoreCase("V")) {
                File inputFile = input(console);
                if(choice.equalsIgnoreCase("C")){
                    PrintStream output = output(console);
                    create(console, inputFile, output);
                } else {
                    System.out.println();
                    view(console, inputFile);
                }
            }
        }
        System.out.println();
    }

    // prints intro message
    public static void intro() {
        System.out.println("Welcome to the game of Mad Libs.");
        System.out.println("I will ask you to provide various words");
        System.out.println("and phrases to fill in a story.");
        System.out.println("The result will be written to an output file.");
        System.out.println();
    }

    // user choice menu
    // returns users choice of continuing
    // parameters:
    //      Scanner console - scanner for user input
    public static String menu(Scanner console) {
        System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
        String choice = console.nextLine();
        return choice;
    }

    // asks user for input file and tests if it exists
    // returns input file
    // parameters:
    //      Scanner console - scanner for user input
    public static File input(Scanner console) throws FileNotFoundException {
        System.out.print("Input file name: ");
        String inFileName = console.nextLine();
        File inputFile = new File(inFileName);
        // test for if the input file exists
        while (!inputFile.exists()) {
            System.out.print("File not found. Try again: ");
            inFileName = console.nextLine();
            inputFile = new File(inFileName);
        }
        return inputFile;
    }

    // asks user for output file name and creates output file
    // returns output file print PrintStream
    // parameters:
    //      Scanner console - scanner for user input
    public static PrintStream output(Scanner console) throws FileNotFoundException {
        System.out.print("Output file name: ");
        String outFileName = console.nextLine();
        File outputFile = new File(outFileName);
        PrintStream output = new PrintStream(outputFile);
        System.out.println();
        return output;

    }

    // creates mad lib
    // parameters:
    //      Scanner console - scanner for user input and file reading
    //      File inputFile - file to be used
    //      PrintStream output - printer to output file
    public static void create(Scanner console, File inputFile, PrintStream output)
    throws FileNotFoundException {
        Scanner madLib = new Scanner(inputFile);
        // analyzes a single line from the mad lib
        while (madLib.hasNextLine()) {
            String line = madLib.nextLine();
            Scanner scanLine = new Scanner(line);
            // analyzes a single word from a line
            while (scanLine.hasNext()) {
                String word = scanLine.next();
                // finds placeholders
                if (word.startsWith("<") && word.endsWith(">")) {
                    int length = word.length();
                    length--;
                    word = word.substring(1, length);
                    char firstLetter = word.charAt(0);
                    String firstLet = firstLetter + "";
                    if (firstLet.equalsIgnoreCase("a") || firstLet.equalsIgnoreCase("e") || 
                    firstLet.equalsIgnoreCase("i") || firstLet.equalsIgnoreCase("o") || 
                    firstLet.equalsIgnoreCase("u")) {
                        System.out.print("Please type an ");
                    } else {
                        System.out.print("Please type a ");
                    }
                    word = word.replace("-", " ");
                    System.out.print(word + ": ");
                    String userWord = console.nextLine();
                    output.print(userWord + " ");
                } else {
                    output.print(word + " ");
                }
            }
            output.println("");
        }
        System.out.println("Your mad-lib has been created!");
        System.out.println();
    }

    // view madLib
    // parameters:
    //      Scanner console - scanner for user input and file reading
    //      File inputFile - file to be used
    public static void view(Scanner console, File inputFile) throws FileNotFoundException {
        Scanner viewMadLib = new Scanner(inputFile);
        while (viewMadLib.hasNextLine()) {
            String printLine = viewMadLib.nextLine();
            System.out.println(printLine);
        }
        System.out.println(); 
    }
}
