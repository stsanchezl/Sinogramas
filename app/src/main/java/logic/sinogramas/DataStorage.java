package logic.sinogramas;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import data.sinogramas.QueueDynamicArrayGeneric;
import data.sinogramas.Unihan;

public class DataStorage {
    private Context contextToWorkWith;
    private File favoriteSinograms;
    private FileReader input;
    private FileWriter output;
    private QueueDynamicArrayGeneric<Unihan> sinogramQueue;

    public DataStorage(Context contextToWorkWith, QueueDynamicArrayGeneric<Unihan> sinogramQueue) {
        this.contextToWorkWith = contextToWorkWith;
        this.sinogramQueue = sinogramQueue;
        createOrReadFile(true);
    }

    private void createOrReadFile(boolean create) {
        if (create) this.favoriteSinograms = new File(contextToWorkWith.getFilesDir(), "favoriteSinograms");
        else this.favoriteSinograms = new File(contextToWorkWith.getFilesDir()+ File.separator + "favoriteSinograms");
        try {
            if (!favoriteSinograms.exists()) {
                favoriteSinograms.createNewFile();
            }
            input = new FileReader(favoriteSinograms);
            output = new FileWriter(favoriteSinograms);
        } catch (IOException ioException) {
            input = null;
            output = null;
        }
    }
    public QueueDynamicArrayGeneric<Unihan> getSinogramQueue() {
        return this.sinogramQueue;
    }

    public boolean store() {
        boolean success = false;
        try {
            while (!this.sinogramQueue.empty()) {
                Unihan currentChar = sinogramQueue.dequeue();
                if (currentChar!=null) this.output.append(currentChar.print());
            }
            output.flush();
            success = true;
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
        createOrReadFile(false);
        try {
            BufferedReader parseInformation = new BufferedReader(input);
            String line;
            while ((line = parseInformation.readLine()) != null) {
                sinogramQueue.enqueue(parseText(line));
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
        return new Unihan(Integer.valueOf(data[0]),data[1],data[2],data[3],data[4],data[5].split("-"),data[6].split("-"),data[7].split("-"));
    }

}
