class Fibonacci {
    public static void main(String[] args) {
        int n = 7, step = 0;
        int a = 0, b = 1, c;
        System.out.print(a + " " + b + " ");
        for (int i = 2; i < n; i++) {
            c = a + b; step++;
            System.out.print(c + " ");
            a = b; b = c;
        }
        System.out.println("\nSteps: " + step);
    }
}
