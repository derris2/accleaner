package com.example.accleaner.dashboard;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.accleaner.machine.MachinesAdapter;
import com.example.accleaner.model.Machine;

public class DashboardActivity {
    private Context context;

    private List<Machine> machineList;
    private MachinesAdapter machinesAdapter;
    private TextView tvMachineWarn;
    private RecyclerView rvMyMachines;
    private ProgressBar pgLoading;

    public DashboardActivity(Context context) {
        this.context = context;
        machineList = new ArrayList<>();
        machinesAdapter = new MachinesAdapter(context,machineList);
    }

    private void getDataPair() {
        String urlGetPair =  "https://192.168.0.172/" + "/smart-building/master/machine/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlGetPair, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    machineList.clear();

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

                    if (machineList.isEmpty()) {
                        tvMachineWarn.setVisibility(View.VISIBLE);
                        tvMachineWarn.setText("Tidak ada machine");
                        rvMyMachines.setVisibility(View.GONE);
                    } else {
                        tvMachineWarn.setVisibility(View.GONE);
                        rvMyMachines.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    tvMachineWarn.setVisibility(View.VISIBLE);
                    tvMachineWarn.setText("Tidak ada machine");
                    rvMyMachines.setVisibility(View.GONE);
                }
                pgLoading.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvMachineWarn.setVisibility(View.VISIBLE);
                tvMachineWarn.setText("Tidak ada machine");
                rvMyMachines.setVisibility(View.GONE);
                pgLoading.setVisibility(View.GONE);
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                120 * 1000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }
}
