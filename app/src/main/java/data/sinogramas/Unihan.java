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
public class Unihan {

    private boolean isBMP;
    private char character;
    private char highSurrogative;
    private char lowSurrogative;
    private String characterInString;

    public Unihan(String characterInString) {
        this.characterInString = characterInString;
        this.character = Character.toChars(Integer.parseInt(characterInString,16))[0];
    }

    @Override
    public String toString() {
        return String.valueOf(this.character);
    }
}
