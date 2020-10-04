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

    public static String input() {
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


    public static String dataTypeSelection() {
        System.out.println("What kind of data type you would like to use?");
        System.out.println("Type i for int, c for char, s for string");
        return input.next().toLowerCase();
    }

    public static void print(String toPrint) {
        System.out.println(toPrint);
    } 

    public static void reverseMessage(String dataType) {
        System.out.println("The reverse "+ dataType+"s are:");
    }

    public static String selectArraysOrReferences() {
        System.out.println("Type a or A for arrays, type r or R for references");
        String selection = input.next().toLowerCase();
        while (!(selection.equals("a") || selection.equals("r"))) {
            System.out.println("Type a or A for arrays, type r or R for references");
            selection = input.next().toLowerCase();
        }
        return selection;
    }

    public static String selectDataStructure() {
        System.out.println("Type 's' for stack, 'q' for queue or 'l' for list");
        return input.next();
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
