import org.hibernate.Session;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FillTables {

    public static List<LinkedPurchaseList> fillLincedPurchaseList(Session session) {

        String hqlPurchaselist = "From " + Purchaselist.class.getCanonicalName();
        List<Purchaselist> purchaselists = session.createQuery(hqlPurchaselist).getResultList();

        String hqlStudent = "From " + Student.class.getCanonicalName();
        List<Student> students = session.createQuery(hqlStudent).getResultList();
        Map<String, Integer> studentsMap = students.stream().collect(Collectors.toMap(Student::getName, Student::getId));

        String hqlCourse = "From " + Course.class.getCanonicalName();
        List<Course> courses = session.createQuery(hqlCourse).getResultList();
        Map<String, Integer> coursesMap = courses.stream().collect(Collectors.toMap(Course::getName, Course::getId));

        return purchaselists.stream()
                .map(l -> new LinkedPurchaseList(new LinkedPurchaseList.Key(studentsMap.get(l.getStudentName()), coursesMap.get(l.getCourseName()))))
                .collect(Collectors.toList());
    }
}
