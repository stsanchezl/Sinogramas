/**
 * Every method is undocumentated for the sake of simplicity
 */
package ui;

/**
 * This class is a basic I/O promtp so we can display or ask the info that is needed for the program to run
 * THIS CLASS WILL BE ERASED IN FUTURE VERSIONS
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 1.0
 * @since 21/10/2020
 */

import java.util.Scanner;

public class CommandLines {
    private static Scanner input = new Scanner(System.in);

    private CommandLines() {
        super();
    }

    public static void menu() {
        System.out.println("Type '1' to insert the characters from the text into the data structure.");
        System.out.println("Type '2' to remove all the characters from the data structure.");
        System.out.println("Type '3' to show the size of the data structure.");
        System.out.println("Type '4' to print the characters.");
        System.out.println("Type '0' to end the program.");
    }
    public static void welcome() {
        System.out.println("****************WELCOME****************");
        System.out.println("This is a try to test the speed for three linear data structures.");
        System.out.println("First, select the linear data structure you'd like to use.");
    }

    public static String input() {
        return input.next();
    }
    public static String input(String toPrint) {
        System.out.println(toPrint);
        return input.next();
    }

    public static String getElement() {
        System.out.println("Type the element to be added");
        return input.next();
    }
    
    public static String getPhrase() {
        System.out.println("Type a phrase");
        return input.nextLine();
    }

    public static void print(String toPrint) {
        System.out.println(toPrint);
    } 

    public static String selectArraysOrReferences() {
        System.out.println("Type 'a' for arrays, type 'r' for references");
        String selection = input.next().toLowerCase();
        while (!(selection.equals("a") || selection.equals("r"))) {
            System.out.println("Type 'a' for arrays, type 'r' for references");
            selection = input.next().toLowerCase();
        }
        return selection;
    }

    public static String selectDataStructure() {
        System.out.println("Type 's' for stack, 'q' for queue or 'l' for list");
        String selection = input.next();
        while (!(selection.equals("s") || selection.equals("q") || selection.equals("l"))) {
            System.out.println("Type 's' for stack, 'q' for queue or 'l' for list");
            selection = input.next().toLowerCase();
        }
        return selection;
    }

    public static String selectOrderOrUnorderedList() {
        System.out.println("Type 'u' for unordered lists, 'o' for ordered lists");
        String selection = input.next();
        while(!(selection.equals("u") || selection.equals("o"))) {
            System.out.println("Type 'u' for unordered lists, 'o' for ordered lists");
            selection = input.next();
        }
        return selection;
    }

    public static int selectSize(String dataStructureType) {
        System.out.println("Type the size of the "+ dataStructureType);
        return Integer.parseInt(input.next());
    }

    public static String stop() {
        System.out.println("Type 'c' to continue or 's' to stop");
        return input.next().toLowerCase();
    }


    public static String stopQueues() {
        System.out.println("Type 'e' for enqueue, 'd' for dequeue or 's' to stop");
        return input.next().toLowerCase();
    }

}
