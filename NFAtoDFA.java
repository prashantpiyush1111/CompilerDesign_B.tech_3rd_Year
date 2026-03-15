import java.util.*;

public class NfaToDfa {

    static int[][][] dfa = new int[100][2][100];
    static int[] state = new int[10000];
    static int[][] go = new int[10000][2];
    static int[] arr = new int[10000];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int st, fin, in;
        int[] f = new int[10];

        int i, j = 3, flag = 0, curr1, k, l;
        int x = 0;

        System.out.println("Follow the one based indexing");
        System.out.print("Enter number of states:");
        st = sc.nextInt();

        System.out.println("Give state numbers from 0 to " + (st - 1));

        for (i = 0; i < st; i++)
            state[(int) Math.pow(2, i)] = 1;

        System.out.print("Enter number of final states:");
        fin = sc.nextInt();

        System.out.print("Enter final states:");
        for (i = 0; i < fin; i++)
            f[i] = sc.nextInt();

        int p, q, r, rel;

        System.out.print("Enter number of rules according to NFA:");
        rel = sc.nextInt();

        System.out.print("Define rule: initialState input finalState");

        for (i = 0; i < rel; i++) {
            p = sc.nextInt();
            q = sc.nextInt();
            r = sc.nextInt();

            if (q == 0)
                dfa[p][0][r] = 1;
            else
                dfa[p][1][r] = 1;
        }

        System.out.print("Enter initial state:");
        in = sc.nextInt();
        in = (int) Math.pow(2, in);

        System.out.print("Solving according to DFA");

        for (i = 0; i < st; i++) {
            for (j = 0; j < 2; j++) {

                int stf = 0;

                for (k = 0; k < st; k++) {
                    if (dfa[i][j][k] == 1)
                        stf += (int) Math.pow(2, k);
                }

                go[(int) Math.pow(2, i)][j] = stf;

                System.out.println((int) Math.pow(2, i) + "-" + j + "-->" + stf);

                if (state[stf] == 0)
                    arr[x++] = stf;

                state[stf] = 1;
            }
        }

        for (i = 0; i < x; i++) {
            for (j = 0; j < 2; j++) {

                int newState = 0;

                for (k = 0; k < st; k++) {

                    if ((arr[i] & (1 << k)) != 0) {

                        int h = (int) Math.pow(2, k);

                        if (newState == 0)
                            newState = go[h][j];

                        newState = newState | go[h][j];
                    }
                }

                if (state[newState] == 0) {
                    arr[x++] = newState;
                    state[newState] = 1;
                }
            }
        }

        System.out.print("Total DFA states:");
        System.out.print("STATE 0 1");

        for (i = 0; i < 10000; i++) {
            if (state[i] == 1) {

                int y = 0;

                if (i == 0)
                    System.out.print("q0 ");
                else {
                    for (j = 0; j < st; j++) {

                        int temp = 1 << j;

                        if ((temp & i) != 0) {
                            System.out.print("q" + j + " ");
                            y += (int) Math.pow(2, j);
                        }
                    }
                }

                System.out.println(" " + go[y][0] + " " + go[y][1]);
            }
        }

        while (j-- > 0) {

            System.out.print("Enter string:");
            String str = sc.next();

            l = str.length();
            curr1 = in;
            flag = 0;

            System.out.print("Path:");
            System.out.print(curr1 + "-");

            for (i = 0; i < l; i++) {

                curr1 = go[curr1][str.charAt(i) - '0'];
                System.out.print(curr1 + "-");
            }

            System.out.print("\nFinal state: " + curr1);

            for (i = 0; i < fin; i++) {
                if ((curr1 & (1 << f[i])) != 0) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 1)
                System.out.println("String Accepted");
            else
                System.out.println("String Rejected");
        }

        sc.close();
    }
}