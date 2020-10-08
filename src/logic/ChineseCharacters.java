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
import java.time.LocalDateTime;

public class ChineseCharacters {

    private String regex; //Regular expresion to find info in the given text
    private String pathToFile;  //Path to file in one person's computer
    private BufferedReader text;  //Representation of the text in memory
    
    private StackGeneric<Character> tempStack; 
    private ListGeneric<Character> tempList;
    private QueueGeneric<Character> tempQueue;
    private ListArrayGeneric<Character> tempArrList;
    private StackListArrayGeneric<Character> tempStackListArr;

    /**
     * Constructor: gets the path where txt is and initialites the strucures to use
     * @param pathToFile: path where the txt is in each pc
     * @param arrayOrReferences: a string with one character that tells whether the structures is implemented
     *                           by references or arrays
     */
    public ChineseCharacters(String pathToFile, String arrayOrReferences) {
        this.pathToFile = pathToFile;
        if (arrayOrReferences.equals("r")) {
            this.tempStack = new StackRefGeneric<>();
            this.tempList = new LinkedListGeneric<>();
            this.tempQueue = new QueueRefGeneric<>();
        } else {
            this.tempStack = new StackListArrayGeneric<>(3000000);
            this.tempList = new ListArrayGeneric<>(70000);
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
     * And print the time right before and right after the symbols are store so speed test can be performed
     * @param selection: data structure to be used
     * @throws IOException 
     */
    public void readText(String selection) throws IOException{
        if (this.regex!= null) {
            Timestamp firstTime = new Timestamp(System.currentTimeMillis());
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
                    // tempQueue.enqueue(elementToAdd); ?
                }
                currentLine = readLine();
            }
            Timestamp lastTime = new Timestamp(System.currentTimeMillis());

            switch (selection) {
                case "l":
                    CommandLines.print(String.valueOf(tempList.length()));
                    break;
                case "q":
                    CommandLines.print(String.valueOf(tempQueue.length()));
                    break;
                case "s":
                    CommandLines.print(String.valueOf(tempStack.length()));
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("No regex found, set one using setRegex() method.");
        }
    }

    /**
     * This method creates a string of the difference of two Timestamps that is easy to read
     * @param beginning: First date
     * @param ending: Second date
     * @param withYear: For normal purposes, it is not necessary to have years, months and days, so it is 
     *                  not added; however, it can be added  if needed.
     * @return A string with format: (x years, x months, x days), x hours, x minutes, x seconds, x nanos
     *         with x being an integer and the parenthetical expression is optional
     */
    private String adjustTime(Timestamp beginning, Timestamp ending, boolean withYear) {
        StringBuilder toPrint = new StringBuilder("Total: ");
        LocalDateTime firstDate = beginning.toLocalDateTime();
        LocalDateTime secondDate = ending.toLocalDateTime();
        if(withYear) {
            int year = secondDate.getYear() - firstDate.getYear();
            int month = secondDate.getMonthValue() - firstDate.getMonthValue();
            int day = secondDate.getDayOfMonth() - firstDate.getDayOfMonth();
            toPrint.append(year+ " years, ");
            toPrint.append(month+ " months, ");
            toPrint.append(day+ " days, ");
        }
        int hour = secondDate.getHour() - firstDate.getHour();
        int minute = secondDate.getMinute() - firstDate.getMinute();
        int second = secondDate.getSecond() - firstDate.getSecond();
        int nano = secondDate.getNano() - firstDate.getNano();
        toPrint.append(hour+ " hours, ");
        toPrint.append(minute+ " minutes, ");
        toPrint.append(second+ " seconds, ");
        toPrint.append(nano+ " nanos. ");
        return toPrint.toString();
    }
}
