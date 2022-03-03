package org.izv.flora.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.view.adapter.viewholder.FloraViewHolder;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<FloraViewHolder> implements View.OnClickListener {

    private Context context;

    private List<Flora> floraList;
    private String ivFloraURL = "https://informatica.ieszaidinvergeles.org:10003/ad/floraV2/public/api/imagen/";

    private View.OnClickListener listener;

    public Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FloraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);

        view.setOnClickListener(listener);

        return new FloraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FloraViewHolder holder, int position) {

        Flora flora = floraList.get(position);
        holder.rvNombre.setText(flora.getNombre());
        holder.rvFamilia.setText(flora.getFamilia());
        Picasso.get().load(ivFloraURL + flora.getId() + "/flora")
                .memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(holder.rvImagen);    }

    @Override
    public int getItemCount() {
        if(floraList == null) {
            return 0;
        }
        return floraList.size();
    }

    public void setFloraList(List<Flora> floraList) {
        this.floraList = floraList;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public void update(List<Flora> floras) {
        floraList = floras;
        notifyDataSetChanged();
    }

    public Flora getItem(int poisition) {
        return floraList.get(poisition);
    }

}
