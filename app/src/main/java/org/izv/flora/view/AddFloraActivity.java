package org.izv.flora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.viewmodel.AddFloraViewModel;

public class AddFloraActivity extends AppCompatActivity {

    private EditText etNombre;
    private Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flora);
        initialize();
    }

    private void initialize() {
        AddFloraViewModel avm = new ViewModelProvider(this).get(AddFloraViewModel.class);
        avm.getAddFloraLiveData().observe(this, aLong -> {
            if(aLong > 0) {
                finish();
            } else {
                Toast.makeText(AddFloraActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
        etNombre = findViewById(R.id.tfAddName);
        btAdd = findViewById(R.id.fabAddFlora);
        btAdd.setOnClickListener(v -> {
            Flora flora = new Flora();
            flora.setNombre(etNombre.getText().toString());
            avm.createFlora(flora);
        });
    }
}