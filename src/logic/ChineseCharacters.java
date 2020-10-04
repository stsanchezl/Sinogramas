/**
 * Undocumentated methods are getters and setters
 */
package logic;

/**
 * This class is a test class where we try to read and store han characters in different data structures
 * Currently, we only use referenced structures as it is not clear the size of any structure 
 * We open a txt with the characters and we parsed them and add them to different data structures
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 1.1
 * @since 02/10/2020
 */

import data.*;
import ui.CommandLines;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ChineseCharacters {

    private String regex; //Regular expresion to find info in the given text
    private String pathToFile;  //Self-explanatory
    private BufferedReader text;  //Representation of the text in memory
    private StackGeneric<Character> tempStack; 
    private ListGeneric<Character> tempList;
    private QueueGeneric<Character> tempQueue;

    /**
     * Constructor: gets the path where txt is and initialites the strucures to use
     * @param pathToFile: path where the txt is in each pc
     */
    public ChineseCharacters(String pathToFile) {
        this.pathToFile = pathToFile;
        this.tempStack = new StackRefGeneric<>();
        this.tempList = new LinkedListGeneric<>();
        this.tempQueue = new QueueRefGeneric<>();
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
     * @throws FileNotFoundException .
     */
    public void closeFile() throws IOException{
        this.text.close();
    }
    
    /**
     * This method reads a line: parse a complete line and skips over the next
     * @return readed line - null if it is the end of the file;
     * @throws IOException 
     */
    public String readLine() throws IOException{
        return text.readLine();
    }

    /**
     * This method takes the text loaded in memory, looks everyline of it
     * Using regex, it finds the unicode characters and parse them so they can be added to the structures
     * And print the time right before and right after the symbols are store so speed test can be performed
     * @param selection: data structure to be used
     * @throws IOException 
     */
    public void readText(String selection) throws IOException{
        
        if (this.regex!= null) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            CommandLines.print(currentTime.toString());
            String currentLine = readLine();
            Pattern pattern;
            Matcher matcher;
            while (currentLine!=null) {
                pattern = Pattern.compile(this.regex);
                matcher = pattern.matcher(currentLine);
                if (matcher.find()) {
                    String found = matcher.group();
                    char elementToAdd = Character.toChars(Integer.parseInt(found.substring(2), 16))[0];
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
                    tempQueue.enqueue(elementToAdd);
                }
                currentLine = readLine();
            }
            currentTime = new Timestamp(System.currentTimeMillis());
            CommandLines.print(currentTime.toString());
        } else {
            System.err.println("No regex found, set one using setRegex() method.");
        }
    }
}
