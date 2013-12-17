package com.ruben.gm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ruben.gm.R;
import com.ruben.gm.models.Vehicle;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: r.deluca
 * Date: 04/07/13
 * Time: 11.31
 * To change this template use File | Settings | File Templates.
 */
public class VehicleAdapter extends ArrayAdapter<Vehicle> {
    private LayoutInflater mInflater;

    private List<Vehicle>  vehicle;
    private int mIdRisorsaVista;

    public VehicleAdapter(Context ctx, int listLayout, int feedItem, List<Vehicle> obj) {
        super(ctx, feedItem, obj);
        //per instanziare un file xml layout nel suo oggetto vista corrispondente
        mInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vehicle=obj;
        mIdRisorsaVista = listLayout;
    }

    @Override
    public int getCount() {
        return vehicle.size();
    }

    @Override
    public Vehicle getItem(int position) {
        return vehicle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try{
            Vehicle vehicle1=vehicle.get(position);
            convertView = mInflater.inflate(mIdRisorsaVista,parent, false);
            ((TextView)convertView.findViewById(R.id.vin)).setText(vehicle1.getVin());
            ((TextView)convertView.findViewById(R.id.make)).setText(vehicle1.getMake());
            ((TextView)convertView.findViewById(R.id.model)).setText(vehicle1.getModel());
        }catch (Exception e) {

        }
        return convertView;
    }
}