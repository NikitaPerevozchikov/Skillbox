package employe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
    private List<Worker> workers; //список устроенных сотрудников
    private String nameCompany; //наименование компании
    private double income = 0; //прибыль
    private int operator = 0; //количество операторов
    private int manager = 0; //количество менеджеров
    private int topmanager = 0; //количество топменеджеров

    public int getOperator() {
        return operator;
    }

    public int getManager() {
        return manager;
    }

    public int getTopmanager() {
        return topmanager;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public Company(String nameCompany) {
        workers = new ArrayList<>();
        this.nameCompany = nameCompany;
    }

    //найм одного сотрудника
    public void hire(Worker position) {
        workers.add(position);
        position.setCompany(this);
        position.getMonthSalary();
        switch (position.getPosition()) {
            case ("Оператор"): {
                this.operator++;
                break;
            }
            case ("Менеджер"): {
                this.manager++;
                break;
            }
            case ("Топменеджер"): {
                this.topmanager++;
                break;
            }
        }

    }

    //найм списка сотрудников
    public void hireAll(List<Worker> listEmploy) {
        for (int i = 0; i < listEmploy.size(); i++) {
            workers.add(listEmploy.get(i));
            switch (workers.get(i).getPosition()) {
                case ("Оператор"): {
                    this.operator++;
                    break;
                }
                case ("Менеджер"): {
                    this.manager++;
                    break;
                }
                case ("Топменеджер"): {
                    this.topmanager++;
                    break;
                }
            }
        }
        for (int i = 0; i < workers.size(); i++) {
            for (Worker worker : workers) {
                if (worker.wages == 0) {
                    worker.setCompany(this);
                    worker.getMonthSalary();
                }
            }

        }

    }

    //увольнение сотрудника
    public void fire(int numberWorker) {
        workers.remove(numberWorker);
    }

    //получение значения дохода компании
    public double getIncome() {
        for (Worker worker : workers) {
            if (worker.getPosition().equals("Менеджер")) {
                income += (worker.getWages() - worker.getSalary());
            }
        }
        return income;
    }

    //вывод списка сотрудников
    public void setList() {
        System.out.println("Компания " + nameCompany);
        int i = 0;
        for (Worker listWorker : workers) {
            System.out.println("Сотрудник № " + (i + 1) + ": должность: " + listWorker.getPosition() + ", зарплата: " + listWorker.getWages() + " руб.");
            i++;
        }
    }

    //вывод списка сотрудников с самой высокой зарплатой
    public void getTopSalaryStaff(int count) {
        Collections.sort(workers, new WorkerComparator());
        if (count > workers.size()) {
            count = workers.size();
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Сотрудник № " + (i + 1) + ": должность: " + workers.get(i).getPosition() + ", зарплата: " + workers.get(i).getWages() + " руб.");
        }
    }

    //вывод списка сотрудников с самой низкой зарплатой
    public void getLowestSalaryStaff(int count) {
        Collections.sort(workers, new WorkerComparator());
        Collections.reverse(workers);
        if (count > workers.size()) {
            count = workers.size();
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Сотрудник № " + (i+1) + ": должность: " + workers.get(i).getPosition() + ", зарплата: " + workers.get(i).getWages() + " руб.");
        }
    }

}



