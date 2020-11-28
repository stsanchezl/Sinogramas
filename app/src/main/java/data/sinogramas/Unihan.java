package data.sinogramas;

/**
 * This class represents a character from the Unihan database
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León
 * @version 1.0
 * @since 15/11/2020
 */

public class Unihan implements Comparable<Unihan>{

    private boolean isBMP;
    private char character;
    private char highSurrogative;
    private char lowSurrogative;
    private String characterInString;

    public Unihan(String characterInString) {
        this.characterInString = characterInString;
        this.character = Character.toChars(Integer.parseInt(characterInString,16))[0];
        this.highSurrogative = Character.MIN_VALUE;
        this.lowSurrogative = Character.MAX_VALUE;
        this.isBMP = true;
    }

    public boolean isBMP() {
        return isBMP;
    }
    public char getCharacter() {
        return this.character;
    }
    public char getHighSurrogative() {
        return highSurrogative;
    }
    public char getLowSurrogative() {
        return lowSurrogative;
    }


    @Override
    public String toString() {
        return String.valueOf(this.character);
    }
    @Override
    public int compareTo(Unihan otherCharacter) {
        int toReturn;
        if (this.character>otherCharacter.getCharacter()) toReturn = 1;
        else if (this.character==otherCharacter.getCharacter()) toReturn = 0;
        else toReturn = -1;
        return toReturn;
    }
}
