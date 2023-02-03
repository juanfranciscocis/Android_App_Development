import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        LecturaDatos leerDatos = new LecturaDatos();
        List<Libro> libros = leerDatos.leerDesdeArchivo("proyecto1/src/datos.txt");
        
        //1.
        //Using stream print the list 
        System.out.println("-----------------------------");
        System.out.println("IMPRESION DE LA LISTA DE LIBROS");
        libros.stream().forEach(System.out::println);
        System.out.println("-----------------------------");

        //2.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS ORDENADA POR TITULO Y PALABRAS CLAVES");
        System.out.println("-----------------------------");
        //using streams, for each book sort the keywords in alphabetical order
        libros.stream().forEach(l -> l.getPalabrasClaves().sort((p1, p2) -> p1.compareTo(p2)));
        //using streams, sort the list by title and print the list
        libros.stream().sorted((l1, l2) -> l1.getTitulo().compareTo(l2.getTitulo())).forEach(l->l.imprimirTituloClaves());
        System.out.println("-----------------------------");


        //3.
        System.out.println("IMPRESION DE LA LISTA DE LIBROS ORDENADA POR AUTOR Y PALABRAS CLAVES EN REVERSO:");
        System.out.println("-----------------------------");
        //using streams, for each book sort the keywords in reverse order
        libros.stream().forEach(l -> l.getPalabrasClaves().sort((p1, p2) -> p2.compareTo(p1)));
        //using streams, sort the list by author and print the list
        libros.stream().sorted((l1, l2) -> l1.getAutor().compareTo(l2.getAutor())).forEach(l->l.imprimirAutorClaves());
        System.out.println("-----------------------------");

        //4.







        
    }
}
