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
        LecturaDatos leerDatos = new LecturaDatos();
        List<Libro> libros = leerDatos.leerDesdeArchivo("proyecto1/src/datos.txt");
        
        //2.
        //Using stream print the list 
        System.out.println("-----------------------------");
        System.out.println("IMPRESION DE LA LISTA DE LIBROS");
        libros.stream().forEach(System.out::println);
        System.out.println("-----------------------------");

        //3.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS ORDENADA POR TITULO Y PALABRAS CLAVES");
        System.out.println("-----------------------------");
        //using streams, for each book sort the keywords in alphabetical order
        libros.stream().forEach(l -> l.getPalabrasClaves().sort((p1, p2) -> p1.compareTo(p2)));
        //using streams, sort the list by title and print the list
        libros.stream().sorted((l1, l2) -> l1.getTitulo().compareTo(l2.getTitulo())).forEach(l->l.imprimirTituloClaves());
        System.out.println("-----------------------------");


        //4.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS ORDENADA POR AUTOR Y PALABRAS CLAVES EN REVERSO:");
        System.out.println("-----------------------------");
        //using streams, for each book sort the keywords in reverse order
        libros.stream().forEach(l -> l.getPalabrasClaves().sort((p1, p2) -> p2.compareTo(p1)));
        //using streams, sort the list by author and print the list
        libros.stream().sorted((l1, l2) -> l1.getAutor().compareTo(l2.getAutor())).forEach(l->l.imprimirAutorClaves());
        System.out.println("-----------------------------");

        //5.
        //using streams, create a list of maps with the author as a key and the list of books as a value
        List<Map<String, List<Libro>>> listaMapasAutor = libros.stream().collect(Collectors.groupingBy(Libro::getAutor)).entrySet().stream().map(e -> {
            Map<String, List<Libro>> mapa = new HashMap<>();
            mapa.put(e.getKey(), e.getValue());
            return mapa;
        }).collect(Collectors.toList());
        //using streams, print the list of maps, but only the author and the date of edition in order first by author and then by date
        listaMapasAutor.stream().sorted((m1, m2) -> m1.keySet().stream().findFirst().get().compareTo(m2.keySet().stream().findFirst().get())).forEach(m -> {
            m.entrySet().stream().forEach(e -> {
                System.out.println("Autor: " + e.getKey());
                e.getValue().stream().sorted((l1, l2) -> l1.getFechaUltimaEdicion().compareTo(l2.getFechaUltimaEdicion())).forEach(l -> System.out.println("Fecha de edición: " + l.getFechaUltimaEdicion()));
                System.out.println(" ");
            });
        });
        System.out.println("-----------------------------");

        //6.
        //using streams, create a list of maps with the year as a key and the list of books as a value
        List<Map<Integer, List<Libro>>> listaMapasAno = libros.stream().collect(Collectors.groupingBy(Libro::getAno)).entrySet().stream().map(e -> {
            Map<Integer, List<Libro>> mapa = new HashMap<>();
            mapa.put(e.getKey(), e.getValue());
            return mapa;
        }).collect(Collectors.toList());
        //using streams, print the list of maps, but only the year and the title in order first by year and then by title
        listaMapasAno.stream().sorted((m1, m2) -> m1.keySet().stream().findFirst().get().compareTo(m2.keySet().stream().findFirst().get())).forEach(m -> {
            m.entrySet().stream().forEach(e -> {
                System.out.println("Año: " + e.getKey());
                e.getValue().stream().sorted((l1, l2) -> l1.getTitulo().compareTo(l2.getTitulo())).forEach(l -> System.out.println("Título: " + l.getTitulo()));
                System.out.println(" ");
            });
        });

        System.out.println("-----------------------------");

        //7.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS ORDENADA POR ISBN Y PALABRAS CLAVES QUE TIENEN MAS DE DOS P");
        System.out.println("-----------------------------");
        //using streams, compare the list of key words looking for the ones that start with P and sort them by their ISBN number
        List<Libro> result = libros.stream()
            .filter(b -> b.getPalabrasClaves().stream().filter(k -> k.startsWith("p")|| k.startsWith("P")).count() >= 2).collect(Collectors.toList());

            result = result.stream().sorted((i1,i2)-> i1.getISBN().compareTo(i2.getISBN())).collect(Collectors.toList());

            result.forEach(l -> l.imprimirTituloISBNLista());


        System.out.println("-----------------------------");
        //8.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS ORDENADA POR AUTOR Y PALABRAS CLAVES QUE NO TIENEN MAS DE DOS P");
        System.out.println("-----------------------------");
        //using streams, compare the list of key words that do not have words starting with P and sorting them by author
       
        List<Libro> result2 = libros.stream()
        .filter(b -> b.getPalabrasClaves().stream().noneMatch(k -> k.startsWith("p")|| k.startsWith("P"))).collect(Collectors.toList());

        result2 = result2.stream().sorted((i1,i2)-> i1.getAutor().compareTo(i2.getAutor())).collect(Collectors.toList());

        result2.forEach(l -> l.imprimirAutorLista());
     
       



        


        
    }
}
