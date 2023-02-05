
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LecturaDatos {
    //Propiedades
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    //Constructor
    public LecturaDatos() {
    }
    
    //Métodos
    public List<Libro> leerDesdeArchivo(String nombreArchivo) {
        List<Libro> libros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            libros = br.lines().map(line -> {
                String[] valores = line.split(",");
                List<String> palabrasClave = Arrays.asList(valores[5].split("\\s+"));
                LocalDate fecha = LocalDate.parse(valores[4], formatter);
                return new Libro(valores[0], valores[1], valores[2], Integer.parseInt(valores[3]), fecha, palabrasClave,Float.parseFloat(valores[6]));
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }
    
}