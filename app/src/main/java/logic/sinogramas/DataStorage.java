package logic.sinogramas;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import data.sinogramas.QueueDynamicArrayGeneric;
import data.sinogramas.Unihan;
import gui.sinogramas.MenuPrincipalActivity;

public class DataStorage {
    private Context contextToWorkWith;
    private File favoriteSinograms;
    private File path;
    private FileReader input;
    private FileWriter output;
    private QueueDynamicArrayGeneric<Unihan> sinogramQueue;
    private final String fileName = "favoriteSinograms.txt";

    public DataStorage(Context contextToWorkWith, QueueDynamicArrayGeneric<Unihan> sinogramQueue) {
        this.path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM+File.separator+"Sinogramas");
        this.favoriteSinograms= new File(path, fileName);
        this.contextToWorkWith = contextToWorkWith;
        this.sinogramQueue = sinogramQueue;
        path.mkdir();
        try {
            if (!favoriteSinograms.exists()) {
                favoriteSinograms.createNewFile();
            }
            this.input = new FileReader(favoriteSinograms);
            this.output = new FileWriter(favoriteSinograms);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            Toast.makeText(contextToWorkWith,ioException.getMessage()+"j", Toast.LENGTH_LONG).show();
        }

        /*
        File directory = new File(Environment.getExternalStorageDirectory() + java.io.File.separator +"Directory");
        if (!directory.exists())
            Toast.makeText(contextToWorkWith,
                    (directory.mkdirs() ? "Directory has been created" : "Directory not created"),
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(contextToWorkWith, "Directory exists", Toast.LENGTH_SHORT).show();
         */
    }


    public QueueDynamicArrayGeneric<Unihan> getSinogramQueue() {
        return this.sinogramQueue;
    }

    public boolean store() {
        boolean success = false;
        try {
            while (!this.sinogramQueue.empty()) {
                Unihan currentChar = this.sinogramQueue.dequeue();
                if (currentChar!=null) {
                    String toAppend = currentChar.print();
                    this.output.append(toAppend);
                }
            }
            this.output.flush();
            success = true;
            Toast.makeText(contextToWorkWith,favoriteSinograms.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException ioException) {
            if (this.output==null) {
                Toast.makeText(contextToWorkWith,"Escritura nula", Toast.LENGTH_LONG).show();
            }
        }
        return success;
    }
    public boolean retreive() {
        this.sinogramQueue = new QueueDynamicArrayGeneric<>();
        boolean success = false;
        try {
            BufferedReader parseInformation = new BufferedReader(input);
            String line;
            while ((line = parseInformation.readLine()) != null) {
                this.sinogramQueue.enqueue(parseText(line));
            }
            success = true;
        } catch (IOException ioException) {
            if (this.input==null) {
                Toast.makeText(contextToWorkWith,"Lectura nula", Toast.LENGTH_LONG).show();
            }
        }
        return success;
    }

    private Unihan parseText(String strToParse) {
        //numOfStrokes+":"+codePoint+":"+mp3file+":"+pinyin+":"+radix+":"+englishDefinitions+":"+pictureLinks+":"+spanishDefinitions;
        String[] data = strToParse.split(":");
        return new Unihan(Integer.valueOf(data[0]),data[1],data[2],data[3],data[4],data[5].split("- "),data[6].split("- "),data[7].split("- "));
    }
}
