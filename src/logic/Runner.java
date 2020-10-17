package logic;

import java.io.IOException;
import ui.CommandLines;

public class Runner {

    public static void runner() {
        //My path is = /home/stiven/Documentos/Java/estructurasDeDatos/Unihan/mergedFiles.txt
        CommandLines.welcome();

        String dataStructure = CommandLines.selectDataStructure();
        String ordered = "o";
        if (dataStructure.equals("l")) {
            ordered = CommandLines.selectOrderOrUnorderedList();
        }
        String arrayOrReferences = CommandLines.selectArraysOrReferences();
        String pathToFile = CommandLines.input("Type the path to the file with the .txt extension.");

        Archive letras = new Archive(arrayOrReferences,dataStructure,ordered,pathToFile);
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
                        letras.readText();
                        letras.closeFile();
                        break;
                    case 2:
                        letras.removeAll();
                        break;
                    case 3:
                        letras.getDataStructureLength();
                        break;
                    case 4:
                        letras.print();
                        break;
                    default:
                        selection =0;
                        break;
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