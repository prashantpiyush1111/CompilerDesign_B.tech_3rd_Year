import java.util.*;

public class FirstFollow {

    static int count, n = 0, m = 0;
    static char[][] calc_first = new char[10][50];
    static char[][] calc_follow = new char[10][50];

    static String[] production = new String[10];

    static char[] f = new char[50], first = new char[50];

    static int k;
    static char ck;
    static int e;

    public static void main(String[] args) {

        int jm = 0, km = 0;
        int i;
        char c;

        count = 8;

        production[0] = "X=TnS";
        production[1] = "X=Rm";
        production[2] = "T=q";
        production[3] = "T=#";
        production[4] = "S=p";
        production[5] = "S=#";
        production[6] = "R=om";
        production[7] = "R=ST";

        char[] done = new char[10];
        int ptr = -1;

        for (k = 0; k < count; k++) {
            Arrays.fill(calc_first[k], '!');
        }

        int point1 = 0, point2, xxx;

        for (k = 0; k < count; k++) {

            c = production[k].charAt(0);
            point2 = 0;
            xxx = 0;

            for (int kay = 0; kay <= ptr; kay++)
                if (c == done[kay])
                    xxx = 1;

            if (xxx == 1)
                continue;

            findfirst(c, 0, 0);
            ptr++;
            done[ptr] = c;

            System.out.print("\n First(" + c + ") = { ");
            calc_first[point1][point2++] = c;

            for (i = jm; i < n; i++) {

                int chk = 0;
                for (int lark = 0; lark < point2; lark++) {
                    if (first[i] == calc_first[point1][lark]) {
                        chk = 1;
                        break;
                    }
                }

                if (chk == 0) {
                    System.out.print(first[i] + ", ");
                    calc_first[point1][point2++] = first[i];
                }
            }

            System.out.println("}");
            jm = n;
            point1++;
        }

        System.out.println("\n---------------------------------------\n");

        char[] donee = new char[10];
        ptr = -1;

        for (k = 0; k < count; k++) {
            Arrays.fill(calc_follow[k], '!');
        }

        point1 = 0;

        for (e = 0; e < count; e++) {

            ck = production[e].charAt(0);
            point2 = 0;
            xxx = 0;

            for (int kay = 0; kay <= ptr; kay++)
                if (ck == donee[kay])
                    xxx = 1;

            if (xxx == 1)
                continue;

            follow(ck);
            ptr++;
            donee[ptr] = ck;

            System.out.print(" Follow(" + ck + ") = { ");
            calc_follow[point1][point2++] = ck;

            for (i = km; i < m; i++) {

                int chk = 0;
                for (int lark = 0; lark < point2; lark++) {
                    if (f[i] == calc_follow[point1][lark]) {
                        chk = 1;
                        break;
                    }
                }

                if (chk == 0) {
                    System.out.print(f[i] + ", ");
                    calc_follow[point1][point2++] = f[i];
                }
            }

            System.out.println("}");
            km = m;
            point1++;
        }
    }

    static void findfirst(char c, int q1, int q2) {

        if (!Character.isUpperCase(c)) {
            first[n++] = c;
            return;
        }

        for (int j = 0; j < count; j++) {

            if (production[j].charAt(0) == c) {

                if (production[j].length() > 2 && production[j].charAt(2) == '#') {

                    if (q1 < production.length && q2 < production[q1].length())
                        findfirst(production[q1].charAt(q2), q1, q2 + 1);
                    else
                        first[n++] = '#';
                }

                else if (production[j].length() > 2 &&
                        !Character.isUpperCase(production[j].charAt(2))) {

                    first[n++] = production[j].charAt(2);
                }

                else if (production[j].length() > 2) {
                    findfirst(production[j].charAt(2), j, 3);
                }
            }
        }
    }

    static void follow(char c) {

        if (production[0].charAt(0) == c) {
            f[m++] = '$';
        }

        for (int i = 0; i < count; i++) {

            for (int j = 2; j < production[i].length(); j++) {

                if (production[i].charAt(j) == c) {

                    if (j + 1 < production[i].length()) {
                        followfirst(production[i].charAt(j + 1), i, j + 2);
                    }

                    if (j + 1 >= production[i].length() &&
                            c != production[i].charAt(0)) {
                        follow(production[i].charAt(0));
                    }
                }
            }
        }
    }

    static void followfirst(char c, int c1, int c2) {

        if (!Character.isUpperCase(c)) {
            f[m++] = c;
        }

        else {

            int i = 0, j = 1;

            for (i = 0; i < count; i++) {
                if (calc_first[i][0] == c)
                    break;
            }

            while (j < 50 && calc_first[i][j] != '!') {

                if (calc_first[i][j] != '#') {
                    f[m++] = calc_first[i][j];
                }

                else {

                    if (c1 < production.length && c2 < production[c1].length())
                        followfirst(production[c1].charAt(c2), c1, c2 + 1);
                    else
                        follow(production[c1].charAt(0));
                }
                j++;
            }
        }
    }
}