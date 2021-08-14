public class ParseRequest {

    public ParseRequest() {
    }

    public static boolean isAddShop(String[] command) {
        return command.length == 2 &&
                (command[0].equals("ДОБАВИТЬ_МАГАЗИН") || command[0].equals("ДМ"));
    }

    public static boolean isAddProduct(String[] command) {
        return command.length == 3 &&
                (command[0].equals("ДОБАВИТЬ_ТОВАР") || command[0].equals("ДТ")) &&
                command[2].matches("(([-+])?[0-9]+(\\.[0-9]+)?)+");
    }

    public static boolean isGetProductInShop(String[] command) {
        return command.length == 3 &&
                (command[0].equals("ВЫСТАВИТЬ_ТОВАР") || command[0].equals("ВТ"));
    }

    public static boolean isStatisticsProduct(String[] command) {
        return command.length == 1 &&
                (command[0].equals("СТАТИСТИКА_ТОВАРОВ")|| command[0].equals("СТ"));
    }
}
