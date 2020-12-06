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
 * @version 5.0
 * @since 16/10/2020
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import data.sinogramas.AVLTreeGeneric;
import data.sinogramas.BSTRefGeneric;
import data.sinogramas.HeapArray;
import data.sinogramas.ListDynamicArrayGeneric;
import data.sinogramas.ListGeneric;
import data.sinogramas.NodeGeneric;
import data.sinogramas.QueueDynamicArrayGeneric;
import data.sinogramas.QueueGeneric;
import data.sinogramas.StackGeneric;
import data.sinogramas.Unihan;
import data.sinogramas.UnorderedListDynamicArrayGeneric;
import static java.lang.Float.parseFloat;

public class Archive {

    // private boolean needSort = false;
    // private boolean needHeapSort = false;
    // private String dataStructure; //data structure to use.
    // private String regex = "[U].[\\dA-F]{4,5}"; //Regular expression to find info in the given text
    private BufferedReader text;  //Representation of the text in memory
    // private InputStream textToParse;

    private HeapArray<Unihan> tempHeap;
    private int[] strokesList;
    private int[] favStrokesList;
    private ListDynamicArrayGeneric<Integer> radixesList;
    private ListDynamicArrayGeneric<Integer> favRadixesList;
    private ListGeneric<Unihan> tempListU;
    private QueueGeneric<Unihan> tempQueue;
    private StackGeneric<Unihan> tempStack;
    private AVLTreeGeneric<Unihan> tempAVL;
    private BSTRefGeneric<Unihan>[] bstStrokesArray;
    private BSTRefGeneric<Unihan>[] favBSTStrokesArray;
    private ArrayList<BSTRefGeneric<Unihan>> bstRadixesArray;
    private ArrayList<BSTRefGeneric<Unihan>> favBSTRadixesArray;
    private BSTRefGeneric<Unihan> tempBST;
    private AVLTreeGeneric<Unihan> favAVL;

    /**
     * Class constructor, it initializes a specific linear data structure so they can hold the
     * Han characters, it does not matter the implementation of the data structure
     * @param arrayOrReferences: Implementation of the array
     * @param dataStructure: Data structure to be used.
     * @param ordered: whether the structure is ordered or not
     * @param textToParse: InputStream with the file so it can be put onto a BufferReader
     */
    
    public Archive() {
        this.strokesList = new int[59]; // 58 is the maximum number of strokes according to Wikipedia
        // we use 59 to leave the first position i=0 free and not use it
        this.favStrokesList = new int[59];
        this.radixesList = new ListDynamicArrayGeneric<>();
        this.favRadixesList = new ListDynamicArrayGeneric<>();
        this.bstRadixesArray = new ArrayList<>();
        this.favBSTRadixesArray = new ArrayList<>();
        this.bstStrokesArray = (BSTRefGeneric<Unihan>[]) new Object[59];
        this.favBSTStrokesArray = (BSTRefGeneric<Unihan>[]) new Object[59];
        this.tempListU = new UnorderedListDynamicArrayGeneric<>();
        this.tempAVL = new AVLTreeGeneric<>();
        this.favAVL = new AVLTreeGeneric<>();
    }
    
    
    
    public Archive(String arrayOrReferences, String dataStructure, String ordered, InputStream textToParse) {
        
        /*
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
            case "t":
                this.tempBST = new BSTRefGeneric<>();
                break;
            case "h":
                needHeapSort = true;
                this.tempHeap = new HeapArray<>(1500000);        
            case "a":
                this.tempAVL = new AVLTreeGeneric<>();
            default:
                break;
        }
        */
    }

    
    /*
    public BSTRefGeneric<Unihan> tempBST() {
        return this.tempBST;
    }
    public ListGeneric<Unihan> getTempList() {
        return this.tempList;
    }
    public QueueGeneric<Unihan> getTempQueue() {
        return this.tempQueue;
    }
    public StackGeneric<Unihan> getTempStack() {
        return this.tempStack;
    }
    public String getRegex () {
        return this.regex;
    }
    public void setRegex(String regex) {
        this.regex = regex;
    }
    */

