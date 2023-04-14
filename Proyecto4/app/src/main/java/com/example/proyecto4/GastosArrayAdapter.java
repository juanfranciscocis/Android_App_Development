package com.example.proyecto4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GastosArrayAdapter extends ArrayAdapter<GastoNuevo> {

    ArrayList<GastoNuevo> objects;

    public GastosArrayAdapter(Context context, ArrayList<GastoNuevo> objects) {
        super(context, -1, objects);
        this.objects = objects;
    }

    private static class ViewHolder {
        TextView gastoText;
        TextView fechaText;
        TextView valorText;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ArrayList<GastoNuevo> model = objects;

        ViewHolder viewHolder; // view lookup cache stored in tag

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_gastos_array_adapter, parent, false);
            viewHolder.gastoText = (TextView) convertView.findViewById(R.id.gastoText);
            viewHolder.fechaText = (TextView) convertView.findViewById(R.id.fechaText);
            viewHolder.valorText = (TextView) convertView.findViewById(R.id.valorText);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Context context = getContext();
        viewHolder.gastoText.setText(model.get(position).getGasto());
        viewHolder.fechaText.setText(model.get(position).getFecha());
        viewHolder.valorText.setText(model.get(position).getValor().toString());
        return convertView;
    }


}