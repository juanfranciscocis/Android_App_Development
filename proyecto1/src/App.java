//Juan Francisco Cisneros (00323665)
//Paula Campaña Donoso (00215572)
//Proyecto 1 - Streams y Lambdas
//Fecha: 07/02/2023

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        //1.
        //Se crea un objeto de la clase LecturaDatos y se llama al método leerDesdeArchivo
        LecturaDatos leerDatos = new LecturaDatos();
        List<Libro> libros = leerDatos.leerDesdeArchivo("proyecto1/src/datos.txt");
        
        //2.
        //Utilizando streams, se imprime la lista de libros
        System.out.println("-----------------------------");
        System.out.println("IMPRESION DE LA LISTA DE LIBROS");
        libros.stream().forEach(System.out::println);
        System.out.println("-----------------------------");

        //3.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS ORDENADA POR TITULO Y PALABRAS CLAVES");
        System.out.println("-----------------------------");
        //usando streams, para cada libro se ordenan las palabras claves de manera alfabética
        libros.stream().forEach(l -> l.getPalabrasClaves().sort((p1, p2) -> p1.compareTo(p2)));
        //usando streams, se ordena la lista por título y se imprime la lista
        libros.stream().sorted((l1, l2) -> l1.getTitulo().compareTo(l2.getTitulo())).forEach(l->l.imprimirTituloClaves());
        System.out.println("-----------------------------");


        //4.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS ORDENADA POR AUTOR Y PALABRAS CLAVES EN REVERSO");
        System.out.println("-----------------------------");
        //usando streams, para cada libro se ordenan las palabras claves de manera alfabética en inverso
        libros.stream().forEach(l -> l.getPalabrasClaves().sort((p1, p2) -> p2.compareTo(p1)));
        //usando streams, se ordena la lista por autor y se imprime la lista
        libros.stream().sorted((l1, l2) -> l1.getAutor().compareTo(l2.getAutor())).forEach(l->l.imprimirAutorClaves());
        System.out.println("-----------------------------");

        //5.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS AGRUPADOS POR AUTOR Y ORDENADOS POR AÑOS DE EDICION PARA CADA AUTOR");
        System.out.println("-----------------------------");
        //con la ayuda de streams, se crea una lista de mapas con el autor como clave y la lista de libros como valor
        List<Map<String, List<Libro>>> listaMapasAutor = libros.stream().collect(Collectors.groupingBy(Libro::getAutor)).entrySet().stream().map(e -> {
            Map<String, List<Libro>> mapa = new HashMap<>();
            mapa.put(e.getKey(), e.getValue());
            return mapa;
        }).collect(Collectors.toList());
        //con la ayuda de streams, se imprime la lista de mapas, pero solo el autor y la fecha de edición en orden primero por autor y luego por fecha de edición
        listaMapasAutor.stream().sorted((m1, m2) -> m1.keySet().stream().findFirst().get().compareTo(m2.keySet().stream().findFirst().get())).forEach(m -> {
            m.entrySet().stream().forEach(e -> {
                System.out.println("Autor: " + e.getKey());
                e.getValue().stream().sorted((l1, l2) -> l1.getFechaUltimaEdicion().compareTo(l2.getFechaUltimaEdicion())).forEach(l -> System.out.println("Fecha de edición: " + l.getFechaUltimaEdicion()));
                System.out.println(" ");
            });
        });
        System.out.println("-----------------------------");

        //6.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS AGRUPADOS POR AÑO DE EDICION Y ORDENADOS POR TITULO EN CADA AÑO");
        System.out.println("-----------------------------");
        //utilizando streams, se crea una lista de mapas con el año como clave y la lista de libros como valor
        List<Map<Integer, List<Libro>>> listaMapasAno = libros.stream().collect(Collectors.groupingBy(Libro::getAno)).entrySet().stream().map(e -> {
            Map<Integer, List<Libro>> mapa = new HashMap<>();
            mapa.put(e.getKey(), e.getValue());
            return mapa;
        }).collect(Collectors.toList());
        //ahora con el uso de streams, se imprime la lista de mapas, pero solo el año y el título en orden primero por año y luego por título
        listaMapasAno.stream().sorted((m1, m2) -> m1.keySet().stream().findFirst().get().compareTo(m2.keySet().stream().findFirst().get())).forEach(m -> {
            m.entrySet().stream().forEach(e -> {
                System.out.println("Año: " + e.getKey());
                e.getValue().stream().sorted((l1, l2) -> l1.getTitulo().compareTo(l2.getTitulo())).forEach(l -> System.out.println("Título: " + l.getTitulo()));
                System.out.println(" ");
            });
        });

        System.out.println("-----------------------------");

        //7.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS ORDENADA POR ISBN Y QUE TENGAN DOS O MAS PALABRAS CLAVES QUE EMPIECEN CON P");
        System.out.println("-----------------------------");
        //utilizando streams, se filtra la lista de libros que tengan dos o más palabras claves que empiecen con P y se ordena por ISBN
        List<Libro> result = libros.stream()
            .filter(b -> b.getPalabrasClaves().stream().filter(k -> k.startsWith("p")|| k.startsWith("P")).count() >= 2).collect(Collectors.toList());

        result = result.stream().sorted((i1,i2)-> i1.getISBN().compareTo(i2.getISBN())).collect(Collectors.toList());

        final List<String> palabrasP = result.stream().flatMap(b -> b.getPalabrasClaves().stream()).filter(k -> k.startsWith("p")|| k.startsWith("P")).collect(Collectors.toList());

        result = result.stream().map(b -> {
            b.setPalabrasClaves(palabrasP);
            return b;
        }).collect(Collectors.toList());

      
 
        result.forEach(l -> l.imprimirTituloISBNLista());

        System.out.println("-----------------------------");

        //8.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS ORDENADA POR AUTOR Y PALABRAS CLAVES QUE NO EMPIECEN CON P");
        System.out.println("-----------------------------");
        //con la ayuda de streams, se filtra la lista de libros que no tengan palabras claves que empiecen con P y se ordena por autor
       
        List<Libro> result2 = libros.stream()
        .filter(b -> b.getPalabrasClaves().stream().noneMatch(k -> k.startsWith("p")|| k.startsWith("P"))).collect(Collectors.toList());

        result2 = result2.stream().sorted((i1,i2)-> i1.getAutor().compareTo(i2.getAutor())).collect(Collectors.toList());

        result2.forEach(l -> l.imprimirAutorLista());
     
        
    }
}
