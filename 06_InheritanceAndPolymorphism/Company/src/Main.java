import employe.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
            Company GazProm = new Company("GazProm"); //создаём компанию
            GazProm.hire(new Manager(10000)); //создаём одного сотрудника
            List<Worker> listEmployees = new ArrayList<>(); //создаём список работников
            setListEmployees(listEmployees, 50, 25, 25); //добавляем в этот список работников
            GazProm.hireAll(listEmployees); //добавляем новых работников в компанию
            GazProm.getTopSalaryStaff(10);
            GazProm.getLowestSalaryStaff(30);
            firePercent(GazProm);
            GazProm.getTopSalaryStaff(10);
            GazProm.getLowestSalaryStaff(30);
    }

    // метод создания списка сотрудников
    static void setListEmployees(List<Worker> listEmployees, int operator, int manager, int topmanager) {
        for (int i = 0; i < operator; i++) {

            listEmployees.add(new Operator(40000 * ((double) ((int) (Math.random() * 100))) / 100));
        }
        for (int i = 0; i < manager; i++) {
            listEmployees.add(new Manager(20000 * ((double) ((int) (Math.random() * 100))) / 100));
        }
        for (int i = 0; i < topmanager; i++) {
            listEmployees.add(new TopManager(30000 * ((double) ((int) (Math.random() * 100))) / 100));
        }
    }

    //метод удаления 50% сотрудников (каждый второй)
    static void firePercent(Company company) {
        for (int i = 0, j = 0; i < company.getWorkers().size(); i++, j++) {
            if (j % 2 == 0) {
                company.fire(i);
                j++;
            }
        }
    }
}

