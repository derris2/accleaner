package com.app.cleanspace.machine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.remote.innopharm.R;
import com.app.cleanspace.model.Machine;

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
        View view   = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_machines, parent, false);
        return new MachinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MachinesViewHolder holder, int position) {
        Machine machine = machineList.get(position);
        holder.tvMachineName.setText(machine.getName());
        holder.tvMacAddress.setText(machine.getMacAddress());
        holder.clRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return machineList.size();
    }

    public class MachinesViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout    clRoot;
        private TextView            tvMachineName, tvMacAddress;
        public MachinesViewHolder(@NonNull View itemView) {
            super(itemView);
            clRoot          = itemView.findViewById(R.id.clRoot);
            tvMachineName    = itemView.findViewById(R.id.tvMachineName);
            tvMacAddress    = itemView.findViewById(R.id.tvMacAddress);
        }
    }
}
