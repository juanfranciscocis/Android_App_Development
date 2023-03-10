package com.example.proyecto2;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;

import java.math.BigInteger;
import java.util.ArrayList;
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
    private TableView<Map<String, String>> tablaResultadoSerial;

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

    public ArrayList<Factorial> listaTasks = new ArrayList<Factorial>();
    public Factorial taskSerial;

    public ObservableList<Map<String,String>> itemsThread = FXCollections.<Map<String,String>>observableArrayList();

    public ObservableList<Map<String,String>> itemsSerial = FXCollections.<Map<String,String>>observableArrayList();

    public ArrayList<Boolean> completado;

    BooleanArrayListener listener = new BooleanArrayListener(completado, new BooleanArrayListenerInterface() {
        @Override
        public void mostrarResultados() {
            System.out.println("Mostrando resultados");
            calculandoThreads.setText("Mostrando resultados ...");
            calculandoSerial.setText("Mostrando resultados ...");
            // Mostrar resultados
            try {
                mostrarTotalTask();
                mostrarTotalSerial();
            }catch (Exception e){
                System.out.println("...");
            }


        }
    });





    @FXML
    void buttonPressed(ActionEvent event) {
        int numeroFactorial = Integer.parseInt(factorialUsuario.getText());
        int numeroTasks = Integer.parseInt(tasksUsuario.getText());
        System.out.println("Factorial: " + numeroFactorial + " Tasks: " + numeroTasks);
        Thread t = new Thread(listener);
        t.start();
        System.out.println("Thread started");

        calculandoThreads.setText("Calculando ...");
        calculandoSerial.setText("Calculando ...");

        completado = new ArrayList<Boolean>();

        tableViewCreator(tablaResultadoTask);
        tablaResultadoTask.setItems(itemsThread);
        tableViewCreator(tablaResultadoSerial);
        tablaResultadoSerial.setItems(itemsSerial);




        calcThreads(numeroFactorial, numeroTasks);
        corriendoSerial(numeroFactorial);




    }

    void calcThreads(int numeroFactorial, int numeroTasks){
        int[] rango = factorialNumeroTask(numeroFactorial, numeroTasks);
        for (int i = 0; i < rango.length; i++) {
            System.out.println("Rango:"+rango[i]);
        }
        int inicio = 1;
        ArrayList<Thread> listaThreads = new ArrayList<Thread>();
        for (int i = 0; i < numeroTasks; i++) {
            int fin = inicio + rango[i] - 1;
            Factorial f = new Factorial(BigInteger.valueOf(inicio), BigInteger.valueOf(fin));
            inicio = fin + 1;

            f.messageProperty().addListener((observable,oldValue,newValue)->{
                // Factorial
                f.item.remove("Factorial");
                f.item.put("Factorial",String.valueOf(newValue));
                f.item.remove("Task");
                f.item.put("Task",String.valueOf(f.id));

                itemsThread.add(f.item);
                tablaResultadoTask.refresh();
            });
            f.valueProperty().addListener((observable,oldValue,newValue)->{
                //Tiempo
                f.item.remove("Tiempo Milisegundos");
                f.item.put("Tiempo Milisegundos",String.valueOf(newValue));

                itemsThread.add(f.item);
                tablaResultadoTask.refresh();
            });



            f.setOnSucceeded(e-> {
                completado.add(true);
            });
            listaTasks.add(f);

            listaThreads.add(new Thread(f));
            listaThreads.get(i).setDaemon(true);
            listaThreads.get(i).start();


        }
    }

    void mostrarTotalTask() {

        long totalTiempo = 0;
        BigInteger totalFactorial = BigInteger.valueOf(1);
        for (Factorial f : listaTasks) {
            totalTiempo += f.tiempo;
            totalFactorial = totalFactorial.multiply(f.subTotal);
        }
        totalSecondsThreads.setText("Total Milisegundos: " + totalTiempo);
        totalThreads.setText("Total Threads: " + totalFactorial);

        Platform.runLater(()->{
            listaTasks.clear();
            calculandoThreads.setText("COMPLETADO");
        });

    }

    void mostrarTotalSerial(){
        while (taskSerial.subTotal == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        totalMilisecondsSerial.setText("Total Milisegundos: " + taskSerial.tiempo);
        totalSerial.setText("Total Serial: " + taskSerial.subTotal);
        calculandoSerial.setText("COMPLETADO");
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



    void tableViewCreator(TableView tableView){
        TableColumn columna1 = new TableColumn("Task");
        columna1.setCellValueFactory(new MapValueFactory<>("Task"));
        TableColumn columna2 = new TableColumn("Factorial");
        columna2.setCellValueFactory(new MapValueFactory<>("Factorial"));
        TableColumn columna3 = new TableColumn("Tiempo Milisegundos");
        columna3.setCellValueFactory(new MapValueFactory<>("Tiempo Milisegundos"));

        tableView.getColumns().add(columna1);
        tableView.getColumns().add(columna2);
        tableView.getColumns().add(columna3);

    }

    void corriendoSerial(int numeroFactorial){
        Factorial f = new Factorial(BigInteger.valueOf(1), BigInteger.valueOf(numeroFactorial));


        f.messageProperty().addListener((observable,oldValue,newValue)->{
            // Factorial

            tablaResultadoSerial.getItems().removeAll(tablaResultadoSerial.getItems());

            f.item.remove("Factorial");
            f.item.put("Factorial",String.valueOf(newValue));
            f.item.remove("Task");
            f.item.put("Task",String.valueOf(f.id));

            itemsSerial.removeAll(itemsSerial);
            itemsSerial.add(f.item);
            tablaResultadoSerial.refresh();
        });

        f.valueProperty().addListener((observable,oldValue,newValue)->{
            tablaResultadoSerial.getItems().removeAll(tablaResultadoSerial.getItems());
            //Tiempo
            f.item.remove("Tiempo Milisegundos");
            f.item.put("Tiempo Milisegundos",String.valueOf(newValue));

            itemsSerial.add(f.item);
            tablaResultadoSerial.refresh();
        });

        Thread t = new Thread(f);
        t.setDaemon(true);
        t.start();

        taskSerial = f;

    }


}
