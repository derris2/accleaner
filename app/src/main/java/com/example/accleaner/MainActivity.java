package com.example.accleaner;

import com.example.accleaner.machine.MachinesAdapter;
import com.example.accleaner.model.Machine;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<String> itemList;

    private MachinesAdapter machinesAdapter;
    private List<Machine> machineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        itemList = new ArrayList<>();
//        adapter = new ItemAdapter(itemList);
//        recyclerView.setAdapter(adapter);
//
//        generateDummyData();

        machineList = new ArrayList<>();
        machinesAdapter = new MachinesAdapter(this, machineList);
        recyclerView.setAdapter(machinesAdapter);


    }

    private void generateDummyData() {
        for (int i = 0; i < 20; i++) {
            itemList.add("Item " + (i + 1));
        }
        adapter.notifyDataSetChanged();
    }

    private void generateDummyDataDerris() throws JSONException {

        JSONArray jsonArray = new JSONArray();

        try {
            // Create dummy machine data
            JSONObject machine1 = new JSONObject();
            machine1.put("machine_id", 1);
            machine1.put("machine_name", "Machine 1");
            machine1.put("machine_type_name", "Type A");

            JSONObject machine2 = new JSONObject();
            machine2.put("machine_id", 2);
            machine2.put("machine_name", "Machine 2");
            machine2.put("machine_type_name", "Type B");

            JSONObject machine3 = new JSONObject();
            machine3.put("machine_id", 3);
            machine3.put("machine_name", "Machine 3");
            machine3.put("machine_type_name", "Type C");

            // Add the dummy machine data to the JSONArray
            jsonArray.put(machine1);
            jsonArray.put(machine2);
            jsonArray.put(machine3);

            // Now you can use this jsonArray as the data you received from the server
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Machine machine = new Machine();
            machine.setId(jsonObject.getString("machine_id"));
            machine.setName(jsonObject.getString("machine_name"));
            machine.setMachineType(jsonObject.getString("machine_type"));
            machineList.add(machine);
        }

        adapter.notifyDataSetChanged();
    }


}