    /**
     * This method loads a file into memory so it can be used
     */
    
    /*
    public void openFile(){
        this.text = new BufferedReader(new InputStreamReader(this.textToParse));
    }
    */

    /**
     * This method de-loads a file off memory
     */
    
    /*
    public void closeFile() {
        try {
            this.text.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    /**
     * This method reads a line: parse a complete line and skips over the next
     * @return read line - null if it is the end of the file
     */
    public String readLine() {
        String toReturn = "";
        try {
            toReturn = text.readLine();
        } catch (IOException e) {
            toReturn = "?";
        } catch (NoSuchElementException noSuchElementException) {
            toReturn = null;
        } finally {
            return toReturn;
        }
    }
    

    /**
     * This method takes the text loaded in memory, looks every line of it
     * Using regex, it finds the unicode characters and parse them so they can be added to the structures
     * And print the time it takes to store all characters it the structures so speed test can be performed
     * @return String with the time passed from the beginning to the end of the insertion
     */
    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String addAll() {
        Instant firstTime = Instant.now();
        Pattern pattern = Pattern.compile(this.regex);
        String currentLine = readLine();
        while (currentLine!=null) {
            Matcher matcher = pattern.matcher(currentLine);
            if (matcher.find()) {
                String found = matcher.group();
                Unihan elementToAdd = new Unihan(found.substring(2));
                switch (this.dataStructure) {
                    case "h":
                        tempHeap.insertItem(elementToAdd);
                        break;
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
                    default:
                        break;
                }
            }
            currentLine = readLine();
        }
        if (needSort) tempList.sort();
        if (needHeapSort) tempHeap.sort();
        Instant lastTime = Instant.now();
        String totalTime = Duration.between(firstTime, lastTime).toString();
        return "It took: "+ String.valueOf(totalTime);
    }
    */

    public Unihan parseChunck() {
        String chrStr = readLine();
        try {
            String[] englishDefinitions = stringToArray(readLine());
            String[] spanishDefinitions = stringToArray(readLine());
            String[] pictureLinks = stringToArray(readLine());
            String codePoint = readLine();
            int numOfStrokes = Integer.parseInt(readLine());
            String pinyin = readLine();
            String radix = readLine();
            String mp3file = readLine();
            Unihan thisChar = new Unihan(numOfStrokes,codePoint,mp3file,pinyin,radix,englishDefinitions,pictureLinks,spanishDefinitions);
            return thisChar;
        } catch (NoSuchElementException noSuchElementException) {
            Unihan emptyChar = new Unihan("U+0000");
            return emptyChar;
        } catch (NullPointerException nullPointerException) {
            Unihan emptyChar = new Unihan("U+0000");
            return emptyChar;
        }
    }
    
    /*
    La presente clase se modificó de la siguiente manera:
    Se puso en comentario lo que se considera que no se va a usar
    Se declararon otras variables como por ejemplo:
    strokesList y radixesList
    strokeList es un arreglo estático de tamaño 59 que lleva registro de cuáles árboles ya
    han sido creados y cuales aun no. Si el valor es null entonces es que no se ha creado
    y se debe crear el árbol. Si no es nulo entonces ya existte la raiz en bstStrokesArray
    y se procede a hacer la inserción. No estoy seguro de si aquí pueda generarse un NullPointer
    Exception en la condición del if.
    La segunda lleva registro de cuál es el máximo radical guardado y se accede a él con
    getLast(). En este caso se usó así porque en el orden de lectura, los radicales si siguen un
    orden consecutivo a diferencia de los strokes y por eso allí se usa el arreglo estático
    o el radix máximo (parte entera únicamente) encontrado durante el parseo. Se
    accede a este máximo con getLast() que es un método que incluí para las listas ordenadas
    dinámicas únicamente. El método parseText() que está abajo es el que más modifiqué:
    Ya no importan las demás estructuras sino principalmente el AVL. Se hacen las inserciones
    correspondientes en esas estructuras y adicionalmente se crearon dos ArrayList que
    almacenan BSTs. Un BST por cada número diferente de strokes en bstStrokesArray 
    y un BST por cada parte entera de radix diferente en bstRadixesArray. De esa manera
    cuando el usuario quiera buscar por número de strokes o por radix (solo está soportado la
    parte entera) se acceda a estos ArrayList por el número y se acceda al BST correspondiente
    y justo ahí se hace un traverse que va almacenando en un Queue y ese se usará para
    desplegar los resultados en pantalla en la app.
    Las tres búsquedas soportadas entonces serían por character directamente que usa el AVL,
    y los filter by Strokes y by Radixes.
    */
    
