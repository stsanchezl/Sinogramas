package gui.sinogramas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitiateDataStructure extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiate_data_structure);

        Button continueButton = (Button) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            private final String possibleDataStructureOptions = "aclp";
            private final String possibleImplementationOptions = "ar";
            private final String possibleTextSelectorOptions = "123";
            @Override
            public void onClick(View v) {
                EditText dataStructureEditText = (EditText) findViewById(R.id.dataStructureEditText);
                EditText arrayOrReferenceEditText = (EditText) findViewById(R.id.arrayOrReferencesEditText);
                EditText textSelectorEditText = (EditText) findViewById(R.id.textSelectorEditText);
                String dataStructure = dataStructureEditText.getText().toString();
                String arrayOrReference = arrayOrReferenceEditText.getText().toString();
                String textSelector = textSelectorEditText.getText().toString();
                boolean firstFlag = possibleDataStructureOptions.contains(dataStructure);
                boolean secondFlag = possibleImplementationOptions.contains(arrayOrReference);
                boolean thirdFlag = possibleTextSelectorOptions.contains(textSelector);
                if (firstFlag && secondFlag && thirdFlag) {
                    Intent menuIntent = new Intent(getApplicationContext(), TestMenu.class);
                    menuIntent.putExtra("gui.InitiateDataStructure.dataStructure",dataStructure);
                    menuIntent.putExtra("gui.InitiateDataStructure.arrayOrReference",arrayOrReference);
                    menuIntent.putExtra("gui.InitiateDataStructure.textSelector",textSelector);
                    startActivity(menuIntent);
                }
            }
        });

    }
}