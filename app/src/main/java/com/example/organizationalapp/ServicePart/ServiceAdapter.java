package com.example.organizationalapp.ServicePart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.organizationalapp.R;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {
    private Context context;
    private List<Services> services;

    public ServiceAdapter(Context context, List<Services> services) {
        this.context = context;
        this.services = services;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_layout, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Services service = services.get(position);
        holder.serviceName.setText(service.getName());
        holder.img.setImageResource(service.getImg());
        holder.img.setColorFilter(service.getColor());

    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView serviceName;


        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_service);
            serviceName = itemView.findViewById(R.id.name_service);

        }
    }
}
