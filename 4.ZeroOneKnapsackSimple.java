public class ZeroOneKnapsackSimple {

    // Function to solve 0-1 Knapsack using Dynamic Programming
    static int knapSack(int capacity, int weight[], int value[], int n) {
        int dp[][] = new int[n + 1][capacity + 1];

        // Build table dp[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0)
                    dp[i][w] = 0;
                else if (weight[i - 1] <= w)
                    dp[i][w] = Math.max(value[i - 1] + dp[i - 1][w - weight[i - 1]], dp[i - 1][w]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int value[] = {60, 100, 120};
        int weight[] = {10, 20, 30};
        int capacity = 50;
        int n = value.length;

        System.out.println("Maximum value in Knapsack = " + knapSack(capacity, weight, value, n));
    }
}
