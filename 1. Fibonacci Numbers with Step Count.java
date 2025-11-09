import java.util.*;

public class Fibonacci {
    // Recursive Fibonacci
    static int fibRec(int n) {
        if (n <= 1)
            return n;
        return fibRec(n - 1) + fibRec(n - 2);
    }

    // Non-recursive Fibonacci
    static void fibNonRec(int n) {
        int a = 0, b = 1;
        System.out.print(a + " " + b + " ");
        for (int i = 2; i < n; i++) {
            int c = a + b;
            System.out.print(c + " ");
            a = b;
            b = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        System.out.println("Non-Recursive Fibonacci:");
        fibNonRec(n);

        System.out.println("\nRecursive Fibonacci (last term): " + fibRec(n - 1));
    }
}


//Recursive:
//⏱ Time: O(2ⁿ)
//💾 Space: O(n) (stack calls)

//Non-Recursive:
//⏱ Time: O(n)
//💾 Space: O(1
