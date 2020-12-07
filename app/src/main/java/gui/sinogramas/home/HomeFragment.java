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
import data.sinogramas.QueueDynamicArrayGeneric;
import data.sinogramas.Unihan;
import gui.sinogramas.ListAdapter;

import gui.sinogramas.MenuPrincipalActivity;
import gui.sinogramas.R;
import logic.sinogramas.Archive;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerSinograms;
    QueueDynamicArrayGeneric<Unihan> sinogramsQueue;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        sinogramsQueue = new QueueDynamicArrayGeneric<>();

        recyclerSinograms = root.findViewById(R.id.recycler_favorites);
        recyclerSinograms.setLayoutManager(new LinearLayoutManager(getContext()));

        fillList();

        ListAdapter adapter = new ListAdapter(sinogramsQueue,getContext());
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
        /*
        QueueDynamicArrayGeneric<Unihan> datos = controller.filterByRadixes(2, 'g');
        while (!datos.empty()) {
            sinogramsQueue.enqueue(datos.dequeue());
        }

         */
        /*
        sinogramsQueue.enqueue(new Unihan("一","1","Uno","yī", "1"));
        sinogramsQueue.enqueue(new Unihan("丨","2","shù","Linea","1"));
        sinogramsQueue.enqueue(new Unihan("丶","3","Punto","diǎn","1"));
        sinogramsQueue.enqueue(new Unihan("丿","4","Trazo barrido","piě","1"));
        sinogramsQueue.enqueue(new Unihan("乙","5","Segundo","yǐ","1"));
        sinogramsQueue.enqueue(new Unihan("亅","6","Gancho","gōu","1"));
        sinogramsQueue.enqueue(new Unihan("二","7","Dos","èr","2"));
        sinogramsQueue.enqueue(new Unihan("亠","8","Tapa","tóu","2"));
        sinogramsQueue.enqueue(new Unihan("人","9","Persona","rén","2"));
        sinogramsQueue.enqueue(new Unihan("儿","10","Piernas","ér","2"));

         */

    }
}