package gui.sinogramas.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import data.sinogramas.MaxHeap;
import data.sinogramas.QueueDynamicArrayGeneric;
import data.sinogramas.Unihan;
import gui.sinogramas.ListAdapter;
import gui.sinogramas.R;
import logic.sinogramas.Archive;

public class HomeFragment extends Fragment {

    private Button searchButton;
    private Button addButton;
    private EditText expressionEditText;
    private EditText numOfStrokesEditText;
    private EditText radixEditText;

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerSinograms;

    public Archive controller;
    public MaxHeap searchByPattern;
    public QueueDynamicArrayGeneric<Unihan> sinogramsQueue;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        searchButton = root.findViewById(R.id.searchSinogramButton);
        addButton = root.findViewById(R.id.addRmFavSinogramButton);
        expressionEditText = root.findViewById(R.id.expressionEditText);
        numOfStrokesEditText = root.findViewById(R.id.numOfStrokesEditText);
        radixEditText = root.findViewById(R.id.radixEditText);

        sinogramsQueue = new QueueDynamicArrayGeneric<>();
        startUp();

        recyclerSinograms = root.findViewById(R.id.recycler_favorites);
        recyclerSinograms.setLayoutManager(new LinearLayoutManager(getContext()));



        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        /*
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numOfStrokes = -1;
                int radix = -1;
                String expression = expressionEditText.getText().toString();
                try {
                    numOfStrokes = Integer.parseInt(numOfStrokesEditText.getText().toString());
                } catch (NumberFormatException numberFormatException) {
                    numOfStrokes = 0;
                }
                try {
                    radix = Integer.parseInt(radixEditText.getText().toString());
                } catch (NumberFormatException numberFormatException) {
                    radix = 0;
                }
                if (!expression.equals("")) {
                    sinogramsQueue.enqueue(controller.searchByChar(expression.charAt(0),'g'));
                } else if (radix>0) {
                    sinogramsQueue = controller.filterByRadixes(radix,'g');
                } else if (numOfStrokes>0) {
                    sinogramsQueue = controller.filterByStrokes(numOfStrokes, 'g');
                }

                ListAdapter adapter = new ListAdapter(sinogramsQueue,getContext());
                recyclerSinograms.setAdapter(adapter);

            }
        });

        return root;
    }

    public void startUp() {
        this.controller = new Archive();
        try {
            for (int i = 81; i < 163; i++) { // NO LEER 163 NI 164 PUES GENERAN PROBLEMAS
                String path = "charsFiles"+File.separator+"chars_"+i+".txt";
                controller.openFile(getResources().getAssets().open(path));
                controller.parseText();
                controller.closeFile();
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException ioException) {
            System.exit(1);
        }
    }
}