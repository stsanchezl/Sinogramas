/**
 * Undocumentated methods are getters and setters
 */
package logic;

/**
 * This class is meant to manage the data within the data structures.
 * @author Cristian Davil Camilo Santos Gil
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


    public Archive(String pathToFile, String arrayOrReferences) {
        this.pathToFile = pathToFile;
        if (arrayOrReferences.equals("r")) {
            this.tempList = new LinkedListGeneric<>();
            this.tempQueue = new QueueRefGeneric<>();
            this.tempStack = new StackRefGeneric<>();
        } else {
            this.tempList = new ListArrayGeneric<>(15000000);
            this.tempQueue = new QueueArrayGeneric<>(10000000);
            this.tempStack = new StackArrayGeneric<>(1500000);
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
    public void readText(String selection) throws IOException{
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
                    switch (selection) {
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
    public void addElement(char elementToAdd, String selectDataStructure) {
        switch(selectDataStructure) {
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
     * @param selectDataStructure: Data structure to be used
     */
    public void removeElement(String selectDataStructure) {
        switch(selectDataStructure) {
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
     * @param selectDataStructure: Data structure to be used.
     */
    public void removeAll(String selectDataStructure) {
        Instant firstTime = Instant.now();
        switch(selectDataStructure) {
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
     * @param selectDataStructure: Data structure to be used.
     */
    public void getDataStructureLength(String selectDataStructure) {
        int size;
        switch(selectDataStructure) {
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

    private char stringToChar(String stringToChar) {
        return Character.toChars(Integer.parseInt(stringToChar,16))[0];
    }
}