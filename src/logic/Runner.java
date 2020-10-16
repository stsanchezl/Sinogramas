package logic;

import java.io.IOException;
import ui.CommandLines;

public class Runner {

    public static void runner() {
        //My path is = /home/stiven/Documentos/Java/estructurasDeDatos/Unihan/mergedFiles.txt
        CommandLines.welcome();
        String dataStructure = CommandLines.selectDataStructure();
        CommandLines.print("Type the path to the file (including the .txt extension).");
        String pathToFile = CommandLines.input();
        Archive letras = new Archive(pathToFile, CommandLines.selectArraysOrReferences());
        CommandLines.menu();
        int selection = Integer.parseInt(CommandLines.input());
        while (selection<0 || selection>9) {
            CommandLines.menu();
            selection = Integer.parseInt(CommandLines.input());
        }
        while (selection!=0) {
            try {
                switch(selection) {
                    case 1:
                        letras.openFile();
                        letras.setRegex("[U].[\\dA-Z]{4,5}");
                        letras.readText(dataStructure);
                        letras.closeFile();
                        break;
                    case 2:
                        letras.removeAll(dataStructure);
                        break;
                    case 3:
                        letras.getDataStructureLength(dataStructure);
                        break;
                    case 9:

                    default:
                        selection =0;
                }
                selection = Integer.parseInt(CommandLines.input());
                while (selection<0 || selection>9) {
                    CommandLines.menu();
                    selection = Integer.parseInt(CommandLines.input());
                }
            } catch (IOException e) {
                System.err.println("Error: "+e.getMessage());
            }
        }
    }
}