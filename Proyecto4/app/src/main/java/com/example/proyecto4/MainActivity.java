package com.example.proyecto4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String gastoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get all components
        Spinner dropdownGastos = findViewById(R.id.dropdownGastos);
        TextView fechaDisplay = findViewById(R.id.fechaDisplay);
        Button btnElegirFecha = findViewById(R.id.fechaBoton);
        TextView valor = findViewById(R.id.valorTextField);
        Button btnAgregar = findViewById(R.id.agregarGastoBoton);
        Button cambiarGastos = findViewById(R.id.cambiarBoton);
        Button verGastos = findViewById(R.id.verBoton);


        // Add the list to the spinner
        dropdownGastos.setAdapter(addItemsOnSpinner(Data.getInstance().getGastos()));

        // Chooser for the date
        btnElegirFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Open a date picker dialog
                DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this);
                datePicker.show();

                //Get the date selected
                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Set the date selected to the text view
                        fechaDisplay.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                });

            }
        });


        // Add a new gasto to the Singleton
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the gasto selected
                gastoSeleccionado = dropdownGastos.getSelectedItem().toString();
                // Get the date selected, if none show a Toast
                String fechaSeleccionada = fechaDisplay.getText().toString();
                if (fechaSeleccionada.equals("AQUI FECHA") || fechaSeleccionada.equals("")) {
                    // Show a Toast
                    Toast.makeText(MainActivity.this, "ERROR NO HAY FECHA SELECIONADA", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Get the valor, if none show a Toast

                Float valorSeleccionado;
                try {
                    valorSeleccionado = Float.parseFloat(valor.getText().toString());
                    if (valorSeleccionado <= 0) {
                        // Show a Toast
                        Toast.makeText(MainActivity.this, "ERROR VALOR 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (Exception e) {
                    // Show a Toast
                    Toast.makeText(MainActivity.this, "ERROR NO HAY VALOR", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Create a new GastoNuevo
                GastoNuevo gastoNuevo = new GastoNuevo(gastoSeleccionado, fechaSeleccionada, valorSeleccionado);

                // Add the new GastoNuevo to the Singleton Data
                Data.getInstance().setGastosUsuario(gastoNuevo);

                // Print the new GastoNuevo
                Log.d("GastoNuevo", gastoNuevo.toString());
            }
        });




        // Open the next activity using Intent
        cambiarGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open activity, but we can return to this activity
                Intent intent = new Intent(MainActivity.this, CambiarActivity.class);
                startActivity(intent);
            }
        });

        // Open the next activity using Intent
        verGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open activity, but we can return to this activity
                Intent intent = new Intent(MainActivity.this, VerGastosActivity.class);
                startActivity(intent);
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