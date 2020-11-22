/**
 * Undocumentated methods are getters and setters
 */
package logic;

/**
 * This class is meant to manage the data within the data structures. It can
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 1.0
 * @since 16/10/2020
 */

import data.*;
import ui.CommandLines;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Archive {

    private String pathToFile;  //Path to file in one person's computer
    private String regex; //Regular expresion to find info in the given text
    private BufferedReader text;  //Representation of the text in memory
    private Scanner scan;
    
    
    private ListGeneric<Integer> tempList;
    private QueueGeneric<Integer> tempQueue;
    private StackGeneric<Integer> tempStack;
    private BSTRefGeneric<Integer> tempBST;
    private HeapArray<Integer> tempHeap;

    private String dataStructure;
    private String ordered;
    
    private boolean needSort = false;
    private boolean needHeapSort = false;

    /**
     * Class constructor, it initializates a specific linear data structure so they can hold the
     * Han characters, it does not matter the implementation of the data structure
     * @param arrayOrReferences: Implementation of the array
     * @param dataStructure: Data structure to be used.
     * @param ordered: Tell whether the structure is ordered or not
     * @param pathToFile: String with the path to the file to the Unihan text
     */
    public Archive(String arrayOrReferences, String dataStructure, String ordered, String pathToFile) {
        this.dataStructure = dataStructure;
        this.ordered = ordered;
        this.pathToFile = pathToFile;
        switch (dataStructure) {
            case "l":
                if (ordered.equals("u")) {
                    needSort = true;
                    if (arrayOrReferences.equals("a")) {
                        this.tempList = new UnorderedListArrayGeneric<>(99999999);
                    } else if (arrayOrReferences.equals("r")) {
                        this.tempList = new UnorderedListRefGeneric<>();
                    } else {
                        this.tempList = new UnorderedListDynamicArrayGeneric<>();
                    }
                } else {
                    if (arrayOrReferences.equals("a")) {
                        this.tempList = new ListArrayGeneric<>(1500000);
                    } else if (arrayOrReferences.equals("r")) {
                        this.tempList = new LinkedListGeneric<>();
                    } else {
                        this.tempList = new ListDynamicArrayGeneric<>();
                    }
                } 
            case "q":
                if (arrayOrReferences.equals("a")) {
                    this.tempQueue = new QueueArrayGeneric<>(1500000);
                } else if (arrayOrReferences.equals("r")) {
                    this.tempQueue = new QueueRefGeneric<>();
                } else {
                    this.tempQueue = new QueueDynamicArrayGeneric<>();
                }
            case "s":
                if (arrayOrReferences.equals("a")) {
                    this.tempStack = new StackArrayGeneric<>(1500000);
                } else if (arrayOrReferences.equals("r")) {
                    this.tempStack = new StackRefGeneric<>();
                } else {
                    this.tempStack = new StackDynamicArrayGeneric<>();
                }
            case "t":
                this.tempBST = new BSTRefGeneric<>();
            case "h":
                needHeapSort = true;
                this.tempHeap = new HeapArray<>(100000000);
            default:
                break;
        }
    }

    public String getPathToFile() {
        return this.pathToFile;
    }
    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }
    public StackGeneric<Integer> getTempStack() {
        return this.tempStack;
    }
    public ListGeneric<Integer> getTempList() {
        return this.tempList;
    }
    public QueueGeneric<Integer> getTempQueue() {
        return this.tempQueue;
    }
    public String getRegex () {
        return this.regex;
    }
    public void setRegex(String regex) {
        this.regex = regex;
    }

    /**
     * This method loads a file into memory so it can be used
     * @throws FileNotFoundException should the path be wrong or file non-existant
     */
    public void openFile() throws FileNotFoundException{
        this.text = new BufferedReader(new FileReader(pathToFile));
        this.scan = new Scanner(new FileReader(pathToFile));
    }

    /**
     * This method de-loads a file off memory
     * @throws FileNotFoundException
     */
    public void closeFile() throws IOException{
        this.text.close();
        this.scan.close();
    }
    
    /**
     * This method reads a line: parse a complete line and skips over the next
     * @return read line - null if it is the end of the file
     * @throws IOException 
     */
    public String readLine() throws IOException{
        return text.readLine();
    }
    
    // not used
    public String nextLine() throws IOException{
        return scan.nextLine();
    }

    /**
     * This method takes the text loaded in memory, looks everyline of it
     * Using regex, it finds the unicode characters and parse them so they can be added to the structures
     * And print the time it takes to store all characters it the structures so speed test can be performed
     * @param selection: data structure to be used
     * @throws IOException 
     */
    public void readText() throws IOException{
        // if (this.regex!= null) {
            Instant firstTime = Instant.now();
            String currentLine = scan.nextLine();
            Pattern pattern;
            Matcher matcher;
            while (scan.hasNextLine()) {
                //pattern = Pattern.compile(this.regex);
                //matcher = pattern.matcher(currentLine);
                //if (matcher.find()) {
                    // String found = matcher.group();
                    // char elementToAdd = stringToChar(found.substring(2));
                    // System.out.println(currentLine);
                    // char elementToAdd = stringToChar(currentLine);
                    Integer elementToAdd = Integer.parseInt(currentLine);
                    // System.out.println(elementToAdd);
                    switch (this.dataStructure) {
                        case "l":
                            tempList.insert(elementToAdd);
                            break;
                        case "q":
                            tempQueue.enqueue(elementToAdd);
                            break;
                        case "s":
                            tempStack.push(elementToAdd);
                            break;  
                        case "t":
                            tempBST.insertBST(elementToAdd);
                            break;
                        case "h":
                            tempHeap.insertItem(elementToAdd);
                            break;
                        default:
                            break;
                    }
                currentLine = scan.nextLine();    
            }
                
            //}
            if (needSort) tempList.sort();
            if (needHeapSort) tempHeap.sort();
            Instant lastTime = Instant.now();
            String totalTime = Duration.between(firstTime, lastTime).toString();
            CommandLines.print("It took "+totalTime+ " to insert all the characters.");
        /*} else {
            throw new UnsupportedOperationException("No regex found, try first setRegex method.");
        }*/
    }

    /**
     * This method adds a character to a specific data structure
     * @param elementToAdd: Char to be added
     * @param selectDataStructure: Data structure to be used
     */
    public void addElement(Integer elementToAdd) {
        switch(this.dataStructure) {
            case "l":
                this.tempList.insert(elementToAdd);
                break;
            case "q":
                this.tempQueue.enqueue(elementToAdd);
                break;
            case "s":
                this.tempStack.push(elementToAdd);
                break;
            default:
                break;
        }
    }

    /**
     * This method removes a character from a specific data structure
     */
    public void removeElement() {
        
        switch(this.dataStructure) {
            case "l":
                Integer toDelete = Integer.parseInt(CommandLines.input("Char to remove:"));
                System.out.println(toDelete);
                Instant firstTime = Instant.now();
                this.tempList.delete(toDelete);
                Instant lastTime = Instant.now();
                String totalTime = Duration.between(firstTime, lastTime).toString();
                CommandLines.print("It took "+totalTime+ " to delete this character.");
                break;
            case "q":
                this.tempQueue.dequeue();
                break;
            case "s":
                this.tempStack.pop();
                break;
            default:
                break;
        }
    }

    /**
     * This method removes all the elements from a specific data structure and displays in console
     * the time it took to do it.
     */
    public void removeAll() {
        Instant firstTime = Instant.now();
        switch(this.dataStructure) {
            case "l":
                throw new UnsupportedOperationException("Not yet implemented");
            case "q":
                while (!tempQueue.empty()) {
                    this.tempQueue.dequeue();
                }
                break;
            case "s":
                while (!tempStack.empty()) {
                    this.tempStack.pop();
                }
                break;
            default:
                break;
        }
        Instant lastTime = Instant.now();
        String totalTime = Duration.between(firstTime, lastTime).toString();
        CommandLines.print("It took "+totalTime+ " to delete all the characters.");
    }

    /**
     * This method shows the length of the data struture.
     */
    public void getDataStructureLength() {
        int size;
        switch(this.dataStructure) {
            case "l":
                size = tempList.length();
                break;
            case "q":
                size = tempQueue.length();
                break;
            case "s":
                size = tempStack.length();
                break;
            default:
                size = -1;
                break;
        }
        CommandLines.print(String.valueOf("There are "+ size +" elements in the structure."));
    }

    /**
     * This method prints the characters of the data structures
     */
    public void print() {
        switch(this.dataStructure) {
            case "l":
                CommandLines.print(tempList.toString());
                break;
            case "q":
                CommandLines.print(tempQueue.toString());
                break;
            case "s":
                CommandLines.print(tempStack.toString());
                break;
            default:
                break;
        }
    }

    /**
     * This method converts a string with the integer representation to its unicode equivalance
     * to a char; e.g. "3400" to '\u3400'.
     * @param stringToChar String to be converted
     * @return the unicode equivalence character of Unihan
     */
    private char stringToChar(String stringToChar) {
        return Character.toChars(Integer.parseInt(stringToChar,16))[0];
    }

    
}