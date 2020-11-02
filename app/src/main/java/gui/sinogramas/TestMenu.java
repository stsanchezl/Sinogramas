package gui.sinogramas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import logic.sinogramas.Archive;

public class TestMenu extends AppCompatActivity {

    private Archive file;
    private Button addAllElementsButton;
    private Button addOneElementsButton;
    private Button deleteAllElementsButton;
    private Button deleteOneElementsButton;
    private Button showLengthElementsButton;
    private String arrayOrReference;
    private String dataStructure;
    private TextView displayOptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_menu);

        addAllElementsButton = (Button) findViewById(R.id.addAllButton);
        addOneElementsButton = (Button) findViewById(R.id.addOneButton);
        deleteAllElementsButton = (Button) findViewById(R.id.deleteAllButton);
        deleteOneElementsButton = (Button) findViewById(R.id.deleteOneButton);
        showLengthElementsButton = (Button) findViewById(R.id.showLengthButton);

        if(getIntent().hasExtra("gui.InitiateDataStructure.dataStructure")) {
            String spanishDataStructure = getIntent().getExtras().getString("gui.InitiateDataStructure.dataStructure");
            this.dataStructure = spanishToEnglishSelection(spanishDataStructure);
        } else {
            this.dataStructure = "";
        }
        if(getIntent().hasExtra("gui.InitiateDataStructure.arrayOrReference")) {
            this.arrayOrReference = getIntent().getExtras().getString("gui.InitiateDataStructure.arrayOrReference");
        } else {
            this.arrayOrReference = "";
        }
        openFile(this.arrayOrReference, this.dataStructure);

        addAllElementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    displayOptionTextView.setText(file.readText());
                    file.closeFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        addOneElementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        deleteAllElementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        deleteOneElementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        showLengthElementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void openFile(String arrayOrReference, String dataStructure) {
        try {
            InputStream readText = getAssets().open("mergedFiles.txt");
            this.file = new Archive(arrayOrReference, dataStructure, "o", readText);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private String spanishToEnglishSelection(String tempStructure) {
        String dataStructure;
        switch (tempStructure) {
            case "a":
                dataStructure = "t";
                break;
            case "c":
                dataStructure = "q";
                break;
            case "l":
                dataStructure = "l";
                break;
            case "p":
                dataStructure = "s";
                break;
            default:
                dataStructure = "z";
                break;
        }
        return dataStructure;
    }
}