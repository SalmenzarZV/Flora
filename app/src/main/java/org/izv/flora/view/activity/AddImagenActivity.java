package org.izv.flora.view.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.entity.Imagen;
import org.izv.flora.viewmodel.AddImagenViewModel;

import java.util.ArrayList;
import java.util.Random;

public class AddImagenActivity extends AppCompatActivity {

    private ImageView ivImageFlora;
    Button btCancelAddImg, btAddImagen;
    TextInputEditText tilImageDescription;
    AutoCompleteTextView actvAddFloraName;

    private ActivityResultLauncher<Intent> launcher;
    private Intent resultadoImagen = null;
    private AddImagenViewModel aivm;

    private ArrayList<Flora> floras = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imagen);
        initialize();
    }

    private void initialize() {
        launcher = getLauncher();

        tilImageDescription = findViewById(R.id.tilAddImageDescription);
        actvAddFloraName = findViewById(R.id.actvAddFloraName);

        ivImageFlora = findViewById(R.id.ivAddImageFlora);
        ivImageFlora.setOnClickListener(v -> {
            selectImage();
        });

        initButtons();

        aivm = new ViewModelProvider(this).get(AddImagenViewModel.class);

        floras = (ArrayList<Flora>) getIntent().getSerializableExtra("idFloras");
        String[] type = new String[floras.size()];
        for (int i = 0; i < floras.size(); i++) {
            type[i] = floras.get(i).getNombre();
        }

        adapter = new ArrayAdapter<>(this, R.layout.name_dropdown_item, R.id.tvDropdownName, type);
        actvAddFloraName.setAdapter(adapter);
    }

    private void initButtons() {
        btAddImagen = findViewById(R.id.btAddImagen);
        btAddImagen.setOnClickListener(v -> {
            uploadDataImage();
        });

        btCancelAddImg = findViewById(R.id.btCancelAddImg);
        btCancelAddImg.setOnClickListener(view -> finish());
    }

    private void uploadDataImage() {
        long id = 0;
        for (int i = 0; i < floras.size(); i++) {
            if (actvAddFloraName.getText().toString().equals(floras.get(i).getNombre())) {
                id = floras.get(i).getId();
            }
        }
        // Los caracteres de interés en un array de char.
        char [] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

        // Longitud del array de char.
                int charsLength = chars.length;

        // Instanciamos la clase Random
                Random random = new Random();

        // Un StringBuffer para componer la cadena aleatoria de forma eficiente
                StringBuffer buffer = new StringBuffer();

        // Bucle para elegir una cadena de 10 caracteres al azar
        for (int i=0;i<10;i++){
            // Añadimos al buffer un caracter al azar del array
            buffer.append(chars[random.nextInt(charsLength)]);
        }
        String idFlora = String.valueOf(id);
        String nombre = actvAddFloraName.getText().toString() + "_" + buffer.toString();
        String descripcion = tilImageDescription.getText().toString();


        if(!(nombre.trim().isEmpty() ||
                resultadoImagen == null)) {
            Imagen imagen = new Imagen();
            imagen.nombre = nombre;
            imagen.descripcion = descripcion;
            imagen.idflora = id;
            aivm.saveImagen(resultadoImagen, imagen);
            Toast.makeText(this, "Añadido primo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Casi picha", Toast.LENGTH_SHORT).show();
        }

    }

    ActivityResultLauncher<Intent> getLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    //respuesta al resultado de haber seleccionado una imagen
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        resultadoImagen = result.getData();
                        Uri uri = resultadoImagen.getData();
                        Picasso.get().load(uri).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(ivImageFlora);
                    }
                }
        );
    }

    Intent getContentIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    void selectImage() {
        Intent intent = getContentIntent();
        launcher.launch(intent);
    }
}