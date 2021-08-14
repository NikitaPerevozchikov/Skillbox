import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> carNumber = new ArrayList<>();
        HashSet<String> carNumberHash = new HashSet<>();
        TreeSet<String> carNumberTree = new TreeSet<>();

        String[] letter = new String[]{"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
        String[] number = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        long start;
        long finish;
        String yesNo = "найден";

        Scanner scanner = new Scanner(System.in);
        arrayNumberCar(carNumber, letter, number);

        carNumberHash.addAll(carNumber);
        carNumberTree.addAll(carNumber);

        System.out.println("Общее число номеров: " + carNumber.size() + " шт.");

        while (true) {
            System.out.println("Введите гос. номер автомобиля: ");
            String text = scanner.nextLine();

            start = System.nanoTime();
            if (!carNumber.contains(text)) {
                yesNo = "не найден";
            }
            finish = System.nanoTime() - start;
            System.out.println("Поиск перебором: Номер " + yesNo + ". Поиск занял: " + finish + " нс");

            start = System.nanoTime();
            if (Collections.binarySearch(carNumber, text) == -1) {
                yesNo = "не найден";
            }
            finish = System.nanoTime() - start;
            System.out.println("Бинарный поиск: Номер " + yesNo + ". Поиск занял: " + finish + " нс");

            start = System.nanoTime();
            if (!carNumberHash.contains(text)) {
                yesNo = "не найден";
            }
            finish = System.nanoTime() - start;
            System.out.println("Поиск в HashSet: Номер " + yesNo + ". Поиск занял: " + finish + " нс");

            start = System.nanoTime();
            if (!carNumberTree.contains(text)) {
                yesNo = "не найден";
            }
            finish = System.nanoTime() - start;
            System.out.println("Поиск в TreeSet: Номер " + yesNo + ". Поиск занял: " + finish + " нс");
        }
    }

    private static void arrayNumberCar(ArrayList<String> carNumber, String[] letter, String[] number) {
        for (int buk1 = 0; buk1 < letter.length; buk1++) {
            for (int cifra = 0; cifra < number.length; cifra++) {
                for (int buk2 = 0; buk2 < letter.length; buk2++) {
                    for (int buk3 = 0; buk3 < letter.length; buk3++) {
                        for (int reg = 1; reg <= 199; reg++) {
                            if (reg < 10) {
                                carNumber.add(letter[buk1] + number[cifra] + number[cifra] + number[cifra] + letter[buk2] + letter[buk3] + " 0" + reg);
                            } else {
                                carNumber.add(letter[buk1] + number[cifra] + number[cifra] + number[cifra] + letter[buk2] + letter[buk3] + " " + reg);
                            }

                        }
                    }
                }
            }
        }
    }

}
