package org.izv.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.viewmodel.AddFloraViewModel;

import java.util.ArrayList;
import java.util.Locale;

public class AddFloraActivity extends AppCompatActivity {

    private TextInputEditText tiAddName, tiAddFamily, tiAddIdentification, tiAddHabitat,
            tiAddPhytosociology, tiAddBiotype, tiAddBioReproduction, tiAddFlowering,
            tiAddFructification, tiAddSexualExpression, tiAddPollination, tiAddDispersion,
            tiAddChromosomeNumber, tiAddReproduction, tiAddDistribution, tiAddBiology,
            tiAddDemography, tiAddThreats, tiAddElevation, tiAddProposedMeasures;

    private FloatingActionButton fabAddFlora, fabAddFloraCancel;

    Flora flora;

    private ArrayList<Flora> floras = new ArrayList<>();

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
        initComponents();
        setFabListeners(avm);
    }

    private void setFabListeners(AddFloraViewModel avm) {
        fabAddFlora.setOnClickListener(v -> {
            if (!isNameEmpty()){
                Flora flora = harvestFields();
                String nombreFlora = flora.getNombre().toLowerCase();
                boolean floraExists = false;

                for (int i = 0; i < floras.size(); i++){
                    if (floras.get(i).getNombre().toLowerCase().equals(nombreFlora)){
                        floraExists = true;
                    }
                }

                if (!floraExists){
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.alert_confirmation_add_title)
                            .setMessage(R.string.alert_confirmation_add_message_flora)
                            .setPositiveButton(R.string.yes, (dialog, which) -> {
                                avm.createFlora(flora);
                                Toast.makeText(this, R.string.completed_flora_add, Toast.LENGTH_SHORT).show();
                                cleanFields();
                            })
                            .setNegativeButton(R.string.no, (dialog, which) -> {
                                dialog.cancel();
                            })
                            .show();
                } else {
                    Toast.makeText(this, R.string.flora_exists, Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, R.string.uncomplete_flora_add, Toast.LENGTH_SHORT).show();
            }

        });

        fabAddFloraCancel.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.alert_cancel_add_title)
                    .setMessage(R.string.alert_cancel_add_message)
                    .setPositiveButton(R.string.yes, (dialog, which) -> {
                        finish();
                    })
                    .setNegativeButton(R.string.no, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    private void cleanFields() {
        tiAddName.setText("");
        tiAddFamily.setText("");
        tiAddIdentification.setText("");
        tiAddHabitat.setText("");
        tiAddPhytosociology.setText("");
        tiAddBiotype.setText("");
        tiAddBioReproduction.setText("");
        tiAddFlowering.setText("");
        tiAddFructification.setText("");
        tiAddSexualExpression.setText("");
        tiAddPollination.setText("");
        tiAddDispersion.setText("");
        tiAddChromosomeNumber.setText("");
        tiAddReproduction.setText("");
        tiAddDistribution.setText("");
        tiAddBiology.setText("");
        tiAddDemography.setText("");
        tiAddThreats.setText("");
        tiAddElevation.setText("");
        tiAddProposedMeasures.setText("");
    }

    private Flora harvestFields() {
        flora = new Flora();
        flora.setNombre(tiAddName.getText().toString());
        flora.setFamilia(setField(tiAddFamily));
        flora.setIdentificacion(setField(tiAddIdentification));
        flora.setHabitat(setField(tiAddHabitat));
        flora.setFitosociologia(setField(tiAddPhytosociology));
        flora.setBiotipo(setField(tiAddBiotype));
        flora.setBiologia_reproductiva(setField(tiAddBioReproduction));
        flora.setFloracion(setField(tiAddFlowering));
        flora.setFructificacion(setField(tiAddFructification));
        flora.setExpresion_sexual(setField(tiAddSexualExpression));
        flora.setPolinizacion(setField(tiAddPollination));
        flora.setDispersion(setField(tiAddDispersion));
        flora.setNumero_cromosomatico(setField(tiAddChromosomeNumber));
        flora.setReproduccion_asexual(setField(tiAddReproduction));
        flora.setDistribucion(setField(tiAddDistribution));
        flora.setBiologia(setField(tiAddBiology));
        flora.setDemografia(setField(tiAddDemography));
        flora.setAmenazas(setField(tiAddThreats));
        flora.setAltitud(setField(tiAddElevation));
        flora.setMedidas_propuestas(setField(tiAddProposedMeasures));
        return flora;
    }

    private String setField(TextInputEditText field){
        String fieldData;
        if (field.getText().toString().isEmpty()){
            fieldData = "No information provided...";
        } else {
            fieldData = field.getText().toString();
        }

        return fieldData;
    }

    private boolean isNameEmpty() {
        return tiAddName.getText().toString().isEmpty();
    }

    private void initComponents() {
        initTextInputEditTexts();
        fabAddFlora = findViewById(R.id.fabAddFlora);
        fabAddFloraCancel = findViewById(R.id.fabAddFloraCancel);
    }

    private void initTextInputEditTexts() {
        tiAddName = findViewById(R.id.tiAddName);
        tiAddFamily = findViewById(R.id.tiAddFamily);
        tiAddIdentification = findViewById(R.id.tiAddIdentification);
        tiAddHabitat = findViewById(R.id.tiAddHabitat);
        tiAddPhytosociology = findViewById(R.id.tiAddPhytosociology);
        tiAddBiotype = findViewById(R.id.tiAddBiotype);
        tiAddBioReproduction = findViewById(R.id.tiAddBioReproduction);
        tiAddFlowering = findViewById(R.id.tiAddFlowering);
        tiAddFructification = findViewById(R.id.tiAddFructification);
        tiAddSexualExpression = findViewById(R.id.tiAddSexualExpression);
        tiAddPollination = findViewById(R.id.tiAddPollination);
        tiAddDispersion = findViewById(R.id.tiAddDispersion);
        tiAddChromosomeNumber = findViewById(R.id.tiAddChromosomeNumber);
        tiAddReproduction = findViewById(R.id.tiAddReproduction);
        tiAddDistribution = findViewById(R.id.tiAddDistribution);
        tiAddBiology = findViewById(R.id.tiAddBiology);
        tiAddDemography = findViewById(R.id.tiAddDemography);
        tiAddThreats = findViewById(R.id.tiAddThreats);
        tiAddElevation = findViewById(R.id.tiAddElevation);
        tiAddProposedMeasures = findViewById(R.id.tiAddProposedMeasures);
    }
}