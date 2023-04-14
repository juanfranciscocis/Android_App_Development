package com.example.proyecto4;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

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


        // if click on item, show a toast with the item text
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a popup if ok is pressed then delete the item from the list ELSE do nothing
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Eliminar gasto");
                builder.setMessage("¿Está seguro que desea eliminar este gasto?");
                builder.setPositiveButton("OK", (dialog, which) -> {
                    objects.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Gasto eliminado", Toast.LENGTH_SHORT).show();
                });
                builder.setNegativeButton("Cancelar", (dialog, which) -> {
                    Toast.makeText(context, "Gasto no eliminado", Toast.LENGTH_SHORT).show();
                });
                builder.show();

            }
        });

        return convertView;
    }




}