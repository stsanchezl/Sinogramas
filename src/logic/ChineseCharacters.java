package logic;

import data.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class ChineseCharacters {

    private String regex;
    private String pathToFile;
    private BufferedReader text;
    private StackGeneric<Character> tempStack;
    private ListGeneric<Character> tempList;
    private QueueGeneric<Character> tempQueue;

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


    public void openFile() throws FileNotFoundException{
        this.text = new BufferedReader(new FileReader(pathToFile));
    }
    public void closeFile() throws IOException{
        this.text.close();
    }
    
    public String readLine() throws IOException{
        return text.readLine();
    }

    public void readText() throws IOException{
        
        if (this.regex!= null) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            System.out.println(currentTime);
            String currentLine = readLine();
            Pattern pattern;
            Matcher matcher;
            while (currentLine!=null) {
                pattern = Pattern.compile(this.regex);
                matcher = pattern.matcher(currentLine);
                if (matcher.find()) {
                    String found = matcher.group();
                    char elementToAdd = Character.toChars(Integer.parseInt(found.substring(2), 16))[0];
                    tempQueue.enqueue(elementToAdd);
                }
                currentLine = readLine();
            }
            currentTime = new Timestamp(System.currentTimeMillis());
            System.out.println(currentTime);
        } else {
            System.err.println("No regex found, set one using setRegex() method.");
        }
    }
}
