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
    private String fileName;
    private List<Libro> libros;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    //Constructor
    public LecturaDatos(String fileName) {
        this.fileName = fileName;
        this.libros = readFromFile(fileName);
    }

    //Getters y Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    //Metodos
    public List<Libro> readFromFile(String fileName) {
        List<Libro> libros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            libros = br.lines().map(line -> {
                String[] values = line.split(",");
                List<String> keywords = Arrays.asList(values[5].split("\\s+"));
                LocalDate date = LocalDate.parse(values[4], formatter);
                return new Libro(values[0], values[1], values[2], Integer.parseInt(values[3]), date, keywords);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }
}
