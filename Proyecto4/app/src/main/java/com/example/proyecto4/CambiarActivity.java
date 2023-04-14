package com.example.proyecto4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CambiarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar);


        // Get all components
        FloatingActionButton getBack = findViewById(R.id.backButton);
        Spinner dropdownGastos = findViewById(R.id.datosActuales);
        Button eliminarGasto = findViewById(R.id.eliminarGasto);
        Button agregarGasto = findViewById(R.id.agregarGasto);
        TextView nuevoGastoTextField = findViewById(R.id.agregarGastoTextView);


        // Go back to the main activity
        getBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CambiarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Load current gastos to the spinner
        dropdownGastos.setAdapter(addItemsOnSpinner(Data.getInstance().getGastos()));

        // If spinner is clicked, load items to the spinner
        dropdownGastos.setOnTouchListener((v, event) -> {
            Log.d("Spinner", "Dropdown eliminados touched");
            dropdownGastos.setAdapter(addItemsOnSpinner(Data.getInstance().getGastos()));
            return false;
        });

        // If eliminar gasto button is clicked, delete the selected gasto from Data
        eliminarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = dropdownGastos.getSelectedItemPosition();
                Data.getInstance().deleteGastos(index);
                dropdownGastos.setAdapter(addItemsOnSpinner(Data.getInstance().getGastos()));
            }
        });

        // If nuevo gasto text field is clicked, clear the text field
        nuevoGastoTextField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevoGastoTextField.setText("");
            }
        });


        // If agregar gasto button is clicked, add the gasto to Data
        agregarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoGasto = nuevoGastoTextField.getText().toString();
                // if the text field is empty, show a toast to the user
                if (nuevoGasto.equals("")){
                    Toast.makeText(CambiarActivity.this, "No se puede agregar un gasto vacío", Toast.LENGTH_SHORT).show();
                    return;
                }
                Data.getInstance().setGastos(nuevoGasto);
                dropdownGastos.setAdapter(addItemsOnSpinner(Data.getInstance().getGastos()));
                // Close the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                // Show a toast to the user
                Toast.makeText(CambiarActivity.this, "Gasto agregado", Toast.LENGTH_SHORT).show();
            }
        });




    }

    //method to add items to the spinner, the method will take an arraylist as a parameter
    public ArrayAdapter<String> addItemsOnSpinner(ArrayList<String> list){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return dataAdapter;
    }

    public void eliminarGasto(View view){

    }



}