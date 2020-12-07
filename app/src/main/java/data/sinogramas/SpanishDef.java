/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.sinogramas;

/**
 *
 * @author dequi
 */

// Clase que genera un objeto por cada definición en español
// Por cada uno de estos objetos se ejecutará el RabinKarp
// Excepto cuando la longitud del patrón es mayor a la del text de la definición
public class SpanishDef {
    
    private char character;
    private String text;
    private int len;
    
    public SpanishDef(char c, String t) {
        this.character = c;
        this.text = t;
        this.len = t.length();
    }
    
    public char getChar() {
        return this.character;
    }
    
    public String getText() {
        return this.text;
    }
    
    public int getLen() {
        return this.len;
    }

    
}
