

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Libro {
    //Propiedades
   private String ISBN;
   private String titulo;
   private String autor;
   private int ediciones;
   private LocalDate fechaUltimaEdicion;
   private List<String> palabrasClaves;

    //Constructor
    public Libro(String titulo, String autor,String ISBN ,int ediciones, LocalDate fechaUltimaEdicion, List<String> palabrasClaves) {
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

    //Métodos    
    //toString
    @Override
    public String toString() {
        return "Libro{" + "ISBN=" + ISBN + ", titulo=" + titulo + ", autor=" + autor + ", ediciones=" + ediciones + ", fechaUltimaEdicion=" + fechaUltimaEdicion + ", palabrasClaves=" + palabrasClaves + '}';
    }




}
