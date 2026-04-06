import java.util.Scanner;

public class ConstantPropagation {

    static class Expr {
        String op, op1, op2, res;
        int flag;
    }

    static Expr[] arr = new Expr[10];
    static int n;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            arr[i] = new Expr();
        }

        input(sc);
        constant();
        output();
    }

    static void input(Scanner sc) {
        System.out.print("\nEnter the maximum number of expressions: ");
        n = sc.nextInt();

        System.out.println("\nEnter the input (op op1 op2 res):");

        for (int i = 0; i < n; i++) {
            arr[i].op = sc.next();
            arr[i].op1 = sc.next();
            arr[i].op2 = sc.next();
            arr[i].res = sc.next();
            arr[i].flag = 0;
        }
    }

    static void constant() {
        for (int i = 0; i < n; i++) {

            if ((isNumber(arr[i].op1) && isNumber(arr[i].op2)) || arr[i].op.equals("=")) {

                int op1 = isNumber(arr[i].op1) ? Integer.parseInt(arr[i].op1) : 0;
                int op2 = isNumber(arr[i].op2) ? Integer.parseInt(arr[i].op2) : 0;

                char op = arr[i].op.charAt(0);
                int res = 0;

                switch (op) {
                    case '+': res = op1 + op2; break;
                    case '-': res = op1 - op2; break;
                    case '*': res = op1 * op2; break;
                    case '/': res = op1 / op2; break;
                    case '=': res = op1; break;
                }

                String res1 = String.valueOf(res);
                arr[i].flag = 1;
                change(i, res1);
            }
        }
    }

    static void output() {
        System.out.println("\nOptimized code is:");

        for (int i = 0; i < n; i++) {
            if (arr[i].flag == 0) {
                System.out.println(arr[i].op + " " + arr[i].op1 + " " + arr[i].op2 + " " + arr[i].res);
            }
        }
    }

    static void change(int p, String res) {
        for (int i = p + 1; i < n; i++) {
            if (arr[p].res.equals(arr[i].op1))
                arr[i].op1 = res;
            else if (arr[p].res.equals(arr[i].op2))
                arr[i].op2 = res;
        }
    }

    static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}