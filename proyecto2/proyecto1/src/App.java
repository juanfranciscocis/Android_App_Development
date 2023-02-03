import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        LecturaDatos readWriteFile = new LecturaDatos("datos.txt");
        List<Libro> libros = readWriteFile.readFromFile("proyecto2/proyecto1/src/datos.txt");
        
        //1.
        //Using stream print the list 
        libros.stream().forEach(System.out::println);

        //2.
        //using streams, for each book sort the keywords in alphabetical order
        libros.stream().forEach(l -> l.getPalabrasClaves().sort((p1, p2) -> p1.compareTo(p2)));
        //using streams, sort the list by title and print the list
        libros.stream().sorted((l1, l2) -> l1.getTitulo().compareTo(l2.getTitulo())).forEach(System.out::println);

        //3.
        //using streams, for each book sort the keywords in reverse order
        libros.stream().forEach(l -> l.getPalabrasClaves().sort((p1, p2) -> p2.compareTo(p1)));
        //using streams, sort the list by author and print the list
        libros.stream().sorted((l1, l2) -> l1.getAutor().compareTo(l2.getAutor())).forEach(System.out::println);

        //4.
        






        
    }
}
