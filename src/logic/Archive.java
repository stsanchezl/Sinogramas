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

public class Archive {

    private String pathToFile;  //Path to file in one person's computer
    private String regex; //Regular expresion to find info in the given text
    private BufferedReader text;  //Representation of the text in memory

    private ListGeneric<Character> tempList;
    private QueueGeneric<Character> tempQueue;
    private StackGeneric<Character> tempStack;

    private String dataStructure;
    private String ordered;

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
                    if (arrayOrReferences.equals("a")) {
                        this.tempList = new UnorderedListArrayGeneric<>(1500000);
                    } else {
                        this.tempList = new UnorderedListRefGeneric<>();
                    }
                } else {
                    if (arrayOrReferences.equals("a")) {
                        this.tempList = new ListArrayGeneric<>(1500000);
                    } else {
                        this.tempList = new LinkedListGeneric<>();
                    }
                } 
            case "q":
                if (arrayOrReferences.equals("a")) {
                    this.tempQueue = new QueueArrayGeneric<>(1500000);
                } else {
                    this.tempQueue = new QueueRefGeneric<>();
                }
            case "s":
                if (arrayOrReferences.equals("a")) {
                    this.tempStack = new StackArrayGeneric<>(1500000);
                } else {
                    this.tempStack = new StackRefGeneric<>();
                }
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
    public StackGeneric<Character> getTempStack() {
        return this.tempStack;
    }
    public ListGeneric<Character> getTempList() {
        return this.tempList;
    }
    public QueueGeneric<Character> getTempQueue() {
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
    }

    /**
     * This method de-loads a file off memory
     * @throws FileNotFoundException
     */
    public void closeFile() throws IOException{
        this.text.close();
    }
    
    /**
     * This method reads a line: parse a complete line and skips over the next
     * @return read line - null if it is the end of the file
     * @throws IOException 
     */
    public String readLine() throws IOException{
        return text.readLine();
    }

    /**
     * This method takes the text loaded in memory, looks everyline of it
     * Using regex, it finds the unicode characters and parse them so they can be added to the structures
     * And print the time it takes to store all characters it the structures so speed test can be performed
     * @param selection: data structure to be used
     * @throws IOException 
     */
    public void readText() throws IOException{
        if (this.regex!= null) {
            Instant firstTime = Instant.now();
            String currentLine = readLine();
            Pattern pattern;
            Matcher matcher;
            while (currentLine!=null) {
                pattern = Pattern.compile(this.regex);
                matcher = pattern.matcher(currentLine);
                if (matcher.find()) {
                    String found = matcher.group();
                    char elementToAdd = stringToChar(found.substring(2));
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
                        default:
                            break;
                    }
                }
                currentLine = readLine();
            }
            Instant lastTime = Instant.now();
            String totalTime = Duration.between(firstTime, lastTime).toString();
            CommandLines.print("It took "+totalTime+ " to insert all the characters.");
        } else {
            throw new UnsupportedOperationException("No regex found, try first setRegex method.");
        }
    }

    /**
     * This method adds a character to a specific data structure
     * @param elementToAdd: Char to be added
     * @param selectDataStructure: Data structure to be used
     */
    public void addElement(char elementToAdd) {
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
                char toDelete = stringToChar(CommandLines.input("Char to remove:"));
                this.tempList.delete(toDelete);
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