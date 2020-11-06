package gui.sinogramas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import logic.sinogramas.Archive;

public class TestMenu extends AppCompatActivity {

    private Archive file;
    private Button addAllElementsButton;
    private Button addOneElementsButton;
    private Button deleteAllElementsButton;
    private Button deleteOneElementsButton;
    private Button showLengthElementsButton;
    private EditText characterEntryEditText;
    private String arrayOrReference;
    private String dataStructure;
    private String textToOpen;
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
        characterEntryEditText = (EditText) findViewById(R.id.characterEntryEditText);
        displayOptionTextView = (TextView) findViewById(R.id.displayOptionsTextView);

        if(getIntent().hasExtra("gui.InitiateDataStructure.dataStructure")) {
            String spanishDataStructure = getIntent().getExtras().getString("gui.InitiateDataStructure.dataStructure");
            this.dataStructure = spanishToEnglishSelection(spanishDataStructure);
        } else {
            this.dataStructure = null;
        }
        if(getIntent().hasExtra("gui.InitiateDataStructure.arrayOrReference")) {
            this.arrayOrReference = getIntent().getExtras().getString("gui.InitiateDataStructure.arrayOrReference");
        } else {
            this.arrayOrReference = null;
        }
        if(getIntent().hasExtra("gui.InitiateDataStructure.textSelector")) {
            String tempTextToOpen = getIntent().getExtras().getString("gui.InitiateDataStructure.textSelector");
            this.textToOpen = selectFile(tempTextToOpen);
        } else {
            this.textToOpen = null;
        }

        openFile(arrayOrReference, dataStructure,textToOpen);

        addAllElementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                file.openFile();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    displayOptionTextView.setText(file.readText());
                }
                file.closeFile();
            }
        });
        addOneElementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringEntry = characterEntryEditText.getText().toString();
                if (stringEntry!=null && (stringEntry.length()>=4 && stringEntry.length()<=5) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    displayOptionTextView.setText(file.addElement(stringEntry));
                }
            }
        });
        deleteAllElementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) displayOptionTextView.setText(file.removeAll());
            }
        });
        deleteOneElementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (dataStructure.equals("l")) {
                        String stringEntry = characterEntryEditText.getText().toString();
                        if (stringEntry != null && stringEntry.length() >= 4 && stringEntry.length() <= 5) {
                            displayOptionTextView.setText(file.removeElement(stringEntry));
                        }
                    } else {
                        displayOptionTextView.setText(file.removeElement("3400"));
                    }
                }
            }
        });
        showLengthElementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayOptionTextView.setText(file.getDataStructureLength());
            }
        });
    }
    private void openFile(String arrayOrReference, String dataStructure, String textToOpen) {
        try {
            InputStream readText = getAssets().open(textToOpen);
            this.file = new Archive(arrayOrReference, dataStructure, "o", readText);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private String selectFile(String selector) {
        String fileToReturnName;
        switch (selector) {
            case "1":
                fileToReturnName = "Unihan_DictionaryLikeData.txt";
                break;
            case "2":
                fileToReturnName = "Unihan_Variants.txt";
                break;
            case "3":
                fileToReturnName = "mergedFiles.txt";
                break;
            default:
                fileToReturnName = "Unihan_IRGSources.txt";
                break;
        }
        return fileToReturnName;
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