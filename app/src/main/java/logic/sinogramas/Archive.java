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
 * @version 6.0
 * @since 16/10/2020
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import data.sinogramas.*;
import static java.lang.Float.parseFloat;

public class Archive {

    private BufferedReader text;
    private int intRadix;
    private Integer[] treesByStrokes;
    private Integer[] favStrokesList;
    private InputStream textToParse;

    private ArrayList<BSTRefGeneric<Unihan>> bstRadixesArray;
    private ArrayList<BSTRefGeneric<Unihan>> favBSTRadixesArray;
    private AVLTreeGeneric<Unihan> favAVL;
    private AVLTreeGeneric<Unihan> tempAVL;
    private BSTRefGeneric<Unihan>[] bstStrokesArray;
    private BSTRefGeneric<Unihan>[] favBSTStrokesArray;
    private BSTRefGeneric<Unihan> tempBST;
    private HeapArray<Unihan> tempHeap;
    private ListDynamicArrayGeneric<Integer> radixesList;
    private ListDynamicArrayGeneric<Integer> favRadixesList;
    private ListGeneric<Unihan> tempListU;
    private QueueGeneric<Unihan> tempQueue;
    private StackGeneric<Unihan> tempStack;

    public Archive() {
        this.treesByStrokes = new Integer[59]; // 58 is the maximum number of strokes according to Wikipedia, position 0 not used
        this.favStrokesList = new Integer[59]; // Position 0 not used
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

    /**
     * This method loads a file into memory so it can be used
     */
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
     * This method reads 9 lines from the file selected and creates a Unihan character.
     * @return the created Unihan character.
     */
    private Unihan parseChunck() {
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

    public void parseText() {
        Unihan thisChar = parseChunck();
        this.treesByStrokes[thisChar.getNumOfStrokes()] = thisChar.getNumOfStrokes();
        // the line below checks whether the position is null. In case it is null we
        // then procede to create the bst and store it in that position.
        // I am not completely sure if it works properly, though
        if (treesByStrokes[thisChar.getNumOfStrokes()] != thisChar.getNumOfStrokes()) {
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
            this.treesByStrokes[parseChunck().getNumOfStrokes()] = parseChunck().getNumOfStrokes();
            if (treesByStrokes[parseChunck().getNumOfStrokes()] != parseChunck().getNumOfStrokes()) {
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
     * This method parse a String line and converts it into an array
     * The tokens are separated by commas
     * @param strToParse Stirng to be parsed
     * @return String[] array with the tokens
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