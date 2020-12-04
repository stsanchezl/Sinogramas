package gui.sinogramas.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.ArrayList;

import data.sinogramas.ListArrayGeneric;
import data.sinogramas.ListDynamicArrayGeneric;
import data.sinogramas.UnorderedListArrayGeneric;
import gui.sinogramas.ListAdapter;
import gui.sinogramas.ListElement;

import gui.sinogramas.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView recyclerFavorites;
    UnorderedListArrayGeneric<ListElement> listFavorites;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        listFavorites = new UnorderedListArrayGeneric<>(200);
        recyclerFavorites = root.findViewById(R.id.recycler_favorites);
        recyclerFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        fillListCSV();
        ListAdapter adapter = new ListAdapter(listFavorites,getContext());
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
                    //Split by ";"
                    String[] tokens = line.split(";");

                    //read the data
                    ListElement sinogramE = new ListElement(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4]);
                    listFavorites.insert(sinogramE);
                }
            } catch (IOException e) {
                Log.wtf("MyActivity", "Error reading data file on line"+line,e);
                e.printStackTrace();
            }
    }
}