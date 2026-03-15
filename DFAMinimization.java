import java.util.Scanner;

public class DFAMinimization {

    static int table[][] = new int[10][10];
    static int matrix[][] = new int[10][10];

    static int indexOf(char[] array, char x, int max) {
        for (int i = 0; i < max; i++) {
            if (array[i] == x)
                return i;
        }
        return -1;
    }

    static int movesToMarked(int i, int j, int l) {
        int k, x, y;

        for (k = 0; k < l; k++) {
            x = table[i][k];
            y = table[j][k];

            if (x != -1 && y != -1 && matrix[x][y] == 1) {
                return 1;
            }
        }
        return 0;
    }

    static void printStates(char[] states, int n, int f) {

        int store = 0;
        int bin, finalState;

        for (int i = 0; i < n + f; i++) {

            bin = 1 << i;

            if ((store & bin) != 0)
                continue;

            finalState = (i >= n) ? 1 : 0;

            store = store | bin;

            System.out.print(states[i]);

            for (int j = i + 1; j < n + f; j++) {

                if (matrix[j][i] == 0) {

                    bin = 1 << j;
                    store = store | bin;

                    if (j >= n)
                        finalState = 1;

                    System.out.print(states[j]);
                }
            }

            if (finalState == 1)
                System.out.print(" (f)");

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char language[] = new char[10];
        char states[] = new char[10];

        int l = 0;

        System.out.println("Enter language symbols:");

        String lang = sc.nextLine();

        for (int i = 0; i < lang.length(); i++) {
            language[l++] = lang.charAt(i);
        }

        int n = 0, f = 0;

        System.out.println("Enter normal states:");
        String normal = sc.nextLine();

        for (int i = 0; i < normal.length(); i++) {
            states[n++] = normal.charAt(i);
        }

        System.out.println("Enter final states:");
        String finals = sc.nextLine();

        for (int i = 0; i < finals.length(); i++) {
            states[n + f] = finals.charAt(i);
            f++;
        }

        int total = n + f;

        System.out.println("Enter transition table:");

        for (int i = 0; i < total; i++) {
            for (int j = 0; j < l; j++) {

                char ch = sc.next().charAt(0);

                table[i][j] = indexOf(states, ch, total);
            }
        }

        for (int i = 0; i < total; i++) {
            matrix[i][i] = -1;
        }

        for (int i = n; i < total; i++) {
            for (int j = 0; j < n; j++) {

                matrix[i][j] = 1;
                matrix[j][i] = 1;
            }
        }

        int change = 1;

        while (change == 1) {

            change = 0;

            for (int i = 0; i < total; i++) {
                for (int j = 0; j < total; j++) {

                    if (matrix[i][j] == 0) {

                        if (movesToMarked(i, j, l) == 1) {

                            change = 1;

                            matrix[i][j] = 1;
                            matrix[j][i] = 1;
                        }
                    }
                }
            }
        }

        System.out.println("Distinguishable Matrix:");

        System.out.print("  ");

        for (int i = 0; i < total; i++) {
            System.out.print(states[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < total; i++) {

            System.out.print(states[i] + " ");

            for (int j = 0; j < i; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println("Equivalent States:");

        printStates(states, n, f);

        sc.close();
    }
}