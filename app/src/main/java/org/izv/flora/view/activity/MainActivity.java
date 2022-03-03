package org.izv.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.view.adapter.Adapter;
import org.izv.flora.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAdd;
    private FloatingActionButton fabImagen;
    private RecyclerView rvMain;
    private MainActivityViewModel mavm;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    protected void onResume() {
        super.onResume();
        mavm.getFlora();
        initialize();
    }

    private void initialize() {
        initComponents();
        adapter = new Adapter(this);
        defineAdapterListener();
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setAdapter(adapter);

        mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mavm.getFlora();
        mavm.getFloraLiveData().observe(this, floraPlural -> {
            adapter.setFloraList(floraPlural);
        });


    }

    private void defineAdapterListener() {
        adapter.setOnClickListener(view -> {
            openEditFloraActivity(adapter.getItem(rvMain.getChildAdapterPosition(view)));
        });
    }

    private void initComponents() {
        fabAdd = findViewById(R.id.fabAdd);
        fabImagen = findViewById(R.id.fabImagen);
        fabAdd.setOnClickListener(v -> openAddActivity());
        fabImagen.setOnClickListener(v -> openAddImagenActivity());
        rvMain = findViewById(R.id.rvMain);
    }

    private void openAddImagenActivity() {
        Intent intent = new Intent(this, AddImagenActivity.class);

        ArrayList<Flora> floras = new ArrayList<>();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            floras.add(adapter.getItem(i));
            Log.v("jamaica", adapter.getItem(i).getNombre());
        }
        intent.putExtra("idFloras", floras);

        startActivity(intent);
    }

    private void openAddActivity() {
        Intent intent = new Intent(this, AddFloraActivity.class);
        startActivity(intent);
    }

    private void openEditFloraActivity(Flora f) {
        Intent intent = new Intent(this, EditFloraActivity.class);
        intent.putExtra("idFlora", f);

        ArrayList<Flora> floras = new ArrayList<>();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            floras.add(adapter.getItem(i));
        }
        intent.putExtra("idFloras", floras);

        startActivity(intent);
    }
}