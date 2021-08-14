import model.BaseRepository;
import model.student.Student;
import model.student.StudentRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String nameBase = "skill-mongo";
        String pathFile = "src/main/resources/mongo.csv";
        String host = "127.0.0.1";
        int port = 27017;

        BaseRepository mongoDB = new BaseRepository(host, port, nameBase);
        List<Student> models = ParseFile.parseFileCSVStudents(pathFile);

        StudentRepository studentRepository = new StudentRepository(mongoDB);

        models.forEach(studentRepository::addStudent);

        System.out.format("Общее количество студентов в базе: %s чел.%n", studentRepository.getCountStudents());
        System.out.format("Общее количество студентов в базе старже 40 лет: %s чел.%n", studentRepository.getCountStudentsAge40());
        System.out.format("Имя самого молодого студента: %s%n", studentRepository.getNameYoungStudent());
        System.out.format("Список курсов самого возрастного студента: %s%n", studentRepository.getCoursesOldStudent());
    }
}
