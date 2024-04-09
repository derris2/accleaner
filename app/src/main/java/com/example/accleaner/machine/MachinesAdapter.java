package com.example.accleaner.machine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accleaner.R;
import com.example.accleaner.model.*;

import java.util.List;

public class MachinesAdapter extends RecyclerView.Adapter<MachinesAdapter.MachinesViewHolder> {
    private Context         context;
    private List<Machine>    machineList;

    public MachinesAdapter(Context context, List<Machine> machineList) {
        this.context = context;
        this.machineList = machineList;
    }

    @NonNull
    @Override
    public MachinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new MachinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MachinesViewHolder holder, int position) {
        Machine machine = machineList.get(position);
        holder.tvMachineName.setText(machine.getName());
        holder.tvMachineTypeName.setText(machine.getMachineTypeName());
        holder.tvLocation.setText(machine.getLocationName());
    }

    @Override
    public int getItemCount() {
        return machineList.size();
    }

    public class MachinesViewHolder extends RecyclerView.ViewHolder {
        private TextView            tvMachineName, tvMachineTypeName, tvLocation;
        public MachinesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMachineName    = itemView.findViewById(R.id.tvMachineName);
            tvMachineTypeName    = itemView.findViewById(R.id.tvMachineTypeName);
            tvLocation    = itemView.findViewById(R.id.tvLocation);
        }
    }
}
