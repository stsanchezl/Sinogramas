package gui.sinogramas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestMenu extends AppCompatActivity {

    private String dataStructure;
    private String arrayOrReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_menu);

        Button addAllElementsButton = (Button) findViewById(R.id.addAllButton);
        Button addOneElementsButton = (Button) findViewById(R.id.addOneButton);
        Button deleteAllElementsButton = (Button) findViewById(R.id.deleteAllButton);
        Button deleteOneElementsButton = (Button) findViewById(R.id.deleteOneButton);
        Button showLengthElementsButton = (Button) findViewById(R.id.showLengthButton);

        if(getIntent().hasExtra("gui.InitiateDataStructure.dataStructure")) {
            this.dataStructure = getIntent().getExtras().getString("gui.InitiateDataStructure.dataStructure");
        } else {
            this.dataStructure = "";
        }
        if(getIntent().hasExtra("gui.InitiateDataStructure.arrayOrReference")) {
            this.arrayOrReference = getIntent().getExtras().getString("gui.InitiateDataStructure.arrayOrReference");
        } else {
            this.arrayOrReference = "";
        }

        addAllElementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}