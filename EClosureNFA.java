import java.util.Scanner;

class Node {
    int st;
    Node link;

    Node(int s) {
        st = s;
        link = null;
    }
}

public class EClosureNFA {

    static int set[] = new int[20];
    static int nostate, noalpha, s, notransition, c, r;
    static int buffer[] = new int[20];

    static char alphabet[] = new char[20];
    static int e_closure[][] = new int[20][20];

    static Node transition[][] = new Node[20][20];

    static void findclosure(int x, int sta) {

        if (buffer[x] == 1)
            return;

        e_closure[sta][c++] = x;
        buffer[x] = 1;

        if (alphabet[noalpha - 1] == 'e' && transition[x][noalpha - 1] != null) {

            Node temp = transition[x][noalpha - 1];

            while (temp != null) {
                findclosure(temp.st, sta);
                temp = temp.link;
            }
        }
    }

    static void insert_trantbl(int r, char c, int s) {

        int j = findalpha(c);

        if (j == 999) {
            System.out.println("Error");
            System.exit(0);
        }

        Node temp = new Node(s);
        temp.link = transition[r][j];
        transition[r][j] = temp;
    }

    static int findalpha(char c) {

        for (int i = 0; i < noalpha; i++)
            if (alphabet[i] == c)
                return i;

        return 999;
    }

    static void print_e_closure(int i) {

        System.out.print("{");

        for (int j = 0; e_closure[i][j] != 0; j++)
            System.out.print("q" + e_closure[i][j] + ",");

        System.out.print("}");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of alphabets:");
        noalpha = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter alphabets:");
        for (int i = 0; i < noalpha; i++) {
            alphabet[i] = sc.next().charAt(0);
        }

        System.out.print("Enter number of states:");
        nostate = sc.nextInt();

        System.out.print("Enter number of transitions:");
        notransition = sc.nextInt();

        System.out.println("Enter transitions (state alphabet state):");

        for (int i = 0; i < notransition; i++) {
            r = sc.nextInt();
            char c = sc.next().charAt(0);
            s = sc.nextInt();

            insert_trantbl(r, c, s);
        }

        System.out.println("\nE-closure of states:");

        for (int i = 1; i <= nostate; i++) {

            c = 0;

            for (int j = 0; j < 20; j++) {
                buffer[j] = 0;
                e_closure[i][j] = 0;
            }

            findclosure(i, i);

            System.out.print("\nE-closure(q" + i + "): ");
            print_e_closure(i);
        }

        sc.close();
    }
}