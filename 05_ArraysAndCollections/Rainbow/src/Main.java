public class Main {
    public static void main(String[] args) {

        String text = "Каждый охотник желает знать, где сидит фазан";

        String[] arrayColors = text.split("\\,?\\s+");

        for (int i = 0; i < arrayColors.length; i++) {
        }

        for (int i = 0; i < arrayColors.length / 2; i++) {
            String j = arrayColors[i];
            arrayColors[i] = arrayColors[arrayColors.length - 1 - i];
            arrayColors[arrayColors.length - 1 - i] = j;
        }

        for (String coloroString : arrayColors) {
            System.out.println(coloroString);
        }
    }
}