    int intRadix;
    
    public void parseText() {
        
        Unihan thisChar = parseChunck();
        
        this.strokesList[thisChar.getNumOfStrokes()] = thisChar.getNumOfStrokes();
        // the line below checks whether the position is null. In case it is null we
        // then procede to create the bst and store it in that position.
        // I am not completely sure if it works properly, though
        if (strokesList[thisChar.getNumOfStrokes()] != thisChar.getNumOfStrokes()) {
            BSTRefGeneric<Unihan> tempBST = new BSTRefGeneric<>();
            bstStrokesArray[thisChar.getNumOfStrokes()] = tempBST;
        } else {
            bstStrokesArray[thisChar.getNumOfStrokes()].insertBST(thisChar);
        }
        // la próxima linea seguramente generará un error cuando se llegue a algún
        // caracter de la forma 196'.5 o así por el estilo, es decir, uno con comilla
        // no sé cuál es la mejor manera de prevenirlo. ¿Con una excepción o con un regex?
        intRadix = (int) Math.floor(parseFloat(thisChar.getRadix()));
        this.radixesList.insert(intRadix);
        if (radixesList.getLast().compareTo(intRadix) > 0) {
            tempBST = new BSTRefGeneric<>();
            bstRadixesArray.add(tempBST);
        } else {
            bstRadixesArray.get(intRadix - 1).insertBST(thisChar);
        } 
        tempAVL.setRoot(this.tempAVL.insert(tempAVL.getRoot(), thisChar));
        while (readLine()!= null) {
            this.strokesList[parseChunck().getNumOfStrokes()] = parseChunck().getNumOfStrokes();
            if (strokesList[parseChunck().getNumOfStrokes()] != parseChunck().getNumOfStrokes()) {
                tempBST = new BSTRefGeneric<>();
                bstStrokesArray[parseChunck().getNumOfStrokes()] = tempBST;
            } else {
                bstStrokesArray[parseChunck().getNumOfStrokes()].insertBST(parseChunck());
            } 
            intRadix = (int) Math.floor(parseFloat(parseChunck().getRadix()));
            this.radixesList.insert(intRadix);
            if (radixesList.getLast().compareTo(intRadix) > 0) {
                tempBST = new BSTRefGeneric<>();
                bstRadixesArray.add(tempBST);
            } else {
                bstRadixesArray.get(intRadix - 1).insertBST(thisChar);
            } 
            tempAVL.setRoot(this.tempAVL.insert(tempAVL.getRoot(), parseChunck()));
        }
    }
    
    
    public Unihan searchByChar(char c, char selector) {
        NodeGeneric<Unihan> n = null;
        if (selector == 'g') { // g de general
            n = this.tempAVL.find(tempAVL.getRoot(), c);  
        } else if (selector == 'f') { // f de favoritos
            n = this.favAVL.find(favAVL.getRoot(), c);  
        }
        return n.getData();
    }
    
    public QueueDynamicArrayGeneric<Unihan> filterByStrokes(int s, char selector) {
        if (selector == 'g')
            tempBST = this.bstStrokesArray[s];
        else if (selector == 'f')
            tempBST = this.favBSTStrokesArray[s];
        return tempBST.inOrderToQueue(tempBST.getRoot());
    }
    
