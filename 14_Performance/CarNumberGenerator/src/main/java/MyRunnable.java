import java.io.PrintWriter;

public class MyRunnable implements Runnable {

    private int i;

    public MyRunnable(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter("res/numbers" + i + ".txt")){
            char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            for (int regionCode = i * 50; regionCode < i * 50 + 50; regionCode++) {
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {

                                builder.append(firstLetter);
                                builder.append(padNumber(number, 3));
                                builder.append(secondLetter);
                                builder.append(thirdLetter);
                                builder.append(padNumber(regionCode, 3));
                                builder.append("\n");
                            }
                        }
                    }
                }
                writer.write(builder.toString());
            }
            writer.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private StringBuilder padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder();
        numberStr.append(number);
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, "0");
        }
        return numberStr;
    }
}
