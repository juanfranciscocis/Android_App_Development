

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Libro {
    //Propiedades
   private String ISBN;
   private String titulo;
   private String autor;
   private int ediciones;
   private LocalDate fechaUltimaEdicion;
   private List<String> palabrasClaves;
   private float precio;

    //Constructor
    public Libro(String titulo, String autor,String ISBN ,int ediciones, LocalDate fechaUltimaEdicion, List<String> palabrasClaves, float price) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.ediciones = ediciones;
        // check formato de fecha dd/MM/yy
        this.fechaUltimaEdicion = fechaUltimaEdicion;
        //Lista de palabras claves no menor a 4, si es menor, error
        if(palabrasClaves.size() < 4){
            throw new IllegalArgumentException("La lista de palabras claves debe tener al menos 4 elementos");
        }
        this.palabrasClaves = palabrasClaves;

        if(price < 0){
            throw new IllegalArgumentException("El precio del libro es menor que $0");
        }
        this.precio = price;
    }

    //Getters y Setters
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getEdiciones() {
        return ediciones;
    }

    public void setEdiciones(int ediciones) {
        this.ediciones = ediciones;
    }

    public LocalDate getFechaUltimaEdicion() {
        return fechaUltimaEdicion;
    }

    public void setFechaUltimaEdicion(String fechaUltimaEdicion) {
        this.fechaUltimaEdicion = LocalDate.parse(fechaUltimaEdicion, DateTimeFormatter.ofPattern("dd/MM/yy"));
    }

    public List<String> getPalabrasClaves() {
        return palabrasClaves;
    }

    public void setPalabrasClaves(List<String> palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }

    public float getPrecio(){
        return precio;
    }

    public void setPrecio(float precio){
        this.precio = precio;
    }

    //get only year
    public int getAno(){
        return fechaUltimaEdicion.getYear();
    }


    //Métodos   
    //toString
    @Override
    public String toString() {
        // modifica el formato de fecha
        String fecha = fechaUltimaEdicion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        // retorna el libro en formato String de manera ordenada y usando espacio entre lineas
        return "Titulo: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
                "ISBN: " + ISBN + "\n" +
                "Ediciones: " + ediciones + "\n" +
                "Fecha de ultima edicion: " + fecha + "\n" +
                "Palabras claves: " + palabrasClaves + "\n";
    }



    // imprimir solo autor y palabras claves
    public void imprimirAutorClaves() {
        System.out.println( "Autor: " + autor + "\n" +
                "Palabras claves: " + palabrasClaves + "\n");
    }

    // imprimir solo titulo y palabras claves
    public void imprimirTituloClaves() {
        System.out.println( "Titulo: " + titulo + "\n" +
                "Palabras claves: " + palabrasClaves + "\n");
    }

    // imprimir solo autor y año de ultima edicion
    public void imprimirAutorFecha() {
        String fecha = fechaUltimaEdicion.format(DateTimeFormatter.ofPattern("yyyy"));
        System.out.println( "Autor: " + autor + "\n" +
                "Fecha de ultima edicion: " + fecha + "\n");
    }

    // solo fecha 
    public void imprimirFecha() {
        String fecha = fechaUltimaEdicion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println( "Fecha de ultima edicion: " + fecha + "\n");
    }

    // solo titulo
    public void imprimirTitulo() {
        System.out.println( "Titulo: " + titulo + "\n");
    }

    //imprimir titulo con isbn
    public void imprimirTituloISBN() {
        System.out.println( "Titulo: " + titulo + "\n" + 
        "ISBN: " + ISBN + "\n");
    }

    //imprimir titulo con isbn
    public void imprimirTituloISBNLista() {
        System.out.println( "Titulo: " + titulo + "\n" + 
        "ISBN: " + ISBN + "\n" + "Lista:" + palabrasClaves +  "\n");
    }

    //imprimir titulo con isbn
    public void imprimirAutorLista() {
        System.out.println( "Autor: " + autor + "\n" + 
        "Lista:" + palabrasClaves +  "\n");
    }


    




}
