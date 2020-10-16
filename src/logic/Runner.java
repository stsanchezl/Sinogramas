package logic;

import java.io.IOException;
import ui.CommandLines;

public class Runner {

    public static void runner() {

        //My path is = /home/stiven/Documentos/Java/estructurasDeDatos/Unihan/mergedFiles.txt
        CommandLines.print("Ingrese el camino al archivo .txt (incluyendo el archivo)");
        String pathToFile = CommandLines.input();
        ChineseCharacters letras = new ChineseCharacters(pathToFile, CommandLines.selectArraysOrReferences());

        try {
            letras.openFile();
            letras.setRegex("[U].[\\dA-Z]{4,5}");
            letras.readText(CommandLines.selectDataStructure());
            letras.closeFile();
            
        } catch (IOException e) {
            System.err.println("Error: "+e.getMessage());
        }
    }
}
