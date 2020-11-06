/**
 * Undocumented methods are getters and setters
 */
package logic.sinogramas;

/**
 * This class is meant to manage the data within the data structures.
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 1.0
 * @since 16/10/2020
 */

import android.os.Build;
import androidx.annotation.RequiresApi;
import data.sinogramas.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.Duration;
import java.time.Instant;

public class Archive {

    private boolean needSort = false;
    private String dataStructure; //data structure to use.
    private String regex = "[U].[\\dA-Z]{4,5}"; //Regular expression to find info in the given text
    private BufferedReader text;  //Representation of the text in memory
    private InputStream textToParse;

    private ListGeneric<Character> tempList;
    private QueueGeneric<Character> tempQueue;
    private StackGeneric<Character> tempStack;

    /**
     * Class constructor, it initializes a specific linear data structure so they can hold the
     * Han characters, it does not matter the implementation of the data structure
     * @param arrayOrReferences: Implementation of the array
     * @param dataStructure: Data structure to be used.
     * @param ordered: whether the structure is ordered or not
     * @param textToParse: InputStream with the file so it can be put onto a BufferReader
     */
    public Archive(String arrayOrReferences, String dataStructure, String ordered, InputStream textToParse) {
        this.dataStructure = dataStructure;
        this.textToParse = textToParse;

        switch (dataStructure) {
            case "l":
                if (ordered.equals("u")) {
                    needSort = true;
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
                break;
            case "q":
                if (arrayOrReferences.equals("a")) {
                    this.tempQueue = new QueueArrayGeneric<>(1500000);
                } else {
                    this.tempQueue = new QueueRefGeneric<>();
                }
                break;
            case "s":
                if (arrayOrReferences.equals("a")) {
                    this.tempStack = new StackArrayGeneric<>(1500000);
                } else {
                    this.tempStack = new StackRefGeneric<>();
                }
                break;
            default:
                break;
        }
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


    public void openFile(){
        this.text = new BufferedReader(new InputStreamReader(this.textToParse));
    }

    /**
     * This method de-loads a file off memory
     */
    public void closeFile() {
        try {
            this.text.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        String toReturn;
        try {
            toReturn = text.readLine();
        } catch (IOException e) {
            toReturn = "?";
        }
        return toReturn;
    }

    /**
     * This method takes the text loaded in memory, looks every line of it
     * Using regex, it finds the unicode characters and parse them so they can be added to the structures
     * And print the time it takes to store all characters it the structures so speed test can be performed
     * @return String with the time passed from the beginning to the end of the insertion
     * @throws IOException
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String addAll() {
        Instant firstTime = Instant.now();
        Pattern pattern = Pattern.compile(this.regex);
        String currentLine = readLine();
        while (currentLine!=null) {
            Matcher matcher = pattern.matcher(currentLine);
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
        if (needSort) tempList.sort();
        Instant lastTime = Instant.now();
        String totalTime = Duration.between(firstTime, lastTime).toString();
        return "It took: "+ String.valueOf(totalTime);
    }

    /**
     * This method adds a character to a specific data structure
     * @param stringToAdd: String with the 4 or 5 symbols to be parsed to char
     * @return String with the time passed during he addition
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String addElement(String stringToAdd) {
        Instant firstTime = Instant.now();
        char elementToAdd = stringToChar(stringToAdd);
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
        Instant lastTime = Instant.now();
        String totalTime = Duration.between(firstTime, lastTime).toString();
        return "It took "+totalTime+" to add "+String.valueOf(elementToAdd);
    }

    /**
     * This method removes all the elements from a specific data structure and displays in console
     * the time it took to do it.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String removeAll() {
        Instant firstTime = Instant.now();
        switch(this.dataStructure) {
            case "l":
                throw new UnsupportedOperationException("Not implemented yet");
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
        return "It took: "+ String.valueOf(totalTime);
    }

    /**
     * This method removes a character from a specific data structure
     * When used with lists, a character is needed to be passed
     * @param stringToDelete String with four or five hexadecimal symbols of the han character.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String removeElement(String stringToDelete) {
        boolean removed = false;
        char toDelete = stringToChar(stringToDelete);
        Instant firstTime = Instant.now();
        String message;
        switch (dataStructure) {
            case "l":
                try {
                    removed = this.tempList.delete(toDelete);
                } catch (NullPointerException nullPointerException) {
                    return nullPointerException.getMessage();
                }
                break;
            case "q":
                try {
                    toDelete = this.tempQueue.dequeue();
                    removed = true;
                } catch (NullPointerException nullPointerException) {
                    return nullPointerException.getMessage();
                }
                break;
            case "s":
                try {
                    toDelete = this.tempStack.pop();
                    removed = true;
                } catch (NullPointerException nullPointerException) {
                    return nullPointerException.getMessage();
                }
                break;
        }
        Instant lastTime = Instant.now();
        String totalTime = Duration.between(firstTime, lastTime).toString();
        if (removed) {
            return "It took "+totalTime+ " seconds to delete "+String.valueOf(toDelete);
        } else {
            return String.valueOf(toDelete)+" was not removed.";
        }
    }

    /**
     * This method shows the length of the data struture.
     */
    public String getDataStructureLength() {
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
        return String.valueOf(size);
    }

    /**
     * This method prints the characters of the data structures
     */
    public String print() {
        String toReturn;
        switch(this.dataStructure) {
            case "l":
                toReturn = tempList.toString();
                break;
            case "q":
                toReturn = tempQueue.toString();
                break;
            case "s":
                toReturn = tempStack.toString();
                break;
            default:
                toReturn = "";
                break;
        }
        return toReturn;
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