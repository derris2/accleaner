package com.example.accleaner;

// ItemViewHolder.java
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    TextView subTextView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        subTextView = itemView.findViewById(R.id.subTextView);
    }
}

