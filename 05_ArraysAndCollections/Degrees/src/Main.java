public class Main {
    public static void main(String[] args) {

        double[] degrees = new double[30];

        double mediumDegrees = 0;
        int healthyPeople = 0;

        for (int i = 0; i < degrees.length; i++) {
            degrees[i] = 32 + 8 * ((double) ((int) (Math.random() * 10)) / 10);
            System.out.println(degrees[i]);
            mediumDegrees += degrees[i];
            if (degrees[i] >= 36.2 && degrees[i] <= 36.9) {
                healthyPeople++;
            }

        }

        System.out.println("Средняя температура по больнице: " + ((double)((int) (mediumDegrees/degrees.length*10))/10));
        System.out.println("Количество здоровых людей: " + healthyPeople);


    }
}
