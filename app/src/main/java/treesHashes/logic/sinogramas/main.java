/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.sinogramas;

import data.sinogramas.MaxHeap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author dequi
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        Archive arc = new Archive();
        BufferedReader in;
        
        
        // LEER ARCHIVOS INDIVIDUAL
        // NO LEER 163 NI 164 PUES GENERAN PROBLEMAS
        for (int i = 81; i < 163; i++) {
            System.out.println("PAGE " + i);
            in = new BufferedReader(new FileReader("C:\\users\\dequi\\Desktop\\sinocharsBD\\charsFiles\\chars_"+i+".txt"));
            arc.text = in;
            // Inserta todo en el AVL principal (tempAVL)
            // Hace la clasificación por strokes y radixes en los BST almacenados en arreglos
            arc.parseText();
            
        }
        // Método print que hice para verificar si el AVL si quedó bien
        arc.print();
        // SearchPattern retorna un MaxHeap con los Unihan que coinciden
        // El ordenamiento del MaxHeap es según un atributo score que agreugé a Unihan
        // El score se calcula como (double) pattern.length / definition.length
        // El score es más alto (cercano a 1) cuando el patrón es exacto a la definición
        MaxHeap mh = arc.searchPattern("perro");
        // Extrae el resultado con mayor puntaje.
        // Ese debe ser el orden en el que se despleguen en la app
        System.out.println("Extracted: " + mh.extractMax());
        // Prueba la búsqueda por caracter
        System.out.println(arc.searchByChar('业', 'g'));
        
        
        
        
        System.out.println(arc.filterByStrokes(8, 'g'));
        System.out.println(arc.filterByRadixes(2, 'g'));
    }
    
}