    public QueueDynamicArrayGeneric<Unihan> filterByRadixes(int r, char selector) {
        if (selector == 'g')
            tempBST = this.bstRadixesArray.get(r);
        else if (selector == 'f')
            tempBST = this.favBSTRadixesArray.get(r);
        return tempBST.inOrderToQueue(tempBST.getRoot());
    }
    
    public void addFavorite(char c) {
        NodeGeneric<Unihan> n = this.tempAVL.find(tempAVL.getRoot(), c);
        favAVL.setRoot(favAVL.insert(favAVL.getRoot(), n.getData()));
        this.favStrokesList[n.getData().getNumOfStrokes()] = n.getData().getNumOfStrokes();
        if (favStrokesList[n.getData().getNumOfStrokes()] != n.getData().getNumOfStrokes()) {
            BSTRefGeneric<Unihan> tempBST = new BSTRefGeneric<>();
            favBSTStrokesArray[n.getData().getNumOfStrokes()] = tempBST;
        } else {
            favBSTStrokesArray[n.getData().getNumOfStrokes()].insertBST(n.getData());
        }
        intRadix = (int) Math.floor(parseFloat(n.getData().getRadix()));
        this.favRadixesList.insert(intRadix);
        if (favRadixesList.getLast().compareTo(intRadix) > 0) {
            tempBST = new BSTRefGeneric<>();
            favBSTRadixesArray.add(tempBST);
        } else {
            favBSTRadixesArray.get(intRadix - 1).insertBST(n.getData());
        } 
    }
    
    /**
     * This method adds a character to a specific data structure
     * @param stringToAdd: String with the 4 or 5 symbols to be parsed to char
     * @return String with the time passed during he addition
     */
    
    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String addElement(String stringToAdd) {
        Instant firstTime = Instant.now();
        Unihan elementToAdd = new Unihan(stringToAdd);
        switch(this.dataStructure) {
            case "h":
                this.tempHeap.insertItem(elementToAdd);
                break;
            case "l":
                this.tempList.insert(elementToAdd);
                break;
            case "q":
                this.tempQueue.enqueue(elementToAdd);
                break;
            case "s":
                this.tempStack.push(elementToAdd);
                break;
            case "t":
                this.tempBST.insertBST(elementToAdd);
            default:
                break;
        }
        Instant lastTime = Instant.now();
        String totalTime = Duration.between(firstTime, lastTime).toString();
        return "It took "+totalTime+" to add "+String.valueOf(elementToAdd);
    }
    */

    /**
     * This method removes all the elements from a specific data structure and displays in console
     * the time it took to do it.
     */
    
    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String removeAll() {
        Instant firstTime = Instant.now();
        switch(this.dataStructure) {
            case "h":
                throw new UnsupportedOperationException("Not implemented yet");
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
            case "t":
                throw new UnsupportedOperationException("Not implemented yet");
            default:
                break;
        }
        Instant lastTime = Instant.now();
        String totalTime = Duration.between(firstTime, lastTime).toString();
        return "It took: "+ String.valueOf(totalTime);
    }
    */

    /**
     * This method removes a character from a specific data structure
     * When used with lists, a character is needed to be passed
     * @param stringToDelete String with four or five hexadecimal symbols of the han character.
     */
    
    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String removeElement(String stringToDelete) {
        boolean removed = false;
        Unihan toDelete = new Unihan(stringToDelete);
        Instant firstTime = Instant.now();
        String message;
        switch (dataStructure) {
            case "h":
                throw new UnsupportedOperationException("Not implemented yet");
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
            case "t":
                throw new UnsupportedOperationException ("Not implemented yet");
            default:
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
    */

    /**
     * This method shows the length of the data struture.
     */
    
    /*
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
    */

    /**
     * This method prints the characters of the data structures
     */
    
    /*
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
    */

    private String[] stringToArray(String strToParse) {
        strToParse = strToParse.substring(1,strToParse.length()-1);
        String[] data = strToParse.split(", ");
        for (int i=0; i<data.length; i++) {
            data[i] = data[i].substring(1,data[i].length()-1);
        }
        return data;
    }
}