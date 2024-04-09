package com.example.accleaner;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.accleaner.machine.MachinesAdapter;
import com.example.accleaner.model.Machine;
import com.example.accleaner.schedule.ScheduleActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.accleaner.R;
import com.example.accleaner.machine.MachinesAdapter;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Machine> machineList;
    private MachinesAdapter machinesAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        machineList = new ArrayList<>();
        machinesAdapter = new MachinesAdapter(this, machineList);
        recyclerView.setAdapter(machinesAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        String urlGetMachine = "http://192.168.0.172:8080/smart-building/master/machine";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlGetMachine, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Machine machine = new Machine();
                                machine.setId(jsonObject.getString("machine_id"));
                                machine.setType(jsonObject.getString("machine_type_id"));
                                machine.setLocationId(jsonObject.getString("location_id"));
                                machine.setLocationName(jsonObject.getString("location_name"));
                                machine.setName(jsonObject.getString("machine_name"));
                                machine.setMachineTypeName(jsonObject.getString("machine_type_name"));
                                machineList.add(machine);
                            }
                            machinesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error parsing JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "ErrorL: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_home) {
//                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "10*", Toast.LENGTH_LONG).show();
                    return true;
                } else if (id == R.id.navigation_schedule) {
                    Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.navigation_profile) {
//                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    private void loadData() {
        machineList.clear(); // Bersihkan data sebelum memuat ulang
        machinesAdapter.notifyDataSetChanged(); // Notifikasi adapter bahwa data telah berubah

        String urlGetMachine = "http://192.168.0.172:8080/smart-building/master/machine";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlGetMachine, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Mengambil JSONArray dari respons JSON
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                // Membuat objek Machine dari setiap objek JSON dalam array
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Machine machine = new Machine();
                                machine.setId(jsonObject.getString("machine_id"));
                                machine.setType(jsonObject.getString("machine_type_id"));
                                machine.setLocationId(jsonObject.getString("location_id"));
                                machine.setLocationName(jsonObject.getString("location_name"));
                                machine.setName(jsonObject.getString("machine_name"));
                                machine.setMachineTypeName(jsonObject.getString("machine_type_name"));
                                machineList.add(machine);
                            }
                            machinesAdapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Refresh Berhasil", Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error parsing JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

        swipeRefreshLayout.setRefreshing(false);
    }
}
