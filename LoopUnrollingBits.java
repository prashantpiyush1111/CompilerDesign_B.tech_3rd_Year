import java.util.Scanner;

public class LoopUnrollingBits {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        long n;
        int x;
        char ch;

        System.out.print("\nEnter N: ");
        n = sc.nextLong();

        System.out.println("\n1. Loop Roll\n2. Loop UnRoll");
        System.out.print("\nEnter your choice: ");
        ch = sc.next().charAt(0);

        switch (ch) {
            case '1':
                x = countbit1(n);
                System.out.println("\nLoop Roll: Count of 1's : " + x);
                break;

            case '2':
                x = countbit2(n);
                System.out.println("\nLoop UnRoll: Count of 1's : " + x);
                break;

            default:
                System.out.println("\nWrong Choice");
        }
    }

    static int countbit1(long n) {
        int bits = 0, i = 0;

        while (n != 0) {
            if ((n & 1) == 1)
                bits++;
            n = n >> 1;
            i++;
        }

        System.out.println("\nNo of iterations: " + i);
        return bits;
    }

    static int countbit2(long n) {
        int bits = 0, i = 0;

        while (n != 0) {
            if ((n & 1) == 1) bits++;
            if ((n & 2) == 2) bits++;
            if ((n & 4) == 4) bits++;
            if ((n & 8) == 8) bits++;

            n = n >> 4;
            i++;
        }

        System.out.println("\nNo of iterations: " + i);
        return bits;
    }
}