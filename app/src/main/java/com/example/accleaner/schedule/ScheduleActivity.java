package com.example.accleaner.schedule;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.accleaner.MainActivity;
import com.example.accleaner.R;
import com.example.accleaner.model.Machine;
import com.example.accleaner.model.Schedule;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScheduleActivity extends AppCompatActivity {
    private Context context;
    private RecyclerView rvSchedule;
    private List<Schedule> scheduleList;
    private ScheduleAdapter scheduleAdapter;
    private SwipeRefreshLayout swipeRefreshLayout1;
    private ProgressBar pgLoading;
    private TextView tvScheduleWarn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        rvSchedule = findViewById(R.id.recyclerView);
        swipeRefreshLayout1 = findViewById(R.id.swipeRefreshLayout1);
        rvSchedule.setLayoutManager(new LinearLayoutManager(context));

        scheduleList = new ArrayList<>();
        scheduleAdapter = new ScheduleAdapter(context, scheduleList);
        rvSchedule.setAdapter(scheduleAdapter);

        tvScheduleWarn = findViewById(R.id.tvScheduleWarn);
        swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        String urlGetSchedule = "http://192.168.0.172:8080/smart-building/master/schedule";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlGetSchedule, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("JSON Response", response.toString()); // Gunakan Log untuk mencetak ke Logcat
                            System.out.println("JSON Response: " + response.toString()); // Atau gunakan System.out.println() untuk mencetak ke konsol

                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Schedule schedule = new Schedule();
                                schedule.setId(jsonObject.getString("schedule_id"));
                                schedule.setName(jsonObject.getString("schedule_name"));
                                schedule.setDefault_yn(jsonObject.getString("default_yn"));
                                schedule.setMonday_yn(jsonObject.getString("monday_yn"));
                                schedule.setTuesday_yn(jsonObject.getString("tuesday_yn"));
                                schedule.setWednesday_yn(jsonObject.getString("wednesday_yn"));
                                schedule.setThursday_yn(jsonObject.getString("thursday_yn"));
                                schedule.setFriday_yn(jsonObject.getString("friday_yn"));
                                schedule.setSaturday_yn(jsonObject.getString("saturday_yn"));
                                schedule.setSunday_yn(jsonObject.getString("sunday_yn"));
                                schedule.setStart_hour(jsonObject.getString("start_hour"));
                                schedule.setRepeated_interval(jsonObject.getString("repeated_interval"));
                                schedule.setRepeated_unit(jsonObject.getString("repeated_unit"));
                                Toast.makeText(ScheduleActivity.this, jsonObject.getString("repeated_unit"), Toast.LENGTH_LONG).show();
                                schedule.setSchedule_status(jsonObject.getString("schedule_status"));
                                scheduleList.add(schedule);
                            }
                            scheduleAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ScheduleActivity.this, "Error parsing JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ScheduleActivity.this, "ErrorL: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_schedule);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_home) {
                   Intent intent = new Intent(ScheduleActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (id == R.id.navigation_schedule) {
                    Toast.makeText(ScheduleActivity.this, "124", Toast.LENGTH_LONG).show();
                    return true;
                } else if (id == R.id.navigation_profile) {
                    Toast.makeText(ScheduleActivity.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    private void loadData() {
        scheduleList.clear(); // Clear existing data before reloading
        scheduleAdapter.notifyDataSetChanged(); // Notify adapter that data has changed

        String urlGetSchedule = "http://192.168.0.172:8080/smart-building/master/schedule";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlGetSchedule, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Schedule schedule = new Schedule();
                                schedule.setId(jsonObject.getString("schedule_id"));
                                schedule.setName(jsonObject.getString("schedule_name"));
                                schedule.setDefault_yn(jsonObject.getString("default_yn"));
                                schedule.setMonday_yn(jsonObject.getString("monday_yn"));
                                schedule.setTuesday_yn(jsonObject.getString("tuesday_yn"));
                                schedule.setWednesday_yn(jsonObject.getString("wednesday_yn"));
                                schedule.setThursday_yn(jsonObject.getString("thursday_yn"));
                                schedule.setFriday_yn(jsonObject.getString("friday_yn"));
                                schedule.setSaturday_yn(jsonObject.getString("saturday_yn"));
                                schedule.setSunday_yn(jsonObject.getString("sunday_yn"));
                                schedule.setStart_hour(jsonObject.getString("start_hour"));
                                schedule.setRepeated_interval(jsonObject.getString("repeated_interval"));
                                schedule.setRepeated_unit(jsonObject.getString("repeated_unit"));
                                Toast.makeText(ScheduleActivity.this, jsonObject.getString("repeated_unit"), Toast.LENGTH_LONG).show();
                                schedule.setSchedule_status(jsonObject.getString("schedule_status"));
                                scheduleList.add(schedule);
                            }
                            scheduleAdapter.notifyDataSetChanged();
                            Toast.makeText(ScheduleActivity.this, "Refresh Berhasil", Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ScheduleActivity.this, "Error parsing JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ScheduleActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

        swipeRefreshLayout1.setRefreshing(false);
    }
}
