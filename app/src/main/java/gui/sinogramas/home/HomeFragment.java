package gui.sinogramas.home;

import android.os.Bundle;
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

import java.util.ArrayList;

import data.sinogramas.ListArrayGeneric;
import data.sinogramas.ListDynamicArrayGeneric;
import data.sinogramas.UnorderedListArrayGeneric;
import gui.sinogramas.ListAdapter;
import gui.sinogramas.ListElement;
import gui.sinogramas.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerSinograms;
    UnorderedListArrayGeneric<ListElement> listSinograms;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        listSinograms = new UnorderedListArrayGeneric<>(200);
        recyclerSinograms = root.findViewById(R.id.recycler_favorites);
        recyclerSinograms.setLayoutManager(new LinearLayoutManager(getContext()));
        fillList();
        ListAdapter adapter = new ListAdapter(listSinograms,getContext());
        recyclerSinograms.setAdapter(adapter);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void fillList() {

        listSinograms.insert(new ListElement("一","1","Uno","yī", "1"
        ));
        listSinograms.insert(new ListElement("丨","2","shù","Linea","1"
        ));
        listSinograms.insert(new ListElement("丶","3","Punto","diǎn","1"
        ));
        listSinograms.insert(new ListElement("丿","4","Trazo barrido","piě","1"
        ));
        listSinograms.insert(new ListElement("乙","5","Segundo","yǐ","1"
        ));
        listSinograms.insert(new ListElement("亅","6","Gancho","gōu","1"
        ));
        listSinograms.insert(new ListElement("二","7","Dos","èr","2"
        ));
        listSinograms.insert(new ListElement("亠","8","Tapa","tóu","2"
        ));
        listSinograms.insert(new ListElement("人","9","Persona","rén","2"
        ));
        listSinograms.insert(new ListElement("儿","10","Piernas","ér","2"
        ));

    }
}