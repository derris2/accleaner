package com.example.accleaner;

import android.os.Bundle;
import android.widget.Toast;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.accleaner.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MachinesAdapter machinesAdapter;
    private List<Machine> machineList;

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
                // Panggil metode untuk memuat ulang data
                loadData();
            }
        });

        // Mulai memuat data saat aktivitas pertama kali dibuat
        loadData();

        String urlGetPair = "http://192.168.0.172:8080/smart-building/master/machine";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlGetPair, null,
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

                            // Tampilkan pesan toast untuk memberitahu pengguna bahwa data berhasil diambil
                            Toast.makeText(MainActivity.this, "Data berhasil diambil dari URL: " + urlGetPair, Toast.LENGTH_LONG).show();
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
    }
    private void loadData() {
        machineList.clear(); // Bersihkan data sebelum memuat ulang
        machinesAdapter.notifyDataSetChanged(); // Notifikasi adapter bahwa data telah berubah

        String urlGetPair = "http://192.168.0.172:8080/smart-building/master/machine";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlGetPair, null,
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

                            // Tampilkan pesan toast untuk memberitahu pengguna bahwa data berhasil diambil
                            Toast.makeText(MainActivity.this, "Data berhasil diambil dari URL: " + urlGetPair, Toast.LENGTH_LONG).show();
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
