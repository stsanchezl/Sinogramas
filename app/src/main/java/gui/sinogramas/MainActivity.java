package gui.sinogramas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import logic.sinogramas.Archive;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent initiateDataStructure = new Intent(getApplicationContext(), InitiateDataStructure.class);
                startActivity(initiateDataStructure);
            }
        });
    }
}