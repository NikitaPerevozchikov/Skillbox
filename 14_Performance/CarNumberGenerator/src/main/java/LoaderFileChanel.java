import org.openjdk.jmh.annotations.Benchmark;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoaderFileChanel {

    public static void startGeneration() throws Exception {
        Path path = Paths.get("res/numbersFileChanel.txt");
        try (RandomAccessFile writer = new RandomAccessFile(path.toString(), "rw");
             FileChannel channel = writer.getChannel()) {
            char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            for (int regionCode = 0; regionCode < 100; regionCode++) {
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                builder.append(firstLetter);
                                builder.append(padNumber(number, 3));
                                builder.append(secondLetter);
                                builder.append(thirdLetter);
                                builder.append(padNumber(regionCode, 2));
                                builder.append("\n");
                            }
                        }
                    }
                }
                ByteBuffer buff = ByteBuffer.wrap(builder.toString().getBytes());
                channel.write(buff);
            }
        }
    }

    private static StringBuilder padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder();
        numberStr.append(number);
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, "0");
        }
        return numberStr;
    }
}
