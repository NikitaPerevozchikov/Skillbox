
import modelPerson.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

import static junit.framework.TestCase.assertEquals;

public class DatingSiteTest {
    private DatingSite datingSiteTest;
    @Rule
    public GenericContainer redisContainer = new GenericContainer("redis:3.0.6")
            .withExposedPorts(6379);

    @Before
    public void setUp() {
        String address = redisContainer.getHost();
        Integer port = redisContainer.getFirstMappedPort();
        datingSiteTest = new DatingSite(address, port);
        datingSiteTest.siteEmulation();
    }

    @Test
    public void testGetDateVisitFromRedis() {
        int id = 1;
        Person person = new Person(id);
        datingSiteTest.addUserInRedis(person.getDateRegistration(), person.toString());
        long retrieved = datingSiteTest.getDateVisitFromRedis(String.valueOf(person.getId())).longValue();
        assertEquals(person.getDateRegistration(),retrieved);
    }

    @Test
    public void testShowFirstPeople () {
        int idOne = 1;
        int idTwo = 2;
        int idThree = 3;
        Person personOne = new Person(idOne);
        Person personTwo = new Person(idTwo);
        Person personThree = new Person(idThree);
        datingSiteTest.addUserInRedis(personOne.getDateRegistration(), personOne.toString());
        datingSiteTest.addUserInRedis(personTwo.getDateRegistration(), personTwo.toString());
        datingSiteTest.addUserInRedis(personThree.getDateRegistration(), personThree.toString());
        datingSiteTest.showFirstPeople();
        datingSiteTest.showFirstPeople();
        String retrieved = "3";
        assertEquals(datingSiteTest.showFirstPeople(),retrieved);
    }
}

