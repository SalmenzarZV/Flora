package org.izv.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.viewmodel.EditFloraViewModel;

import java.util.ArrayList;

public class EditFloraActivity extends AppCompatActivity {

    private TextInputEditText tiEditName, tiEditFamily, tiEditIdentification, tiEditHabitat,
            tiEditPhytosociology, tiEditBiotype, tiEditBioReproduction, tiEditFlowering,
            tiEditFructification, tiEditSexualExpression, tiEditPollination, tiEditDispersion,
            tiEditChromosomeNumber, tiEditReproduction, tiEditDistribution, tiEditBiology,
            tiEditDemography, tiEditThreats, tiEditElevation, tiEditProposedMeasures;

    private ImageView ivEditImage;

    private FloatingActionButton fabEditFlora, fabEditFloraCancel, fabDeleteFlora;
    Flora flora;
    private ArrayList<Flora> floras = new ArrayList<>();
    private String ivFloraURL = "https://informatica.ieszaidinvergeles.org:10003/ad/floraV2/public/api/imagen/";
    private EditFloraViewModel efvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flora);
        initialize();
    }

    private void initialize() {
        efvm = new ViewModelProvider(this).get(EditFloraViewModel.class);
        initFields();
        fabEditFlora = findViewById(R.id.fabEditFlora);
        fabEditFloraCancel = findViewById(R.id.fabEditFloraCancel);
        fabDeleteFlora = findViewById(R.id.fabDeleteFlora);
        ivEditImage = findViewById(R.id.ivEditImage);
        setFlora();
        Picasso.get().load(ivFloraURL + flora.getId() + "/flora").memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(ivEditImage);
        setFabListeners(efvm);

    }

    private void setFabListeners(EditFloraViewModel efvm) {
        fabEditFlora.setOnClickListener(v -> {
            if (!isNameEmpty()){
                new AlertDialog.Builder(this)
                        .setTitle(R.string.alert_confirmation_edit_title)
                        .setMessage(R.string.alert_confirmation_edit_message_flora)
                        .setPositiveButton(R.string.yes, (dialog, which) -> {
                            Log.v("jamaica", flora.getFamilia());
                            efvm.editFlora(this.flora.getId(), harvestFields());
                            setFlora(flora);
                        })
                        .setNegativeButton(R.string.no, (dialog, which) -> {
                            dialog.cancel();
                        })
                        .show();
            } else {
                Toast.makeText(this, R.string.uncomplete_flora_add, Toast.LENGTH_SHORT).show();
            }

        });

        fabEditFloraCancel.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.alert_cancel_add_title)
                    .setMessage(R.string.alert_cancel_edit_message)
                    .setPositiveButton(R.string.yes, (dialog, which) -> {
                        finish();
                    })
                    .setNegativeButton(R.string.no, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });

        fabDeleteFlora.setOnClickListener(view -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.alert_delete_flora_title)
                    .setMessage(R.string.alert_delete_flora_message)
                    .setPositiveButton(R.string.yes, (dialog, which) -> {
                        Log.v("jamaica", String.valueOf(this.flora.getId()));
                        efvm.deleteFlora(this.flora.getId());
                        Toast.makeText(this, R.string.flora_deleted, Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton(R.string.no, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    private Flora harvestFields() {
        flora = new Flora();
        flora.setNombre(tiEditName.getText().toString());
        flora.setFamilia(setField(tiEditFamily));
        flora.setIdentificacion(setField(tiEditIdentification));
        flora.setHabitat(setField(tiEditHabitat));
        flora.setFitosociologia(setField(tiEditPhytosociology));
        flora.setBiotipo(setField(tiEditBiotype));
        flora.setBiologia_reproductiva(setField(tiEditBioReproduction));
        flora.setFloracion(setField(tiEditFlowering));
        flora.setFructificacion(setField(tiEditFructification));
        flora.setExpresion_sexual(setField(tiEditSexualExpression));
        flora.setPolinizacion(setField(tiEditPollination));
        flora.setDispersion(setField(tiEditDispersion));
        flora.setNumero_cromosomatico(setField(tiEditChromosomeNumber));
        flora.setReproduccion_asexual(setField(tiEditReproduction));
        flora.setDistribucion(setField(tiEditDistribution));
        flora.setBiologia(setField(tiEditBiology));
        flora.setDemografia(setField(tiEditDemography));
        flora.setAmenazas(setField(tiEditThreats));
        flora.setAltitud(setField(tiEditElevation));
        flora.setMedidas_propuestas(setField(tiEditProposedMeasures));
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
        return tiEditName.getText().toString().isEmpty();
    }

    private void initFields() {
        tiEditName = findViewById(R.id.tiEditName);
        tiEditFamily = findViewById(R.id.tiEditFamily);
        tiEditIdentification = findViewById(R.id.tiEditIdentification);
        tiEditHabitat = findViewById(R.id.tiEditHabitat);
        tiEditPhytosociology = findViewById(R.id.tiEditPhytosociology);
        tiEditBiotype = findViewById(R.id.tiEditBiotype);
        tiEditBioReproduction = findViewById(R.id.tiEditBioReproduction);
        tiEditFlowering = findViewById(R.id.tiEditFlowering);
        tiEditFructification = findViewById(R.id.tiEditFructification);
        tiEditSexualExpression = findViewById(R.id.tiEditSexualExpression);
        tiEditPollination = findViewById(R.id.tiEditPollination);
        tiEditDispersion = findViewById(R.id.tiEditDispersion);
        tiEditChromosomeNumber = findViewById(R.id.tiEditChromosomeNumber);
        tiEditReproduction = findViewById(R.id.tiEditReproduction);
        tiEditDistribution = findViewById(R.id.tiEditDistribution);
        tiEditBiology = findViewById(R.id.tiEditBiology);
        tiEditDemography = findViewById(R.id.tiEditDemography);
        tiEditThreats = findViewById(R.id.tiEditThreats);
        tiEditElevation = findViewById(R.id.tiEditElevation);
        tiEditProposedMeasures = findViewById(R.id.tiEditProposedMeasures);
    }

    private void setFlora() {

        Bundle bundle = getIntent().getExtras();
        flora = bundle.getParcelable("idFlora");

        tiEditName.setText(flora.getNombre());
        tiEditFamily.setText(flora.getFamilia());
        tiEditIdentification.setText(flora.getIdentificacion());
        tiEditHabitat.setText(flora.getHabitat());
        tiEditPhytosociology.setText(flora.getFitosociologia());
        tiEditBiotype.setText(flora.getBiotipo());
        tiEditBioReproduction.setText(flora.getBiologia_reproductiva());
        tiEditFlowering.setText(flora.getFloracion());
        tiEditFructification.setText(flora.getFructificacion());
        tiEditSexualExpression.setText(flora.getExpresion_sexual());
        tiEditPollination.setText(flora.getPolinizacion());
        tiEditDispersion.setText(flora.getDispersion());
        tiEditChromosomeNumber.setText(flora.getNumero_cromosomatico());
        tiEditReproduction.setText(flora.getReproduccion_asexual());
        tiEditDistribution.setText(flora.getDistribucion());
        tiEditBiology.setText(flora.getBiologia());
        tiEditDemography.setText(flora.getDemografia());
        tiEditThreats.setText(flora.getAmenazas());
        tiEditElevation.setText(flora.getAltitud());
        tiEditProposedMeasures.setText(flora.getMedidas_propuestas());
    }

    private void setFlora(Flora flora){
        tiEditName.setText(flora.getNombre());
        tiEditFamily.setText(flora.getFamilia());
        tiEditIdentification.setText(flora.getIdentificacion());
        tiEditHabitat.setText(flora.getHabitat());
        tiEditPhytosociology.setText(flora.getFitosociologia());
        tiEditBiotype.setText(flora.getBiotipo());
        tiEditBioReproduction.setText(flora.getBiologia_reproductiva());
        tiEditFlowering.setText(flora.getFloracion());
        tiEditFructification.setText(flora.getFructificacion());
        tiEditSexualExpression.setText(flora.getExpresion_sexual());
        tiEditPollination.setText(flora.getPolinizacion());
        tiEditDispersion.setText(flora.getDispersion());
        tiEditChromosomeNumber.setText(flora.getNumero_cromosomatico());
        tiEditReproduction.setText(flora.getReproduccion_asexual());
        tiEditDistribution.setText(flora.getDistribucion());
        tiEditBiology.setText(flora.getBiologia());
        tiEditDemography.setText(flora.getDemografia());
        tiEditThreats.setText(flora.getAmenazas());
        tiEditElevation.setText(flora.getAltitud());
        tiEditProposedMeasures.setText(flora.getMedidas_propuestas());
    }
}