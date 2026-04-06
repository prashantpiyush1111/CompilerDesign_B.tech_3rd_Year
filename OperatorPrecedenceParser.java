import java.util.*;

public class OperatorPrecedenceParser {

    static String input;
    static int i = 0;
    static char[] stack = new char[50];
    static int top = 0, l;

    static String lasthandle = "";
    static String[] handles = {")E(", "E*E", "E+E", "i", "E^E"};

    static char[][] prec = {
            {'>', '>', '<', '<', '<', '<', '<', '>', '>'},
            {'>', '>', '<', '<', '<', '<', '<', '>', '>'},
            {'>', '>', '>', '>', '<', '<', '<', '>', '>'},
            {'>', '>', '>', '>', '<', '<', '<', '>', '>'},
            {'>', '>', '>', '>', '<', '<', '<', '>', '>'},
            {'>', '>', '>', '>', '>', 'e', 'e', '>', '>'},
            {'<', '<', '<', '<', '<', '<', '<', '>', 'e'},
            {'>', '>', '>', '>', '>', 'e', 'e', '>', '>'},
            {'<', '<', '<', '<', '<', '<', '<', '<', '>'}
    };

    static int getindex(char c) {
        switch (c) {
            case '+': return 0;
            case '-': return 1;
            case '*': return 2;
            case '/': return 3;
            case '^': return 4;
            case 'i': return 5;
            case '(': return 6;
            case ')': return 7;
            case '$': return 8;
            case 'E': return 5; // FIX
        }
        return -1;
    }

    static void shift() {
        stack[++top] = input.charAt(i++);
    }

    static int reduce() {

        for (int h = 0; h < handles.length; h++) {

            String handle = handles[h];
            int len = handle.length();
            int found = 1;

            if (top + 1 >= len) {

                for (int t = 0; t < len; t++) {
                    if (stack[top - t] != handle.charAt(t)) {
                        found = 0;
                        break;
                    }
                }

                if (found == 1) {
                    top = top - len + 1;
                    stack[top] = 'E';
                    lasthandle = handle;
                    return 1;
                }
            }
        }
        return 0;
    }

    static void dispstack() {
        for (int j = 0; j <= top; j++)
            System.out.print(stack[j]);
    }

    static void dispinput() {
        for (int j = i; j < l; j++)
            System.out.print(input.charAt(j));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the string");
        input = sc.next();

        input = input + "$";
        l = input.length();

        stack[0] = '$';

        System.out.println("\nSTACK\tINPUT\tACTION");

        while (i < l) {

            shift();

            System.out.print("\n");
            dispstack();
            System.out.print("\t");
            dispinput();
            System.out.print("\tShift");

            while (i < l &&
                   getindex(stack[top]) != -1 &&
                   getindex(input.charAt(i)) != -1 &&
                   prec[getindex(stack[top])][getindex(input.charAt(i))] == '>') {

                if (reduce() == 1) {
                    System.out.print("\n");
                    dispstack();
                    System.out.print("\t");
                    dispinput();
                    System.out.print("\tReduced: E->" + lasthandle);
                } else {
                    break;
                }
            }
        }

        String result = new String(stack, 0, top + 1);

        if (result.equals("$E$"))
            System.out.println("\nAccepted");
        else
            System.out.println("\nNot Accepted");
    }
}