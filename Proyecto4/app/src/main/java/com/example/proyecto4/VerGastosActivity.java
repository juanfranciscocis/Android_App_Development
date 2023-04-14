package com.example.proyecto4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VerGastosActivity extends AppCompatActivity {

    private ArrayList<GastoNuevo> gastos = new ArrayList<>();
    private GastosArrayAdapter adapter;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_gastos);


        // Get all components
        FloatingActionButton getBack = findViewById(R.id.backButton2);
        Button filtroTipo = findViewById(R.id.filtroTipo);
        Button filtroFecha = findViewById(R.id.filtroFecha);
        Button filtroRango = findViewById(R.id.filtroRango);
        TextView menorQue = findViewById(R.id.menorQue);
        TextView mayorQue = findViewById(R.id.mayorQue);


        // Go back to the main activity
        getBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerGastosActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        gastos = Data.getInstance().getGastosUsuario();
        listView = findViewById(R.id.listViewGastos);
        adapter = new GastosArrayAdapter(this, gastos);
        listView.setAdapter(adapter);





        // load the gastos to the spinner
        Spinner dropdownGastos = findViewById(R.id.gastosDropDown);

        dropdownGastos.setOnTouchListener((v, event) -> {
            dropdownGastos.setAdapter(addItemsOnSpinner(Data.getInstance().getGastos()));
            return false;
        });

        // when pressing filtro tipo, filter the listview
        filtroTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gastoSeleccionado = dropdownGastos.getSelectedItem().toString();
                gastos = Data.getInstance().searchGastoUsuario(gastoSeleccionado);
                adapter = new GastosArrayAdapter(VerGastosActivity.this, gastos);
                listView.setAdapter(adapter);
            }
        });

        // when pressing filtro fecha, open the date picker and on ok filter the listview
        filtroFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open a date picker dialog
                DatePickerDialog datePicker = new DatePickerDialog(VerGastosActivity.this);
                datePicker.show();

                //When the date is selected, filter the listview
                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        String fechaSeleccionada = dayOfMonth + "/" + month + "/" + year;
                        gastos = Data.getInstance().searchGastoUsuarioFecha(fechaSeleccionada);
                        adapter = new GastosArrayAdapter(VerGastosActivity.this, gastos);
                        listView.setAdapter(adapter);
                    }
                });
            }
        });

        // when pressing filtro rango, take both menorQue and mayorQue and filter the listview
        filtroRango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menor = menorQue.getText().toString();
                String mayor = mayorQue.getText().toString();
                gastos = Data.getInstance().searchValorUsuario(Float.valueOf(menor), Float.valueOf(mayor));
                adapter = new GastosArrayAdapter(VerGastosActivity.this, gastos);
                listView.setAdapter(adapter);
            }
        });









    }


    //method to add items to the spinner, the method will take an arraylist as a parameter
    public ArrayAdapter<String> addItemsOnSpinner(ArrayList<String> list){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return dataAdapter;
    }
}