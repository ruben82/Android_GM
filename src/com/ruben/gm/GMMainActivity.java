package com.ruben.gm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.ruben.gm.adapter.VehicleAdapter;
import com.ruben.gm.models.Authorization;
import com.ruben.gm.models.Vehicle;
import com.ruben.gm.utils.HTTPUTils;
import com.ruben.gm.utils.Utils;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GMMainActivity extends Activity
{
    /** Called when the activity is first created. */
    private Authorization authorization;
    private VehicleAdapter vehicleAdapter;
    private ListView vehiclesList;
    private Activity main=this;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        vehiclesList=(ListView)findViewById(R.id.vehicleList);

    }


    public void getToken(View view) throws IOException, JSONException {
        HttpResponse response = HTTPUTils.Authenticate();
        String returnString;
        if(response!=null){
            System.out.println("dopo Chiamata con status "+response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                returnString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                Log.i("AUTHENTICATE", returnString);
                response = null;
                JSONObject obj = new JSONObject(returnString);
                authorization= new Authorization("Bearer "+obj.getString("access_token"),obj.getString("expires_in"),obj.getString("token_type"));
                ((TextView)findViewById(R.id.token)).setText(authorization.token);
                findViewById(R.id.vehicleLayout).setVisibility(View.VISIBLE);
            }
        }
    }
    public void getVehicles(View view) throws IOException, JSONException {
        HttpResponse response = HTTPUTils.httpGetCall("?offset=0&size=2",authorization.token);
        String returnString;
        final List<Vehicle> list = new ArrayList<Vehicle>();
        if(response!=null){
            System.out.println("dopo Chiamata con status "+response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                returnString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                Log.i("AUTHENTICATE", returnString);
                response = null;
                JSONObject obj = new JSONObject(returnString);
                obj = obj.getJSONObject("vehicles");
                JSONArray arrayVehi = obj.getJSONArray("vehicle");

                Vehicle vehicle;
                for (int i = 0; i < arrayVehi.length(); i++) {
                    vehicle = new Vehicle(
                            arrayVehi.getJSONObject(i).getString("vin"),
                            arrayVehi.getJSONObject(i).getString("make"),
                            arrayVehi.getJSONObject(i).getString("model"),
                            arrayVehi.getJSONObject(i).getString("year"),
                            arrayVehi.getJSONObject(i).getString("manufacturer"),
                            arrayVehi.getJSONObject(i).getString("phone"),
                            arrayVehi.getJSONObject(i).getString("unitType"),
                            arrayVehi.getJSONObject(i).getString("primaryDriverId"),
                            arrayVehi.getJSONObject(i).getString("url"),
                            arrayVehi.getJSONObject(i).getString("primaryDriverUrl"));
                    System.out.println("\n" + (i + 1) + ") " + vehicle.toString());
                    list.add(vehicle);
                }
                vehicleAdapter= new VehicleAdapter(this, R.layout.vehicle_item,R.id.vin, list);
                vehiclesList.setAdapter(vehicleAdapter);
                vehiclesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent= new Intent(main,VehicleActivity.class);
                        intent.putExtra("token",authorization.token);
                        Bundle b= new Bundle();
                        b.putSerializable("vehicle",list.get(i));
                        intent.putExtras(b);
                        main.startActivity(intent);
                    }
                });
                vehicleAdapter.notifyDataSetChanged();
            }
        }
    }
}
