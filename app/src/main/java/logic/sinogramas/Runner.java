package logic.sinogramas;

import data.sinogramas.MaxHeap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author dequi
 */
public class Runner {
    public static void main(String[] args){

        try {
            Archive controller = new Archive();
            BufferedReader text;
            for (int i = 81; i < 163; i++) { // NO LEER 163 NI 164 PUES GENERAN PROBLEMAS
                String path = "src"+File.separator+"assets"+File.separator+"charsFiles"+File.separator+"chars_"+i+".txt";
                text = new BufferedReader(new FileReader(path));
                controller.text = text;
                // Inserta todo en el AVL principal (tempAVL)
                // Hace la clasificación por strokes y radixes en los BST almacenados en arreglos
                controller.parseText();
            }
            // SearchPattern retorna un MaxHeap con los Unihan que coinciden
            // El ordenamiento del MaxHeap es según un atributo score que agreugé a Unihan
            // El score se calcula como (double) pattern.length / definition.length
            // El score es más alto (cercano a 1) cuando el patrón es exacto a la definición
            MaxHeap mh = controller.searchPattern("perro");
            // Extrae el resultado con mayor puntaje.
            // Ese debe ser el orden en el que se despleguen en la app
            System.out.println("Extracted: " + mh.extractMax());
            // Prueba la búsqueda por caracter
            System.out.println(controller.searchByChar('业', 'g'));
            System.out.println(controller.filterByStrokes(8, 'g'));
            System.out.println(controller.filterByRadixes(2, 'g'));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
