public class Main {

    public static void main(String[] args) {
        String[][] iks = new String[7][7];

        for (int i = 0; i < iks.length; i++) {
            for (int j = 0; j < iks[i].length; j++) {
                if (j == i || j == iks[i].length - 1 - i) {
                    iks[i][j] = "X";
                } else {
                    iks[i][j] = " ";
                }
            }
        }

        for (int i = 0; i < iks.length; i++) {
            for (int j = 0; j < iks[i].length; j++) {
                System.out.print(iks[i][j]);
            }
            System.out.println("");
        }
    }
}
