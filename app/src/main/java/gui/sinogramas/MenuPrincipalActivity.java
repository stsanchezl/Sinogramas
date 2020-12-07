package gui.sinogramas;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import data.sinogramas.*;
import logic.sinogramas.Archive;

public class MenuPrincipalActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        startUp();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void startUp() {
        try {
            Archive controller = new Archive();
            BufferedReader text;
            for (int i = 81; i < 163; i++) { // NO LEER 163 NI 164 PUES GENERAN PROBLEMAS
                String path = "charsFiles"+File.separator+"chars_"+i+".txt";
                controller.openFile(getAssets().open(path));
                controller.parseText();
                controller.closeFile();
            }
            /*
            MaxHeap mh = controller.searchPattern("perro");
            // Extrae el resultado con mayor puntaje.
            // Ese debe ser el orden en el que se despleguen en la app
            System.out.println("Extracted: " + mh.extractMax());
            // Prueba la búsqueda por caracter
            System.out.println(controller.searchByChar('业', 'g'));
            System.out.println(controller.filterByStrokes(8, 'g'));
            System.out.println(controller.filterByRadixes(2, 'g'));
            */
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException ioException) {
            System.exit(1);
        }
    }
}