package com.example.accleaner;

// ItemViewHolder.java
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView tvMachineId, tvMachineName, tvMachineTypeName;

    public ItemViewHolder(View itemView) {
        super(itemView);
        tvMachineId = itemView.findViewById(R.id.tvMachineId);
        tvMachineName = itemView.findViewById(R.id.tvMachineName);
        tvMachineTypeName = itemView.findViewById(R.id.tvMachineTypeName);
    }
}

