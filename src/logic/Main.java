package logic;

import java.io.IOException;

import data.*;
import ui.*;

public class Main {

    public static void main(String[] args) {
        //reverse(CommandLines.dataTypeSelection());
        //palindrome();
        //testQueue(CommandLines.dataTypeSelection());
        /*
        UndoButton frame = new UndoButton();
        frame.setVisible(true);
        */
        CommandLines.print("Ingrese el camino al archivo .txt (inclutendo el archivo)");
        String pathToFile = CommandLines.input();
        ChineseCharacters letras = new ChineseCharacters(pathToFile);
        try {
            letras.openFile();
            letras.setRegex("[U].[\\dA-Z]{4,5}");
            letras.readText();
            letras.closeFile();
        } catch (IOException e) {
            System.err.println("Error: "+e.getMessage());
        }
        
    }

    public static void reverse(String type) {
        String selection;
        int size;
        boolean flag = true;
        switch(type) {
            case "i":
                StackGeneric<Integer> intStack;
                selection = CommandLines.selectArraysOrReferences();
                if (selection.equals("a")) {
                    size = CommandLines.selectSize("stack");
                    intStack = new StackArrayGeneric<>(size);
                } else {
                    intStack = new StackRefGeneric<>();
                }

                while(flag && !(intStack.full())) {
                    
                    int numberToBeAdded = Integer.parseInt(CommandLines.getElement());
                    intStack.push(numberToBeAdded);
                    flag = "c".equals(CommandLines.stop());
                }
                CommandLines.reverseMessage("integer");
                CommandLines.print(intStack.toString());
                break;

            case "s":
                StackGeneric<String> strStack;
                selection = CommandLines.selectArraysOrReferences();
                if (selection.equals("a")) {
                    size = CommandLines.selectSize("Stack");
                    strStack = new StackArrayGeneric<>(size);
                } else {
                    strStack = new StackRefGeneric<>();
                }

                while(flag && !(strStack.full())) {
                    String stringToBeAdded = CommandLines.getElement();
                    strStack.push(stringToBeAdded);
                    flag = "c".equals(CommandLines.stop());
                }
                CommandLines.reverseMessage("string");
                CommandLines.print(strStack.toString());
                break;

            default:
                CommandLines.print(type + " not valid.");
                break;
        }
    }
    
    public static void palindrome() {
        int counter;
        int iterator;
        boolean isPalindrome = true;
        StackGeneric<Character> stack;
        String phrase = CommandLines.getPhrase().replace(" ","");
        String selection = CommandLines.selectArraysOrReferences();

        if (selection.equals("a")) {
            stack = new StackArrayGeneric<>(phrase.length()/2);
        } else {
            stack = new StackRefGeneric<>();
        }

        for (iterator=0; iterator<phrase.length()/2; iterator++) {
            stack.push(phrase.charAt(iterator));
        }
        counter = iterator;
        if (phrase.length()%2!=0) {
            counter++;
        }
        
        while (!(stack.empty()) && isPalindrome) {
            if (stack.pop()!=phrase.charAt(counter)) {
                isPalindrome=false;
            }
            counter++;
        }

        if (isPalindrome) {
            CommandLines.print("The phrase is a palindrome");
        } else {
            CommandLines.print("The phrase is not a palindrome");
        }
    }

    public static void testQueue(String type) {
        String arrayOrRefSelection = CommandLines.selectArraysOrReferences();
        String selection;
        switch(type) {
            case "i":
                QueueGeneric<Integer> intQueue;
                if (arrayOrRefSelection.equals("a")) {
                    int size = CommandLines.selectSize("queue");
                    intQueue = new QueueArrayGeneric<>(size);
                } else {
                    intQueue = new QueueRefGeneric<>();
                }
                selection = CommandLines.stopQueues();
                while(!selection.equals("s")) {
                    if(selection.equals("e")) {
                        if (intQueue.full()) {
                            CommandLines.print("Queue full, can't enqueue");
                        } else {
                            int number = Integer.parseInt(CommandLines.getElement());
                            intQueue.enqueue(number);
                        }
                    } else {
                        if(intQueue.empty()) {
                            CommandLines.print("Queue empty, can't be dequeue");
                        } else {
                            int item = intQueue.dequeue();
                            CommandLines.print("The element dequeued is "+item);
                        }
                    }
                    selection = CommandLines.stopQueues();
                }

                break;
            case "s":
                QueueGeneric<String> strQueue;
                if (arrayOrRefSelection.equals("a")) {
                    int size = CommandLines.selectSize("queue");
                    strQueue = new QueueArrayGeneric<>(size);
                } else {
                    strQueue = new QueueRefGeneric<>();
                }

                selection = CommandLines.stopQueues();
                while(!selection.equals("s")) {
                    if(selection.equals("e")) {
                        if (strQueue.full()) {
                            CommandLines.print("Queue full, can't enqueue");
                        } else {
                            strQueue.enqueue(CommandLines.getElement());
                        }
                    } else {
                        if(strQueue.empty()) {
                            CommandLines.print("Queue empty, can't be dequeue");
                        } else {
                            CommandLines.print("The element dequeued is "+strQueue.dequeue());
                        }
                    }
                    selection = CommandLines.stopQueues();
                }
                break;
            default:
                CommandLines.print("toPrint");
                break;
        }
    }



}