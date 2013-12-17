package com.ruben.gm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ruben.gm.models.Vehicle;
import com.ruben.gm.utils.HTTPUTils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: r.deluca
 * Date: 04/07/13
 * Time: 11.50
 * To change this template use File | Settings | File Templates.
 */
public class VehicleActivity extends Activity {
    private Vehicle vehicle;
    private String token;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_layout);
        vehicle=(Vehicle)getIntent().getExtras().getSerializable("vehicle");
        token=getIntent().getStringExtra("token");
    }

    public void getDiagnostic(View view) throws JSONException, IOException {
      String   jsonBody="{ 'DiagnosticRequest': {\n" +
                "    'diagnosticItem': [\n" +
                "      'GET COMMUTE SCHEDULE' ," +
              "        'GET CHARGE MODE'  ]"+
                "}}";

        JSONObject nn=new JSONObject(jsonBody);
        String returnString = "";
        StringEntity se = new StringEntity(nn.toString(),"UTF-8");
        HttpResponse response = HTTPUTils.httpPostCall("/"+vehicle.getVin()+"/commands/diagnostics", se,token);


        if(response!=null){
            System.out.println("dopo Chiamata con status "+response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 202) {
                returnString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                Log.i("Diagnostic", returnString);
                response = null;
                JSONObject obj = new JSONObject(returnString);
            }
        }
    }
    public void lockDoor(View view) throws JSONException, IOException {
        String jsonBody="{" +
                "    'lockDoorRequest':\n" +
                "    {\n" +
                "        'delay':'0'\n" +
                "    }\n" +
                "}";

        JSONObject nn=new JSONObject(jsonBody);
        String returnString = "";
        StringEntity se = new StringEntity(nn.toString(),"UTF-8");
        HttpResponse response = HTTPUTils.httpPostCall("/"+vehicle.getVin()+"/commands/lockDoor", se,token);


        if(response!=null){
            System.out.println("dopo Chiamata con status "+response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 202) {
                returnString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                Log.i("LOCKDOOR", returnString);
                response = null;
                JSONObject obj = new JSONObject(returnString);
            }
        }
    }
}