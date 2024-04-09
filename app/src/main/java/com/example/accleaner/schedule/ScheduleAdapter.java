package com.example.accleaner.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accleaner.R;
import com.example.accleaner.model.Schedule;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private Context context;
    private List<Schedule> scheduleList;

    public ScheduleAdapter(Context context, List<Schedule> scheduleList) {
        this.context = context;
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        holder.tvScheduleStartHour.setText(schedule.getStart_hour());
        String daysText = "";

        if (schedule.getMonday_yn().equals("Y") &&
                schedule.getTuesday_yn().equals("Y") &&
                schedule.getWednesday_yn().equals("Y") &&
                schedule.getThursday_yn().equals("Y") &&
                schedule.getFriday_yn().equals("Y") &&
                schedule.getSaturday_yn().equals("Y") &&
                schedule.getSunday_yn().equals("Y")) {
            daysText = "Everyday";
        } else {
            StringBuilder daysTextBuilder = new StringBuilder();

            if (schedule.getMonday_yn().equals("Y")) {
                daysTextBuilder.append("Monday");
            }
            if (schedule.getTuesday_yn().equals("Y")) {
                appendCommaIfNotEmpty(daysTextBuilder);
                daysTextBuilder.append("Tuesday");
            }
            if (schedule.getWednesday_yn().equals("Y")) {
                appendCommaIfNotEmpty(daysTextBuilder);
                daysTextBuilder.append("Wednesday");
            }
            if (schedule.getThursday_yn().equals("Y")) {
                appendCommaIfNotEmpty(daysTextBuilder);
                daysTextBuilder.append("Thursday");
            }
            if (schedule.getFriday_yn().equals("Y")) {
                appendCommaIfNotEmpty(daysTextBuilder);
                daysTextBuilder.append("Friday");
            }
            if (schedule.getSaturday_yn().equals("Y")) {
                appendCommaIfNotEmpty(daysTextBuilder);
                daysTextBuilder.append("Saturday");
            }
            if (schedule.getSunday_yn().equals("Y")) {
                appendCommaIfNotEmpty(daysTextBuilder);
                daysTextBuilder.append("Sunday");
            }

            daysText = daysTextBuilder.toString();
        }

        if (daysText.isEmpty()) {
            holder.tvScheduleDays.setText("No schedule set");
        } else {
            holder.tvScheduleDays.setText(daysText);
        }

        String repeatedInfo = "Every " + schedule.getRepeated_interval() + " " + schedule.getRepeated_unit();
        holder.tvScheduleRepeatedInterval.setText(repeatedInfo);

        holder.switchButton.setChecked(schedule.getSchedule_status().equals("ON"));
    }

    private void appendCommaIfNotEmpty(StringBuilder builder) {
        if (builder.length() > 0) {
            builder.append(", ");
        }
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        private TextView tvScheduleStartHour,tvScheduleDays,tvScheduleRepeatedInterval;
        private Switch switchButton;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvScheduleStartHour = itemView.findViewById(R.id.tvScheduleStartHour);
            tvScheduleDays = itemView.findViewById(R.id.tvScheduleDays);
            tvScheduleRepeatedInterval = itemView.findViewById(R.id.tvScheduleRepeatedInterval);
            switchButton = itemView.findViewById(R.id.switchButton);
        }
    }
}
