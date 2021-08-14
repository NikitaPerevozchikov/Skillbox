package employe;

import java.util.Comparator;

public class WorkerComparator implements Comparator<Worker> {
    @Override
    public int compare(Worker o1, Worker o2) {
        if (o1.getWages() > o2.getWages()) {
            return -1;
        }
        if (o1.getWages() < o2.getWages()) {
            return 1;
        }
        return 0;
    }
}
