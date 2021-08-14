package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 0)
@Fork(value = 1)
@Measurement(iterations = 2)

public class TestGeneration {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public void testGenerationLoader() throws Exception {
        PrintWriter writer = new PrintWriter("res/numbers.txt");
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
            writer.write(builder.toString());
        }
        writer.flush();
        writer.close();
    }

    @Benchmark
    public void testGenerationLoaderFileChanel() throws Exception {
        Path path = Paths.get("res/numbersFileChanel.txt");
        RandomAccessFile writer = new RandomAccessFile(path.toString(), "rw");
        FileChannel channel = writer.getChannel();
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
        channel.close();
        writer.close();
    }

    @Benchmark
    public void testGenerationLoaderLoaderThread() throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            Runnable task = () -> {
                try {
                    PrintWriter writer = new PrintWriter("res/numbers" + finalI + ".txt");
                    char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
                    for (int regionCode = finalI * 50; regionCode < finalI * 50 + 50; regionCode++) {
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
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e);
                }

            };
            service.execute(task);
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
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
