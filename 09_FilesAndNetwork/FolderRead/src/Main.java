import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите путь к папке (пример: C:/Users/UserName/Desktop/Folder): ");
            String wayFolder = scanner.nextLine();
            Directory directory = new Directory(wayFolder);
            if (Directory.isFolderExist(directory)) {
                System.out.println("Общий вес папки: " + Directory.formatReadableWeightFolder(directory.getWeightFolder()) + "\n");
                continue;
            }
            System.out.println("Неправильный ввод");
        }
    }
}