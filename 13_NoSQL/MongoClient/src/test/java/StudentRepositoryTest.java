import model.BaseRepository;
import model.student.Student;
import model.student.StudentRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import static junit.framework.TestCase.assertEquals;

public class StudentRepositoryTest {
    public BaseRepository mongoDB;
    public StudentRepository students;
    @Rule
    public final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @Before
    public void setUp() {
        mongoDBContainer.start();
        String address = mongoDBContainer.getHost();
        int port = mongoDBContainer.getFirstMappedPort();
        mongoDB = new BaseRepository(address, port, "testBase");
        students = new StudentRepository(mongoDB);
        students.addStudent(new Student("Jack", 18, new String[]{"Java,Python,iOS"}));
        students.addStudent(new Student("Nick", 35, new String[]{"Web,Python"}));
        students.addStudent(new Student("Bob", 42, new String[]{"Java,Android"}));
        students.addStudent(new Student("Max", 19, new String[]{"Java", "iOS"}));
        students.addStudent(new Student("Daniel", 41, new String[]{"Java", "iOS"}));
    }

    @Test
    public void testgetCountStudentsAge40() {
        long exteted = students.getCountStudentsAge40();
        assertEquals(exteted, 2);
    }

    @Test
    public void testAddObjectInCollection() {
        long exteted = students.getCountStudents();
        assertEquals(exteted, 5);
    }

    @Test
    public void testGetNameYoungStudent() {
        String exteted = students.getNameYoungStudent();
        assertEquals(exteted, "Jack");
    }

    @Test
    public void testgetCoursesOldStudent() {
        String exteted = students.getCoursesOldStudent();
        assertEquals(exteted, "[Java,Android]");
    }
}
