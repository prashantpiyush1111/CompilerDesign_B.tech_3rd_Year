import java.util.Scanner;

class IntermediateCodeGeneration {

    static int i = 1, j = 0, no = 0, tmpch = 90;
    static char[] str = new char[100];
    static char[] left = new char[15];
    static char[] right = new char[15];

    static class Exp {
        int pos;
        char op;
    }

    static Exp[] k = new Exp[15];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int x = 0; x < 15; x++) {
            k[x] = new Exp();
        }

        System.out.println("\t\tINTERMEDIATE CODE GENERATION\n");
        System.out.print("Enter the Expression : ");
        String input = sc.next();
        str = input.toCharArray();

        System.out.println("The intermediate code:");
        findopr();
        explore();
    }

    static void findopr() {
        for (i = 0; i < str.length && str[i] != '\0'; i++)
            if (str[i] == ':') {
                k[j].pos = i;
                k[j++].op = ':';
            }

        for (i = 0; i < str.length && str[i] != '\0'; i++)
            if (str[i] == '/') {
                k[j].pos = i;
                k[j++].op = '/';
            }

        for (i = 0; i < str.length && str[i] != '\0'; i++)
            if (str[i] == '*') {
                k[j].pos = i;
                k[j++].op = '*';
            }

        for (i = 0; i < str.length && str[i] != '\0'; i++)
            if (str[i] == '+') {
                k[j].pos = i;
                k[j++].op = '+';
            }

        for (i = 0; i < str.length && str[i] != '\0'; i++)
            if (str[i] == '-') {
                k[j].pos = i;
                k[j++].op = '-';
            }
    }

    static void explore() {
        i = 1;
        while (i < k.length && k[i].op != '\0') {
            fleft(k[i].pos);
            fright(k[i].pos);

            str[k[i].pos] = (char) tmpch--;
            System.out.print("\t" + str[k[i].pos] + " := " + new String(left).trim() + k[i].op + new String(right).trim());
            System.out.println();
            i++;
        }

        fright(-1);

        if (no == 0) {
            fleft(str.length);
            System.out.print("\t" + new String(right).trim() + " := " + new String(left).trim());
            return;
        }

        System.out.print("\t" + new String(right).trim() + " := " + str[k[--i].pos]);
    }

    static void fleft(int x) {
        int w = 0, flag = 0;
        x--;
        while (x != -1 && str[x] != '+' && str[x] != '*' && str[x] != '=' &&
                str[x] != '\0' && str[x] != '-' && str[x] != '/' && str[x] != ':') {

            if (str[x] != '$' && flag == 0) {
                left[w++] = str[x];
                left[w] = '\0';
                str[x] = '$';
                flag = 1;
            }
            x--;
        }
    }

    static void fright(int x) {
        int w = 0, flag = 0;
        x++;
        while (x != -1 && x < str.length &&
                str[x] != '+' && str[x] != '*' && str[x] != '\0' &&
                str[x] != '=' && str[x] != ':' && str[x] != '-' && str[x] != '/') {

            if (str[x] != '$' && flag == 0) {
                right[w++] = str[x];
                right[w] = '\0';
                str[x] = '$';
                flag = 1;
            }
            x++;
        }
    }
}