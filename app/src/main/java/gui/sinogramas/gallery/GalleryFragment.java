package gui.sinogramas.gallery;

import android.os.Bundle;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import data.sinogramas.QueueDynamicArrayGeneric;
import data.sinogramas.Unihan;
import gui.sinogramas.ListAdapter;

import gui.sinogramas.*;

public class GalleryFragment extends Fragment {

    private Button searchButton;
    private Button deleteButton;
    private EditText expressionEditText;
    private EditText numOfStrokesEditText;
    private EditText radixEditText;

    private GalleryViewModel galleryViewModel;
    private RecyclerView recyclerFavorites;
    QueueDynamicArrayGeneric<Unihan> favoriteQueue;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        favoriteQueue = new QueueDynamicArrayGeneric<>();

        searchButton = root.findViewById(R.id.searchSinogramButton);
        deleteButton = root.findViewById(R.id.addRmFavSinogramButton);
        expressionEditText = root.findViewById(R.id.expressionEditText);
        numOfStrokesEditText = root.findViewById(R.id.numOfStrokesEditText);
        radixEditText = root.findViewById(R.id.radixEditText);

        recyclerFavorites = root.findViewById(R.id.recycler_favorites);
        recyclerFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        fillListCSV();
        ListAdapter adapter = new ListAdapter(favoriteQueue,getContext());
        recyclerFavorites.setAdapter(adapter);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    private void fillListCSV() {

        InputStream is = getResources().openRawResource(R.raw.sinograms_list);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
            try {
                while( (line = reader.readLine()) != null) {
                    String[] tokens = line.split(";");
                    Unihan thisChar = new Unihan(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4]);
                    favoriteQueue.enqueue(thisChar);
                }
            } catch (IOException e) {
                Log.wtf("MyActivity", "Error reading data file on line"+line,e);
                e.printStackTrace();
            }
    }
}