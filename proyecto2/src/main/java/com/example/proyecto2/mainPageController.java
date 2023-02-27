package com.example.proyecto2;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class mainPageController {

    @FXML
    private Button botonCalcular;

    @FXML
    private TextField factorialUsuario;

    @FXML
    private Label calculandoSerial;

    @FXML
    private Label calculandoThreads;

    @FXML
    private TableView<?> tablaResultadoSerial;

    @FXML
    private TableView<Map<String, String>> tablaResultadoTask;

    @FXML
    private Label totalThreads;

    @FXML
    private Label totalSerial;

    @FXML
    private TextField tasksUsuario;

    @FXML
    private Label totalMilisecondsSerial;

    @FXML
    private Label totalSecondsThreads;

    @FXML
    void buttonPressed(ActionEvent event) {
        int numeroFactorial = Integer.parseInt(factorialUsuario.getText());
        int numeroTasks = Integer.parseInt(tasksUsuario.getText());
        System.out.println("Factorial: " + numeroFactorial + " Tasks: " + numeroTasks);

        calculandoThreads.setText("Calculando ...");
        calculandoSerial.setText("Calculando ...");
        corriendoConTask(numeroFactorial, numeroTasks);
        corriendoSerial(numeroFactorial);




    }








    int[] factorialNumeroTask(int factorial, int numTasks) {
        if (factorial < 0 || numTasks < 1) {
            throw new IllegalArgumentException("Invalid input values");
        }


        int range = factorial / numTasks;
        int remainder = factorial % numTasks;

        int[] ranges = new int[numTasks];
        for (int i = 0; i < numTasks; i++) {
            ranges[i] = range;
            if (remainder > 0) {
                ranges[i]++;
                remainder--;
            }
        }

        return ranges;
    }

    void corriendoConTask(int numeroFactorial, int numeroTasks){


        int[] rango = factorialNumeroTask(numeroFactorial, numeroTasks);
        for (int i = 0; i < rango.length; i++) {
            System.out.println(rango[i]);
        }


        int inicio = 1;
        ArrayList<Factorial> listaTasks = new ArrayList<Factorial>();
        for (int i = 0; i < numeroTasks; i++) {
            int fin = inicio + rango[i] - 1;
            Factorial f = new Factorial(BigInteger.valueOf(inicio), BigInteger.valueOf(fin));
            listaTasks.add(f);
            f.setOnRunning(e -> {
                calculandoThreads.setText("Calculando ...");
            });
            f.setOnSucceeded(e -> {
                calculandoThreads.setText("Calculado");
            });
            inicio = fin + 1;
        }


        System.out.println("Lista de tasks: " + listaTasks.size());

        ArrayList<Thread> listaThreads = new ArrayList<Thread>();
        for (int i = 0; i < listaTasks.size(); i++) {
            Thread t = new Thread(listaTasks.get(i));
            listaThreads.add(t);
        }
        System.out.println("Lista de threads: " + listaThreads.size());
        System.out.println();


        for (int i = 0; i < listaThreads.size(); i++) {
            listaThreads.get(i).start();
            System.out.println("Thread " + i + " iniciado");
        }

        while (true){
            int contador = 0;
            for (int i = 0; i < listaThreads.size(); i++) {
                if (listaThreads.get(i).getState() == Thread.State.TERMINATED){
                    contador++;
                    System.out.println("Thread " + i + " terminado");
                }
                System.out.println("Thread " + i + " " + listaThreads.get(i).getState());
            }
            if (contador == listaThreads.size()){
                break;
            }
        }

        for (int i = 0; i < listaThreads.size(); i++) {
            try {
                listaThreads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        BigInteger total = BigInteger.valueOf(1);

        for (int i = 0; i < listaTasks.size(); i++) {
            System.out.println("Task " + i + " " + listaTasks.get(i).subTotal);
            total = total.multiply(listaTasks.get(i).subTotal);


        }
        System.out.println();
        System.out.println("Total: " + total);

        long tiempoTotal = 0;
        for (int i = 0; i < listaTasks.size(); i++) {
            tiempoTotal += listaTasks.get(i).tiempo;
        }
        System.out.println("Tiempo total: " + tiempoTotal);
        totalSecondsThreads.setText("Tiempo Total: " + String.valueOf(tiempoTotal) + " milisegundos");


        tableViewUpdater(tablaResultadoTask, listaTasks);
        totalThreads.setText("Total Calculado: " + String.valueOf(total));


    }


    void tableViewUpdater (TableView tableView, ArrayList<Factorial> listaTasks){
        TableColumn columna1 = new TableColumn("Task");
        columna1.setCellValueFactory(new MapValueFactory<>("Task"));
        TableColumn columna2 = new TableColumn("Factorial");
        columna2.setCellValueFactory(new MapValueFactory<>("Factorial"));
        TableColumn columna3 = new TableColumn("Tiempo Milisegundos");
        columna3.setCellValueFactory(new MapValueFactory<>("Tiempo Milisegundos"));

        tableView.getColumns().add(columna1);
        tableView.getColumns().add(columna2);
        tableView.getColumns().add(columna3);

        ObservableList<Map<String, String>> items = FXCollections.<Map<String,String>>observableArrayList();

        for (int i = 0; i < listaTasks.size(); i++) {
            Map<String,String> item = new HashMap<>();
            item.put("Task", String.valueOf(i));
            item.put("Factorial", String.valueOf(listaTasks.get(i).subTotal));
            item.put("Tiempo Milisegundos", String.valueOf(listaTasks.get(i).tiempo));
            items.add(item);
        }



        tableView.setItems(items);
    }


    void corriendoSerial(int numeroFactorial){
        Factorial f = new Factorial(BigInteger.valueOf(1), BigInteger.valueOf(numeroFactorial));
        Thread t = new Thread(f);
        t.start();
        while (true){
            if (t.getState() == Thread.State.TERMINATED){
                break;
            }
        }
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Serial: " + f.subTotal);
        calculandoSerial.setText("Calculado");
        ArrayList<Factorial> listaTasks = new ArrayList<Factorial>();
        listaTasks.add(f);

        tableViewUpdater(tablaResultadoSerial, listaTasks);
        totalSerial.setText("Total Calculado: " + String.valueOf(listaTasks.get(0).subTotal));
        totalMilisecondsSerial.setText("Tiempo Total: " + String.valueOf(listaTasks.get(0).tiempo) + " milisegundos");



    }


}
