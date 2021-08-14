import model.student.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParseFile {
    public static List<Student> parseFileCSVStudents(String fileDirectory) {
        Path path = Paths.get(fileDirectory);
        List<String> list = new ArrayList<>();
        try {
            Files.readAllLines(path);
            list.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list.stream().map(s -> s.split(",?\"?\""))
                .map(e -> {
                    String[] nameAge = e[0].split(",");
                    return new Student(nameAge[0], Integer.parseInt(nameAge[1]), e[1].split(","));
                })
                .collect(Collectors.toList());
    }
}
